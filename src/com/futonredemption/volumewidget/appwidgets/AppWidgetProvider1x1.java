package com.futonredemption.volumewidget.appwidgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;


public class AppWidgetProvider1x1 extends AppWidgetProvider2x1
{
	// Both 2x1 and 1x1 widgets behave exactly the same.
	
	public static void UpdateWidget(Context context, AppWidgetManager widgetManager, int widgetId, final VolumeWidgetUpdateInfo info)
	{
		AppWidgetProvider2x1.UpdateWidget(context, widgetManager, widgetId, info);
	}
}
