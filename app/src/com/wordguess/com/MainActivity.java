package com.wordguess.com;

import java.io.IOException;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.plus.PlusClient;

import com.facebook.*;
import com.facebook.Session.StatusCallback;
import com.facebook.android.Facebook;
import com.facebook.internal.SessionTracker;
import com.facebook.internal.Utility;
import com.facebook.model.GraphUser;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.transitions.CCFadeTransition;

public class MainActivity extends Activity //implements ConnectionCallbacks, OnConnectionFailedListener
{
	private static String MY_AD_UNIT_ID = "a15188e7c9840f4";
	protected CCGLSurfaceView _glSurfaceView;
	private AdView adView;

	private boolean isResumed = false;
	private static final int SPLASH = 0;
	//private static final int SELECTION = 1;
	private static final int FRAGMENT_COUNT = 1;

	private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
	
	public static boolean sound=true;

	//  Facebook fb;
	//private SessionTracker mSessionTracker;
	//private Session mCurrentSession;

	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

	private ProgressDialog mConnectionProgressDialog;
	private PlusClient mPlusClient;
	private ConnectionResult mConnectionResult;

	public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
	{
		super.onActivityResult(paramInt1, paramInt2, paramIntent);
		System.out.println("Inside onActivityResult");

//		if (paramInt1 == REQUEST_CODE_RESOLVE_ERR && paramInt2 == RESULT_OK)
//		{
//			mConnectionResult = null;
//			mPlusClient.connect();
//			System.out.println("Inside if plus connected");
//		}
//		Intent intent = new Intent("android.intent.category.LAUNCHER");
//		intent.setClassName("com.wordguess.com", "com.wordguess.com.MainActivity");
//		this.startActivity(intent);
		
		
		
		CCDirector.sharedDirector().resume();
		//CCDirector.sharedDirector().getActivity().
		//Session.getActiveSession().onActivityResult(this, paramInt1, paramInt2, paramIntent);
		//    if (mCurrentSession.isOpened()) {
		//        Request.executeMeRequestAsync(mCurrentSession, new Request.GraphUserCallback() {
		//
		//              // callback after Graph API response with user object
		//              @Override
		//              public void onCompleted(GraphUser user, Response response) {
		//                  Log.w("myConsultant", user.getId() + " " + user.getName() + " " + user.getInnerJSONObject());
		//              }
		//            });
		//        }



	}

	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		getWindow().setFlags(1024, 1024);
		getWindow().setFlags(128, 128);
		this._glSurfaceView = new CCGLSurfaceView(this);
		setContentView(this._glSurfaceView);

		//    FragmentManager fm = getSupportFragmentManager();
		//    fragments[SPLASH] = fm.findFragmentById(R.id.splashFragment);
		//   // fragments[SELECTION] = fm.findFragmentById(R.id.selectionFragment);
		//
		//    FragmentTransaction transaction = fm.beginTransaction();
		//    for(int i = 0; i < fragments.length; i++) {
		//        transaction.hide(fragments[i]);
		//    }
		//    transaction.commit();
		//this.signInWithFacebook();

		this.adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
		this.adView.setGravity(81);
		super.addContentView(this.adView, new ViewGroup.LayoutParams(-1, -1));
		this.adView.loadAd(new AdRequest());
		
		SharedPreferences sharedpreferences = CCDirector.sharedDirector().getActivity().getSharedPreferences("sound", 0);
		
		this.sound=sharedpreferences.getBoolean("sound_stat", true);
	
//		mPlusClient = new PlusClient.Builder(this, this, this).setVisibleActivities("com.wordguess.com.MainActivity")
//		.build();
//		// Progress bar to be displayed if the connection failure is not resolved.
//		mConnectionProgressDialog = new ProgressDialog(this);
//		mConnectionProgressDialog.setMessage("Signing in...");

	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	{
		if (paramInt == KeyEvent.KEYCODE_BACK)
		{
			Log.i("Test", "Back button pressed!");
			if (MainLayer.iScreen == 2)
			{
				Log.i("PLAY LAYER", "Back button pressed!");
				CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5F, MainLayer.scene()));
				return false;
			}
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	public void onPause()
	{
		super.onStop();
		isResumed=false;
		CCDirector.sharedDirector().pause();
		android.content.SharedPreferences.Editor editor = CCDirector.sharedDirector().getActivity().getSharedPreferences("sound", 0).edit();
		editor.putBoolean("sound_stat", this.sound);
		editor.commit();
		//mPlusClient.disconnect();
	}

	public void onResume()
	{
		super.onResume();
		isResumed=true;
		CCDirector.sharedDirector().resume();
		//mPlusClient.connect();
	}

	public void onStart()
	{
		super.onStart();
		CCDirector.sharedDirector().attachInView(this._glSurfaceView);
		CCDirector.sharedDirector().setDisplayFPS(false);
		CCDirector.sharedDirector().setAnimationInterval(0.0166666675359011D);
		CCScene localCCScene = MainLayer.scene();
		CCDirector.sharedDirector().runWithScene(localCCScene);
		//mPlusClient.connect();
	}

	public void onStop()
	{
		super.onStop();
		CCDirector.sharedDirector().end();
		android.content.SharedPreferences.Editor editor = CCDirector.sharedDirector().getActivity().getSharedPreferences("sound", 0).edit();
		editor.putBoolean("sound_stat", this.sound);
		editor.commit();
		//mPlusClient.disconnect();
	}


	/*private void signInWithFacebook() {

	    mSessionTracker = new SessionTracker(getBaseContext(), new StatusCallback() {

	        @Override
	        public void call(Session session, SessionState state, Exception exception) {
	        }
	    }, null, false);

	    String applicationId = Utility.getMetadataApplicationId(getBaseContext());
	    mCurrentSession = mSessionTracker.getSession();

	    if (mCurrentSession == null || mCurrentSession.getState().isClosed()) {
	        mSessionTracker.setSession(null);
	        Session session = new Session.Builder(getBaseContext()).setApplicationId(applicationId).build();
	        Session.setActiveSession(session);
	        mCurrentSession = session;
	    }

	    if (!mCurrentSession.isOpened()) {
	        Session.OpenRequest openRequest = null;
	        openRequest = new Session.OpenRequest(this);

	        if (openRequest != null) {
	            openRequest.setDefaultAudience(SessionDefaultAudience.FRIENDS);
	            openRequest.setPermissions(Arrays.asList("user_birthday", "email", "user_location"));
	            openRequest.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);

	            mCurrentSession.openForRead(openRequest);
	        }
	    }else {
	        Request.executeMeRequestAsync(mCurrentSession, new Request.GraphUserCallback() {
	              @Override
	              public void onCompleted(GraphUser user, Response response) {
	                  Log.w("myConsultant", user.getId() + " " + user.getName() + " " + user.getInnerJSONObject());
	              }

	            });
	    }
	}*/

//	@Override
//	public void onConnectionFailed(ConnectionResult result) {
//		// TODO Auto-generated method stub
//		if (mConnectionProgressDialog.isShowing()) {
//			// The user clicked the sign-in button already. Start to resolve
//			// connection errors. Wait until onConnected() to dismiss the
//			// connection dialog.
//			if (result.hasResolution()) {
//				try {
//					result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
//				} catch (SendIntentException e) {
//					mPlusClient.connect();
//				}
//			}
//		}
//
//		// Save the intent so that we can start an activity when the user clicks
//		// the sign-in button.
//		mConnectionResult = result;
//	}
//
//	@Override
//	public void onConnected(Bundle arg0) {
//		// TODO Auto-generated method stub
//		mConnectionProgressDialog.dismiss();
//		String accountName = mPlusClient.getAccountName();
//		 Toast.makeText(this, accountName + " is connected.", Toast.LENGTH_LONG).show();
//	}
//
//	@Override
//	public void onDisconnected() {
//		// TODO Auto-generated method stub
//		Log.d("g+", "disconnected");
//		
//	}

	//  private void showFragment(int fragmentIndex, boolean addToBackStack) {
	//	    FragmentManager fm = getSupportFragmentManager();
	//	    FragmentTransaction transaction = fm.beginTransaction();
	//	    for (int i = 0; i < fragments.length; i++) {
	//	        if (i == fragmentIndex) {
	//	            transaction.show(fragments[i]);
	//	        } else {
	//	            transaction.hide(fragments[i]);
	//	        }
	//	    }
	//	    if (addToBackStack) {
	//	        transaction.addToBackStack(null);
	//	    }
	//	    transaction.commit();
	//	}
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.MainActivity
 * JD-Core Version:    0.6.2
 */