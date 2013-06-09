package com.feiliu.ane;

import com.adobe.fre.FREContext;
import com.fl.gamehelper.base.info.UserInfo;
import com.fl.gamehelper.service.FlGameSdkMSg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class FlGameSdkMSgReceiver extends BroadcastReceiver {
	public static FREContext _context;
	private String TAG = "FlGameSdkMSgReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(FlGameSdkMSg.PLAY_GAME_ACTION)) {
			String _msg = intent.getStringExtra(FlGameSdkMSg.MSG_TAG);
			if (_msg.equals(FlGameSdkMSg.MSG_TO_GAME_MAIN_SCREEN)) {
				Intent _toGameScreenIntent = new Intent(context,
						_context.getActivity().getClass());
				_toGameScreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(_toGameScreenIntent);

				Log.d(TAG, "---FlGameSdkMSgReceiver--用户登录-------");
				// 取用户的唯一标识 uuid
				UserInfo _u = UserInfo.getUserInfo(_context.getActivity());
				String uuid = _u.getmUUid();
				// 取开放平台用户的token,非第三方平台无token
				int accountType = _u.getmAccountType();
				String thirdToken = null;
				switch (accountType) {
				case 2:
					thirdToken = _u.getmToken();
					break;
				case 1:
					thirdToken = _u.getmToken();
					break;

				default:
					thirdToken = "no third";
				}	
				String str = "返回数据"; 
				str += "*"+0;
				str += "*"+uuid; 
				str += "*"+thirdToken;
				str += "*"+"arr[2] is uid";
				str += "*"+"arr[3] is thirdToken";
				_context.dispatchStatusEventAsync(TAG, str);
				Log.d(TAG, "---dispatchStatusEventAsync--用户登录-------");
			}
			else
			{
				String str = "返回数据";
				str += "*"+2;
				str += "*"+"back is error";
				_context.dispatchStatusEventAsync(TAG, str);
				Log.d(TAG, "---dispatchStatusEventAsync--用户退出-------");
			}
		}
	}

}
