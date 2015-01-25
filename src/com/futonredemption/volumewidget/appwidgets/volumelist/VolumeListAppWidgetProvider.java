package com.futonredemption.volumewidget.appwidgets.volumelist;

import com.futonredemption.volumewidget.Intents;
import com.futonredemption.volumewidget.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class VolumeListAppWidgetProvider extends AppWidgetProvider
{
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		handleUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	private void handleUpdate(Context context,
			AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int len = appWidgetIds.length;
		int appWidgetId;
		for(int i = 0; i < len; i++) {
			appWidgetId = appWidgetIds[i];
			final Intent listViewServiceIntent = createListViewService(context, appWidgetId);
			final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_listvolumes);
			views.setRemoteAdapter(appWidgetId, R.id.volume_list, listViewServiceIntent);
			views.setEmptyView(R.id.volume_list, R.id.empty_view);
			final PendingIntent intentTemplate = createIntentTemplate(context, appWidgetId);
			views.setPendingIntentTemplate(R.id.volume_list, intentTemplate);
			
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	private PendingIntent createIntentTemplate(Context context, int appWidgetId) {
		final PendingIntent pIntentTemplate;
		final Intent intent = Intents.nudgeVolumeTemplate(context);
		pIntentTemplate = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		return pIntentTemplate;
	}

	private static Intent createListViewService(final Context context, final int appWidgetId) {
		final Intent intent = new Intent(context, VolumeListRemoteViewsService.class);
		intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		return intent;
	}
}
