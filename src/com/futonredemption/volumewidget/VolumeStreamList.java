package com.futonredemption.volumewidget;

import java.util.ArrayList;
import java.util.List;

import org.beryl.app.AndroidVersion;

import android.content.Context;
import android.media.AudioManager;

public class VolumeStreamList {

	private final Context context;
	public VolumeStreamList(final Context context) {
		this.context = context;
	}
	
	public List<VolumeStreamItem> createList() {
		final List<VolumeStreamItem> result = new ArrayList<VolumeStreamItem>();
		result.add(createItem(AudioManager.STREAM_ALARM, R.string.alarm));
		result.add(createItem(AudioManager.STREAM_MUSIC, R.string.music));
		result.add(createItem(AudioManager.STREAM_NOTIFICATION, R.string.notification));
		result.add(createItem(AudioManager.STREAM_RING, R.string.ringer));
		
		if(AndroidVersion.isBeforeIceCreamSandwich()) {
			result.add(createItem(AudioManager.STREAM_SYSTEM, R.string.system));
		}
		
		result.add(createItem(AudioManager.STREAM_VOICE_CALL, R.string.voice));
		
		if(AndroidVersion.isEclairOrHigher() && AndroidVersion.isBeforeHoneycomb()) {
			result.add(createItem(AudioManager.STREAM_DTMF, R.string.touchtone));
		}

		return result;
	}

	private VolumeStreamItem createItem(int streamId, int resId) {
		final VolumeStreamItem item = new VolumeStreamItem();
		item.StreamId = streamId;
		item.StreamName = context.getText(resId);
		
		return item;
	}
}
