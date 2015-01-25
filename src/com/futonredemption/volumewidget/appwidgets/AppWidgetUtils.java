package com.futonredemption.volumewidget.appwidgets;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidgetUtils {

	private static final int PENDING_INTENT_FLAGS = PendingIntent.FLAG_UPDATE_CURRENT;

	public static final void BindClickAction(final Context context, final RemoteViews views, final int resId,
			final int widgetId, final Intent intent) {
		final int requestCode = generateRequestCode(resId, widgetId);
		PendingIntent pIntent = PendingIntent.getService(context, requestCode, intent, PENDING_INTENT_FLAGS);
		views.setOnClickPendingIntent(resId, pIntent);
	}

	private static int generateRequestCode(int resId, int widgetId) {
		StringBuilder sb = new StringBuilder();
		sb.append(resId);
		sb.append(" ");
		sb.append(widgetId);
		// Only needed if we have more problems with uniqueness.
		//sb.append(System.currentTimeMillis());
		return sb.toString().hashCode();
	}

	public static final void BindClickActionActivity(final Context context, final RemoteViews views, final int resId,
			final int widgetId, final Intent intent) {
		final int requestCode = generateRequestCode(resId, widgetId);
		PendingIntent pIntent = PendingIntent.getActivity(context, requestCode, intent, PENDING_INTENT_FLAGS);
		views.setOnClickPendingIntent(resId, pIntent);
	}
}
