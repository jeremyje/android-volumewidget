package com.futonredemption.volumewidget.appwidgets.volumelist;

import java.util.List;

import com.futonredemption.volumewidget.Intents;
import com.futonredemption.volumewidget.R;
import com.futonredemption.volumewidget.VolumeStreamItem;
import com.futonredemption.volumewidget.VolumeStreamList;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

public class VolumeAdjustRemoteViewsFactory implements RemoteViewsFactory {

	private final Context context;
	private final List<VolumeStreamItem> streams;
	
	public VolumeAdjustRemoteViewsFactory(Context context, Intent intent) {
		this.context = context;
		this.streams = new VolumeStreamList(context).createList();
	}
	
	public int getCount() {
		return streams.size();
	}

	public long getItemId(int position) {
		return 0;
	}

	public RemoteViews getLoadingView() {
		// TODO Auto-generated method stub
		return null;
	}

	public RemoteViews getViewAt(int position) {
		final VolumeStreamItem item = streams.get(position);
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_listvolumes_item);
		views.setTextViewText(R.id.stream_name, item.StreamName);
		
		final Intent fillInIntentUp = Intents.fillNudgeVolume(new Intent(), AudioManager.ADJUST_RAISE, item.StreamId);
		views.setOnClickFillInIntent(R.id.btnNudgeUp, fillInIntentUp);
		
		final Intent fillInIntentDown = Intents.fillNudgeVolume(new Intent(), AudioManager.ADJUST_LOWER, item.StreamId);
		views.setOnClickFillInIntent(R.id.btnNudgeDown, fillInIntentDown);
		
		return views;
	}

	public int getViewTypeCount() {
		return 1;
	}

	public boolean hasStableIds() {
		return true;
	}

	public void onCreate() {
		// TODO Auto-generated method stub

	}

	public void onDataSetChanged() {
		// TODO Auto-generated method stub

	}

	public void onDestroy() {
		// TODO Auto-generated method stub

	}

}
