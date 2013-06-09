package com.feiliu.ane 
{ 
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class FeiliuEvents 
	{ 
		public function FeiliuEvents()
		{
		} 
		/**************************平台通知************************************/
		/**
		 *init 
		 */		
		public static const FEILIU_SDK_STATUS:String = "FeiliuInit";
		/**
		 * 用户登录
		 */
		public static const FEILIU_LOGIN_STATUS : String = "FeiliuLogin";
		
		/**
		 * 用户注销
		 */
		public static const FEILIU_LOGOUT_STATUS : String = "FeiliuExit";
		
		/**
		 * 充值
		 */
		public static const FEILIU_PAY_STATUS : String = "FeiliuPay";
	} 
}