package com.futonredemption.volumewidget.appwidgets.volumelist;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class VolumeListRemoteViewsService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new VolumeAdjustRemoteViewsFactory(this, intent);
	}
}
