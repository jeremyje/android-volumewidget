package com.futonredemption.volumewidget;

import android.media.AudioManager;

public class Constants {
	public static final int SET_MUSIC_VOLUME_FLAGS = AudioManager.FLAG_SHOW_UI;
	public static final int SET_RINGER_VOLUME_FLAGS = SET_MUSIC_VOLUME_FLAGS | AudioManager.FLAG_ALLOW_RINGER_MODES
			| AudioManager.FLAG_VIBRATE;
}
