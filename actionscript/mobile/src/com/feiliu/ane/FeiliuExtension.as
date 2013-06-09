package com.feiliu.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class FeiliuExtension extends EventDispatcher 
	{ 
		public static const FEILIU_FUNCTION_INIT:String = "feiliu_function_init";//与java端中Map里的key一致
		public static const FEILIU_FUNCTION_LOGIN:String = "feiliu_function_login";//与java端中Map里的key一致
		public static const FEILIU_FUNCTION_PAY:String = "feiliu_function_pay";//与java端中Map里的key一致
		public static const FEILIU_FUNCTION_EXIT:String = "feiliu_function_exit";//与java端中Map里的key一致
		
		public static const EXTENSION_ID:String = "com.feiliu.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext;
		
		/**单例的实例*/
		private static var _instance:FeiliuExtension; 
		public function FeiliuExtension(target:IEventDispatcher=null)
		{
			super(target);
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
			
		} 
		
		//第二个为参数，会传入java代码中的FREExtension的createContext方法
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():FeiliuExtension
		{
			if(_instance == null) 
				_instance = new FeiliuExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		/**
		 *init发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function FeiliuInit(key:int):String{
			if(extContext ){
				return extContext.call(FEILIU_FUNCTION_INIT,key) as String;
			}
			return "call login failed";
		} 
		
		/**
		 *登录发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function FeiliuLogIn(key:int):String{
			if(extContext ){
				return extContext.call(FEILIU_FUNCTION_LOGIN,key) as String;
			}
			return "call login failed";
		} 
		/**
		 *付费发送函数 
		 * @param key 暂时传什么都可以 留着以后可能要用
		 * @return 
		 * 
		 */		 
		public function FeiliuPay(data:Vector.<String>,len:int = 5):String{
			if(extContext && data.length == len){   
				return extContext.call(FEILIU_FUNCTION_PAY,data)as String;
			}
			return "call pay failed";
		}
		
		/**
		 *退出SDK时候调用   这个函数只在退出游戏的时候调用  
		 * @param key
		 * @return 
		 * 
		 */		
		public function ExitSDKHandle(key:int):String{
			if(extContext){ 
				return extContext.call(FEILIU_FUNCTION_EXIT,key) as String;
			}
			return "call exit failed";
		}
		/**
		 *获取用户名 暂时没实现 看需要  
		 * @return 
		 * 
		 */		
		public function getUserName():String{
			return null;
		}
		
		/**
		 *获取唯一ID标识 暂时没实现 看需要  
		 * @return 
		 * 
		 */
		public function getUserID():String{
			return null;
		}
	} 
}