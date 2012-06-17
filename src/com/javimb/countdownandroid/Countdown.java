package com.javimb.countdownandroid;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;

/**
 * Simple Countdown class for Andorid apps
 * @author  Javi Imbernon
 */
public class Countdown {
	private TextView text;
	private int min, sec, startMin, startSec;
	private boolean running;
	private CountDownTimer timer;
	private Vibrator vibrator;
	private final long[] vibrationPattern = {0, 500, 500, 500, 500, 1000};
	
	/**
	 * Constructor.
	 *
	 * @param context
	 * @param text TextView used as timer
	 * @param startMin initial minutes
	 * @param startSec initial seconds
	 */
	public Countdown(Context context, TextView text, int startMin, int startSec) {
		this.text = text;
		this.startMin = startMin;
		this.startSec = startSec;		
		this.min = startMin;
		this.sec = startSec;
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		this.running = false;
		initText();
	}
	
	private void createTimer(int minutes, int seconds){
		timer = new CountDownTimer(minSecToMillis(minutes, seconds), 1000) {

		     public void onTick(long millisUntilFinished) {
		    	 text.setText(millisTominSec(millisUntilFinished));
		     }

		     public void onFinish() {
		    	 vibrator.vibrate(vibrationPattern, -1);
	    		 running = false;
	    		 reset();		    	 
		     }
		};
	}
	
	/**
	 * Start the countdown, only if it is paused.
	 *
	 * @return true if it is able to start
	 */
	public boolean start(){
		if(!isRunning()){
			createTimer(min, sec);
			timer.start();
			running = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Pause the countdown, only if it is started.
	 *
	 * @return true if it is able to pause
	 */
	public boolean pause(){
		if(isRunning()){
			timer.cancel();
			running = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Reset the countdown, only if it is paused.
	 *
	 * @return true if it is able to reset
	 */
	public boolean reset(){
		if(!isRunning()){
			min = startMin;
			sec = startSec;
			initText();
			createTimer(min, sec);
			return true;
		}
		return false;
	}
	
	/**
	 * @return true if the countdown is running
	 */
	public boolean isRunning(){
		return running;
	}
	
	private void initText(){
		String minSec = "";
		if(min < 10){
			minSec += "0";
		}
		minSec += min + ":";
		if(sec < 10){
			minSec += "0";
		}
		minSec += sec;
		text.setText(minSec);
	}
	
	private int minSecToMillis(int min, int sec){
		int millis = min * 60 * 1000;
		millis += sec * 1000;
		return millis;
	}
	
	private String millisTominSec(long millis){
		String minSec = "";
		long min = millis / 60000;
		this.min = (int) min;
		long sec = millis % 60000 / 1000;
		this.sec = (int) sec;
		
		if(min < 10){
			minSec += "0";
		}
		minSec += min + ":";
		if(sec < 10){
			minSec += "0";
		}
		minSec += sec;
		
		return minSec;
	}
}
