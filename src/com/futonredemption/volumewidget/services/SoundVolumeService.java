package com.futonredemption.volumewidget.services;

import com.futonredemption.volumewidget.Constants;
import com.futonredemption.volumewidget.Intents;
import com.futonredemption.volumewidget.WidgetPreferences;
import com.futonredemption.volumewidget.appwidgets.AppWidgetProvider1x1;
import com.futonredemption.volumewidget.appwidgets.AppWidgetProvider2x1;
import com.futonredemption.volumewidget.appwidgets.AppWidgetProvider3x1;
import com.futonredemption.volumewidget.appwidgets.VolumeWidgetUpdateInfo;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class SoundVolumeService extends IntentService {

	public SoundVolumeService() {
		super("SoundVolumeService");
	}

	public SoundVolumeService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final String action = intent.getAction();

		if (action.equals(Intents.ACTION_NUDGE_VOLUME)) {
			handleNudgeVolume(intent);
		} else if (action.equals(Intents.ACTION_REFRESH_WIDGETS)) {
			refreshWidgets(intent);
		} else if (action.equals(Intents.ACTION_STREAM_SET)) {
			setVolumeStream(intent);
		}
	}

	private void setVolumeStream(Intent intent) {
		final int widgetId = intent.getIntExtra(Intents.EXTRA_WIDGETID, Intents.EXTRA_INT_INVALIDVALUE);
		final int buttonId = intent.getIntExtra(Intents.EXTRA_BUTTONID, Intents.EXTRA_INT_INVALIDVALUE);
		final int streamType = intent.getIntExtra(Intents.EXTRA_STREAMTYPE, Intents.EXTRA_INT_INVALIDVALUE);
		WidgetPreferences prefs = new WidgetPreferences(this);
		prefs.setButtonStream(widgetId, buttonId, streamType);
		
		updateAllWidgets();
	}

	private void refreshWidgets(Intent intent) {
		updateAllWidgets();
	}

	private void handleNudgeVolume(Intent intent) {
		int streamType = intent.getIntExtra(Intents.EXTRA_STREAMTYPE, Intents.EXTRA_INT_INVALIDVALUE);
		int adjustDirection = intent.getIntExtra(Intents.EXTRA_VOLUME_ADJUST_DIRECTION, AudioManager.ADJUST_SAME);

		if (streamType != Intents.EXTRA_INT_INVALIDVALUE) {
			int flags = Constants.SET_MUSIC_VOLUME_FLAGS;
			if (streamType == AudioManager.STREAM_RING) {
				flags = Constants.SET_RINGER_VOLUME_FLAGS;
			}

			adjustVolume(streamType, adjustDirection, flags);
			updateAllWidgets();
		}
	}

	private void updateAllWidgets() {
		int i, len;
		final AppWidgetManager widgetManager = getAppWidgetManager();
		VolumeWidgetUpdateInfo info;

		final int[] ids3x1 = widgetManager.getAppWidgetIds(new ComponentName(this, AppWidgetProvider3x1.class));
		len = ids3x1.length;
		for (i = 0; i < len; i++) {
			info = VolumeWidgetUpdateInfo.createInstance(this, ids3x1[i]);
			AppWidgetProvider3x1.UpdateWidget(this, widgetManager, ids3x1[i], info);
		}

		final int[] ids2x1 = widgetManager.getAppWidgetIds(new ComponentName(this, AppWidgetProvider2x1.class));
		len = ids2x1.length;
		for (i = 0; i < len; i++) {
			info = VolumeWidgetUpdateInfo.createInstance(this, ids2x1[i]);
			AppWidgetProvider2x1.UpdateWidget(this, widgetManager, ids2x1[i], info);
		}

		final int[] ids1x1 = widgetManager.getAppWidgetIds(new ComponentName(this, AppWidgetProvider1x1.class));
		len = ids1x1.length;
		for (i = 0; i < len; i++) {
			info = VolumeWidgetUpdateInfo.createInstance(this, ids1x1[i]);
			AppWidgetProvider1x1.UpdateWidget(this, widgetManager, ids1x1[i], info);
		}
	}

	public static Intent getServiceIntent(Context context, String action) {
		final Intent intent = new Intent(context, SoundVolumeService.class);
		intent.setAction(action);
		return intent;
	}

	private void adjustVolume(int streamType, int adjustDirection, int flags) {
		final AudioManager manager = getAudioManager();
		manager.adjustStreamVolume(streamType, adjustDirection, flags);
	}

	private AppWidgetManager _appWidgetManager = null;

	private AppWidgetManager getAppWidgetManager() {
		if (_appWidgetManager == null) {
			_appWidgetManager = AppWidgetManager.getInstance(this);
		}

		return _appWidgetManager;
	}

	private AudioManager _audioManager = null;

	private AudioManager getAudioManager() {
		if (_audioManager == null) {
			_audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		}

		return _audioManager;
	}
}
