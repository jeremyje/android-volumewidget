package com.futonredemption.volumewidget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.preference.PreferenceManager;

public class WidgetPreferences {

	private final SharedPreferences prefs;
	
	public WidgetPreferences(Context context) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public int getStream(int widgetId) {
		return getStream(widgetId, 0);
	}
	
	public int getStream(int widgetId, int buttonId) {
		final String prefString = widgetButtonPref(widgetId, buttonId);
		int defValue;
		
		if(buttonId == 0) {
			defValue = AudioManager.STREAM_RING;
		} else {
			defValue = AudioManager.STREAM_MUSIC;
		}
		
		return prefs.getInt(prefString, defValue);
	}
	
	public void setButtonStream(int widgetId, int buttonId, int streamType) {
		Editor editor = prefs.edit();
		final String prefString = widgetButtonPref(widgetId, buttonId);
		
		editor.putInt(prefString, streamType);
		editor.commit();
	}
	
	private String widgetButtonPref(int widgetId, int buttonId) {
		StringBuilder sb = new StringBuilder();
		sb.append("Stream_");
		sb.append(widgetId);
		sb.append("_");
		sb.append(buttonId);
		return sb.toString();
	}
}
