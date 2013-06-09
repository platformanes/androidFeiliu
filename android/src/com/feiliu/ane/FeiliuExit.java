package com.feiliu.ane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class FeiliuExit implements FREFunction {

	private String TAG = "FeiliuExit";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		_context.dispatchStatusEventAsync(TAG, "退出返回");
		return result;
	}

}
