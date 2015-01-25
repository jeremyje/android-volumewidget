package com.futonredemption.volumewidget;

import android.content.Context;
import android.media.AudioManager;

public class AudioStreamState {
	public final int MaxVolume;
	public final int Volume;
	public final int VolumePercent;
	public final int StreamId;
	public final CharSequence StreamName;
	
	public AudioStreamState(Context context, int streamId) {
		final AudioManager manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		MaxVolume = manager.getStreamMaxVolume(streamId);
		Volume = manager.getStreamVolume(streamId);
		VolumePercent = (Volume * 100) / MaxVolume;
		StreamId = streamId;
		StreamName = streamIdToName(context, streamId);
	}
	
	public static CharSequence streamIdToName(final Context context, final int streamId) {
		CharSequence streamName;
		
		switch(streamId) {
			case AudioManager.STREAM_ALARM: { streamName = context.getText(R.string.alarm); } break;
			case AudioManager.STREAM_NOTIFICATION: { streamName = context.getText(R.string.notification); } break;
			case AudioManager.STREAM_SYSTEM: { streamName = context.getText(R.string.system); } break;
			case AudioManager.STREAM_VOICE_CALL: { streamName = context.getText(R.string.voice); } break;
			case AudioManager.STREAM_RING: { streamName = context.getText(R.string.ringer); } break;
			case AudioManager.STREAM_MUSIC: { streamName = context.getText(R.string.music); } break;
			case AudioManager.STREAM_DTMF: { streamName = context.getText(R.string.touchtone); } break;
			default: { streamName = "Unknown"; }
		}
		
		return streamName;
	}
	
}
