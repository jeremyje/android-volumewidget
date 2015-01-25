package com.futonredemption.volumewidget.appwidgets;

import com.futonredemption.volumewidget.AudioStreamState;
import com.futonredemption.volumewidget.Intents;
import com.futonredemption.volumewidget.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.media.AudioManager;
import android.widget.RemoteViews;

public class AppWidgetProvider3x1 extends AppWidgetProvider
{
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(Intents.refreshWidgets(context));
	}
	
	public static void UpdateWidget(Context context, AppWidgetManager widgetManager, int widgetId, final VolumeWidgetUpdateInfo info)
	{
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_2stream);

		final AudioStreamState state0 = info.StreamInfo.get(0);
		final AudioStreamState state1 = info.StreamInfo.get(1);
		
		AppWidgetUtils.BindClickAction(context, views, R.id.btnNudgeDown0, widgetId,
						Intents.nudgeVolume(context, AudioManager.ADJUST_LOWER, state0.StreamId));
		
		AppWidgetUtils.BindClickAction(context, views, R.id.btnNudgeUp0, widgetId,
				Intents.nudgeVolume(context, AudioManager.ADJUST_RAISE, state0.StreamId));

		AppWidgetUtils.BindClickAction(context, views, R.id.btnNudgeDown1, widgetId,
				Intents.nudgeVolume(context, AudioManager.ADJUST_LOWER, state1.StreamId));
		
		AppWidgetUtils.BindClickAction(context, views, R.id.btnNudgeUp1, widgetId,
				Intents.nudgeVolume(context, AudioManager.ADJUST_RAISE, state1.StreamId));

		views.setTextViewText(R.id.btnCaption0, state0.StreamName);
		views.setTextViewText(R.id.btnCaption1, state1.StreamName);
		
		AppWidgetUtils.BindClickActionActivity(context, views, R.id.btnCaption0, widgetId,
				Intents.pickStream(context, widgetId, 0));
		
		AppWidgetUtils.BindClickActionActivity(context, views, R.id.btnCaption1, widgetId,
				Intents.pickStream(context, widgetId, 1));
		
		widgetManager.updateAppWidget(widgetId, views);
	}
}
