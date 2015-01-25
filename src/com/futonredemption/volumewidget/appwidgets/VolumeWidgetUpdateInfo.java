package com.futonredemption.volumewidget.appwidgets;

import java.util.ArrayList;

import com.futonredemption.volumewidget.AudioStreamState;
import com.futonredemption.volumewidget.WidgetPreferences;

import android.content.Context;

public class VolumeWidgetUpdateInfo
{
	public final ArrayList<AudioStreamState> StreamInfo = new ArrayList<AudioStreamState>();
	
	private VolumeWidgetUpdateInfo() {
		// only made to force use of static method.
	}
	
	
	public static VolumeWidgetUpdateInfo createInstance(final Context context, int widgetId) {
		final ArrayList<Integer> streamIds = new ArrayList<Integer>();
		
		final WidgetPreferences prefs = new WidgetPreferences(context);
		streamIds.add(prefs.getStream(widgetId, 0));
		streamIds.add(prefs.getStream(widgetId, 1));
		
		return createInstance(context, streamIds);
	}
	
	public static VolumeWidgetUpdateInfo createInstance(final Context context, ArrayList<Integer> streamIds) {
		final VolumeWidgetUpdateInfo info = new VolumeWidgetUpdateInfo();
		
		for(Integer streamId : streamIds) {
			info.StreamInfo.add(new AudioStreamState(context, streamId.intValue()));
		}

		return info;
	}
}
