package com.futonredemption.volumewidget.appwidgets;

import com.futonredemption.volumewidget.AudioStreamState;
import com.futonredemption.volumewidget.Intents;
import com.futonredemption.volumewidget.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.media.AudioManager;
import android.widget.RemoteViews;

public class AppWidgetProvider2x1 extends AppWidgetProvider
{
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(Intents.refreshWidgets(context));
	}
	
	public static void UpdateWidget(Context context, AppWidgetManager widgetManager, int widgetId, final VolumeWidgetUpdateInfo info)
	{
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_1stream);
		final AudioStreamState state = info.StreamInfo.get(0);
		
		AppWidgetUtils.BindClickAction(context, views, R.id.btnNudgeDown, widgetId,
				Intents.nudgeVolume(context, AudioManager.ADJUST_LOWER, state.StreamId));
		
		AppWidgetUtils.BindClickAction(context, views, R.id.btnNudgeUp, widgetId,
				Intents.nudgeVolume(context, AudioManager.ADJUST_RAISE, state.StreamId));
		
		AppWidgetUtils.BindClickAction(context, views, R.id.btnCaption, widgetId,
				Intents.pickStream(context, widgetId, 0));

		AppWidgetUtils.BindClickActionActivity(context, views, R.id.btnCaption, widgetId,
				Intents.pickStream(context, widgetId, 0));
				
		views.setTextViewText(R.id.btnCaption, state.StreamName);
		
		widgetManager.updateAppWidget(widgetId, views);
	}
}
