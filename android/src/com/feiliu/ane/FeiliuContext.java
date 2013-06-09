package com.feiliu.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class FeiliuContext extends FREContext {
	/**
	 * INIT sdk
	 */
	public static final String FEILIU_FUNCTION_INIT = "feiliu_function_init";
	/**
	 * 登录Key
	 */
	public static final String FEILIU_FUNCTION_LOGIN = "feiliu_function_login";
	/**
	 * 付费Key
	 */
	public static final String FEILIU_FUNCTION_PAY = "feiliu_function_pay";
	/**
	 * 退出Key
	 */
	public static final String FEILIU_FUNCTION_EXIT = "feiliu_function_exit";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
//	       //映射
		   map.put(FEILIU_FUNCTION_INIT, new FeiliuInit());
	       map.put(FEILIU_FUNCTION_LOGIN, new FeiliuLogin());
	       map.put(FEILIU_FUNCTION_PAY, new FeiliuPay());
	       map.put(FEILIU_FUNCTION_EXIT, new FeiliuExit());
	       return map;
	}

}
