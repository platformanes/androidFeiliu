package com.feiliu.ane;

import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fl.gamehelper.base.info.UserInfo;
import com.fl.gamehelper.service.UserAccountCallback;
import com.fl.gamehelper.service.UserAccountManager;
import com.fl.gamehelper.ui.activity.account.LoginActivity;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class FeiliuLogin implements FREFunction ,UserAccountCallback{

	private String TAG = "FeiliuLogin";
	private FREContext _context;
	UserAccountManager mUserAccountManager;
	UserInfo mUserInfo;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FlGameSdkMSgReceiver._context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		Intent _intent = new Intent(_context.getActivity(), LoginActivity.class);
		_context.getActivity().startActivity(_intent);
		Log.d(TAG, "---------Login初始化返回-------");
		return result;
	}
	
	@Override
	public void acquireUserInfo(UserInfo aUserInfo, boolean aIsLogin) {
		if (aIsLogin) {
			mUserInfo = aUserInfo;
			//mUserInfo.getmUUid() + " 您刚才的操作成功了!  "
			Log.d(TAG, "---------Ture----用户登录-------");
			String str = "返回数据";
			str += "*"+0;
			str += "*"+mUserInfo.getmUUid(); 
			str += "*"+"arr[2] is uid";
			str += "*"+"arr[3] is session";
			_context.dispatchStatusEventAsync(TAG, str);
		} else {
			Log.d(TAG, "---------Flase----用户登录-------");
			String str = "返回数据";
			str += "*"+2;
			str += "*"+mUserInfo.getmUUid(); 
			str += "*"+"arr[2] is uid";
			str += "*"+"arr[3] is session";
			_context.dispatchStatusEventAsync(TAG, str);
			//成功退出！
		}
	}

	@Override
	public void errorToExit() {
		//welcome 后台登录失败 
		_context.dispatchStatusEventAsync(TAG, "welcome 后台登录失败");
	}
}
