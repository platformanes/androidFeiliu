package com.feiliu.ane;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;

import com.adobe.fre.FREArray;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.feiliu.alipay.AlixId;
import com.feiliu.alipay.BaseHelper;
import com.feiliu.alipay.MobileSecurePayHelper;
import com.feiliu.alipay.MobileSecurePayer;
import com.feiliu.alipay.PartnerConfig;
import com.feiliu.alipay.Products;
import com.feiliu.alipay.ResultChecker;
import com.feiliu.alipay.Rsa;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class FeiliuPay implements FREFunction {

	private String TAG = "FeiliuPay";
	public static FREContext _context;
	private  ArrayList<Products.ProductDetail> mproductlist;
	private ProgressDialog mProgress = null;
	
	@Override
	public FREObject call(final FREContext context, FREObject[] $args) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		String info;
		Log.d(TAG, "---------pay 001-------");
		if($args.length<1)
		{
			_context.dispatchStatusEventAsync(TAG,"参数不正确！");
			return null;
		}
		try{
			FREArray __array = (FREArray) $args[0];
			int __len = (int)__array.getLength();
			if(__len == 0)
			{
				_context.dispatchStatusEventAsync(TAG,"传入数组参数不正确！");
				return null;
			}

			info = __array.getObjectAt(0).getAsString();
			Log.d(TAG, "---------pay info-------"+info);
		}
		catch(Exception e)
		{
			_context.dispatchStatusEventAsync(TAG, "输入参数有误："+e.getMessage());
			return null;
		}
		if(info != null)FeiliuPayHandle(info);
		
		return result;
	}
 
	private void FeiliuPayHandle(String $info)
	{
		Products products = new Products();
		this.mproductlist = products.retrieveProductInfo();
		
		// TODO Auto-generated method stub
		// 检测安全支付服务是否被安装
		Log.d(TAG, "---------pay 检测安全支付服务是否被安装-------");
		MobileSecurePayHelper mspHelper = new MobileSecurePayHelper(_context.getActivity());
		boolean isMobile_spExist = mspHelper.detectMobile_sp();
		if (!isMobile_spExist)
		{
			//未安装
		}
		// check some info.
		// 检测配置信息
		Log.d(TAG, "---------pay 检测参数是否齐备-------");
		if (!checkInfo()) {
			Log.d(TAG, "---------pay 缺少partner或者seller，请在src/com/alipay/android/appDemo4/PartnerConfig.java中增加。-------");
		}
		// start the pay.
		// 调用pay方法进行支付
		Log.d(TAG, "---------pay开始-------");
		// 根据订单信息开始进行支付 
		try {
			
			String info = URLDecoder.decode($info); 
			// start the pay. 
			// 调用pay方法进行支付
			Log.d(TAG, "---------支付-------"+info);
			MobileSecurePayer msp = new MobileSecurePayer();
			boolean bRet = msp.pay(info, mHandler, AlixId.RQF_PAY, _context.getActivity());
			/*
				add by feiliu.com lyl 2012_06_12
				把从飞流计费服务器端获得的签名串里<RetMsg>这个节点的内容，URLDecoder.decode下，
				然后赋值给info。
			*/
			if (bRet) {
				// show the progress bar to indicate that we have started
				// paying.
				// 显示“正在支付”进度条
				closeProgress();
				mProgress = BaseHelper.showProgress(_context.getActivity(), null, "正在支付", false,
						true);
			} 
			else
			{
				
			}
		} catch (Exception ex) {
			
		}
	}
	
	/**
	 * get the selected order info for pay. 获取商品订单信息
	 * 
	 * @param position
	 *            商品在列表中的位置
	 * @return
	 */
	String getOrderInfo(int position) {
		String strOrderInfo = "partner=" + "\"" + PartnerConfig.PARTNER + "\"";
		strOrderInfo += "&";
		strOrderInfo += "seller=" + "\"" + PartnerConfig.SELLER + "\"";
		strOrderInfo += "&";
		strOrderInfo += "out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		strOrderInfo += "&";
		strOrderInfo += "subject=" + "\"" + mproductlist.get(position).subject
				+ "\"";
		strOrderInfo += "&";
		strOrderInfo += "body=" + "\"" + mproductlist.get(position).body + "\"";
		strOrderInfo += "&";
		strOrderInfo += "total_fee=" + "\""
				+ mproductlist.get(position).price.replace("一口价:", "") + "\"";
		strOrderInfo += "&";
		strOrderInfo += "notify_url=" + "\""
				+ "http://notify.java.jpxx.org/index.jsp" + "\"";

		return strOrderInfo;
	}
	
	/**
	 * get the out_trade_no for an order. 获取外部订单号
	 * 
	 * @return
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		String strKey = format.format(date);

		java.util.Random r = new java.util.Random();
		strKey = strKey + r.nextInt();
		strKey = strKey.substring(0, 15);
		return strKey;
	}

	//
	//
	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param signType
	 *            签名方式
	 * @param content
	 *            待签名订单信息
	 * @return
	 */
	String sign(String signType, String content) {
		return Rsa.sign(content, PartnerConfig.RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 * @return
	 */
	String getSignType() {
		String getSignType = "sign_type=" + "\"" + "RSA" + "\"";
		return getSignType;
	}

	/**
	 * get the char set we use. 获取字符集
	 * 
	 * @return
	 */
	String getCharset() {
		String charset = "charset=" + "\"" + "utf-8" + "\"";
		return charset;
	}
	
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

	/**
	 * check some info.the partner,seller etc. 检测配置信息
	 * partnerid商户id，seller收款帐号不能为空
	 * 
	 * @return
	 */
	private boolean checkInfo() {
		String partner = PartnerConfig.PARTNER;
		String seller = PartnerConfig.SELLER;
		if (partner == null || partner.length() <= 0 || seller == null
				|| seller.length() <= 0)
			return false;

		return true;
	}

	//
	// the handler use to receive the pay result.
	// 这里接收支付结果，支付宝手机端同步通知
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				String strRet = (String) msg.obj;

				// 测试中打印同步通知log，上线建议注释掉，或者自行设置开关
				Log.d(TAG, strRet);
				
				switch (msg.what) {
				case AlixId.RQF_PAY: {
					//
					closeProgress();

					BaseHelper.log(TAG, strRet);

					// 此处将提示给开发人员具体的交易状态码，
					// 由于安全支付服务付款成功以后会有提示展示给用户，所以建议在上线版本中不进行额外提示
					// 以免造成用户提示的混乱。
					// 从通知中获取参数
					try {
						// 获取交易状态，具体状态代码请参看文档
						String tradeStatus = "resultStatus=";
						int imemoStart = strRet.indexOf("resultStatus=");
						imemoStart += tradeStatus.length();
						int imemoEnd = strRet.indexOf(";memo=");
						tradeStatus = strRet.substring(imemoStart, imemoEnd);

						// 对通知进行验签
						ResultChecker resultChecker = new ResultChecker(strRet);

						int retVal = resultChecker.checkSign();
						// 返回验签结果以及交易状态
						// 验签失败
						if (retVal == ResultChecker.RESULT_CHECK_SIGN_FAILED) {
							
						} else {
							
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	//
	//
	/**
	 * the OnCancelListener for lephone platform. lephone系统使用到的取消dialog监听
	 */
	 static class AlixOnCancelListener implements
			DialogInterface.OnCancelListener {
		Activity mcontext;

		AlixOnCancelListener(Activity context) {
			mcontext = context;
		}

		public void onCancel(DialogInterface dialog) {
			mcontext.onKeyDown(KeyEvent.KEYCODE_BACK, null);
		}
	}

	//
	// close the progress bar
	// 关闭进度框
	void closeProgress() {
		try {
			if (mProgress != null) {
				mProgress.dismiss();
				mProgress = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
