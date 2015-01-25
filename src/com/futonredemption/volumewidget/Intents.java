package com.futonredemption.volumewidget;

import java.util.ArrayList;
import java.util.List;

import org.beryl.app.ChoosableIntent;
import org.beryl.app.IntentChooser;

import com.futonredemption.volumewidget.services.SoundVolumeService;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Intents
{
	public static final String ACTION_STREAM_PICK = "ACTION_STREAM_PICK";
	public static final String ACTION_STREAM_SET = "ACTION_STREAM_SET";
	
	public static final String ACTION_REFRESH_WIDGETS = "ACTION_REFRESH_WIDGETS";
	public static final String ACTION_NUDGE_VOLUME = "ACTION_NUDGE_VOLUME";
	
	public static final String EXTRA_STREAMTYPE = "EXTRA_STREAMTYPE";
	public static final String EXTRA_VOLUME_ADJUST_DIRECTION = "EXTRA_VOLUME_ADJUST_DIRECTION";
	
	public static final String EXTRA_BUTTONID = "EXTRA_BUTTONID";
	public static final String EXTRA_WIDGETID = "EXTRA_WIDGETID";
	
	public static final int EXTRA_INT_INVALIDVALUE = -1;
	
	public static Intent refreshWidgets(Context context)
	{
		final Intent intent = new Intent(context, SoundVolumeService.class);
		intent.setAction(ACTION_REFRESH_WIDGETS);
		return intent;
	}
	
	public static Intent nudgeVolumeTemplate(Context context) {
		final Intent intent = new Intent(context, SoundVolumeService.class);
		intent.setAction(ACTION_NUDGE_VOLUME);
		intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
		
		return intent;
	}
	
	public static Intent fillNudgeVolume(Intent intent, int adjustDirection, int targetStream) {
		intent.putExtra(EXTRA_VOLUME_ADJUST_DIRECTION, adjustDirection);
		intent.putExtra(EXTRA_STREAMTYPE, targetStream);
		return intent;
	}
	
	public static Intent nudgeVolume(Context context, int adjustDirection, int targetStream)
	{
		final Intent intent = nudgeVolumeTemplate(context);
		return fillNudgeVolume(intent, adjustDirection, targetStream);
	}

	public static Intent setStream(Context context, int widgetId, int buttonId, int targetStream) {
		final Intent intent = new Intent(context, SoundVolumeService.class);
		intent.setAction(ACTION_STREAM_SET);
		intent.putExtra(EXTRA_WIDGETID, widgetId);
		intent.putExtra(EXTRA_BUTTONID, buttonId);
		intent.putExtra(EXTRA_STREAMTYPE, targetStream);
		return intent;
	}
	
	public static Intent pickStream(Context context, int widgetId, int buttonId) {
		final ArrayList<ChoosableIntent> intents = new ArrayList<ChoosableIntent>();
		
		List<VolumeStreamItem> streamDefs = new VolumeStreamList(context).createList();
		for(VolumeStreamItem streamDef : streamDefs) {
			
			intents.add(createChoosableVolumeStream(context, widgetId, buttonId, streamDef));
		}
		
		final CharSequence title = context.getText(R.string.select_volume);
		
		return IntentChooser.createChooserIntent(context, title, intents);
	}
	
	private static ChoosableIntent createChoosableVolumeStream(final Context context, final int widgetId, final int buttonId, final VolumeStreamItem streamDef) {
		final Intent intent = setStream(context, widgetId, buttonId, streamDef.StreamId);
		final CharSequence title = streamDef.StreamName;
		return new ChoosableIntent(title, intent, ChoosableIntent.RUNAS_Service);
	}
}
