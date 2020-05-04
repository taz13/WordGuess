package com.wordguess.com;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiapplicious.whatstheword.R;

public class SplashFragment extends Fragment {

	public SplashFragment(){
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.splash, 
	            container, false);
	    return view;
	}
}
