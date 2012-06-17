package com.javimb.countdownandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Simple app for test the Countdown class
 * @author  Javi Imbernon
 */
public class CountdownTesterActivity extends Activity implements OnClickListener {
	private TextView countdownTextView;
	private Button startButton, pauseButton, resetButton;
	private Countdown countdown;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Keep always the screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        countdownTextView = (TextView) findViewById(R.id.countdown);
        startButton = (Button) findViewById(R.buttons.start);
        pauseButton = (Button) findViewById(R.buttons.pause);
        resetButton = (Button) findViewById(R.buttons.reset);
        
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        
        //Initialize a countdown at 0:10
        countdown = new Countdown(this, countdownTextView, 0, 10);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.buttons.start:
			countdown.start();
			break;
			
		case R.buttons.pause:
			countdown.pause();
			break;
			
		case R.buttons.reset:
			countdown.reset();
			break;
		}
	}
}