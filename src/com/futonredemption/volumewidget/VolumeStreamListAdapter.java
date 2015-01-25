package com.futonredemption.volumewidget;

import java.util.List;

import android.content.Context;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class VolumeStreamListAdapter extends BaseAdapter {

	private final Context context;
	private final List<VolumeStreamItem> volumeStreams;
	
	public VolumeStreamListAdapter(final Context context) {
		this.context = context;
		this.volumeStreams = new VolumeStreamList(context).createList();
	}
	
	public int getCount() {
		return volumeStreams.size();
	}

	public Object getItem(int position) {
		return volumeStreams.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {
		public ImageButton NudgeDownButton;
		public ImageButton NudgeUpButton;
		public TextView StreamName;
		public VolumeStreamItem item;
		
		public void bindView(View view) {
			StreamName = (TextView)view.findViewById(R.id.stream_name);
			NudgeDownButton = (ImageButton)view.findViewById(R.id.btnNudgeDown);
			NudgeUpButton = (ImageButton)view.findViewById(R.id.btnNudgeUp);
		}
		
		public void setItem(VolumeStreamItem item) {
			this.item = item;
			StreamName.setText(item.StreamName);
		}
	}
	
	public static class VolumeAdjustOnClickListener implements OnClickListener {

		private final int direction;
		
		public VolumeAdjustOnClickListener(int direction) {
			this.direction = direction;
		}
		
		public void onClick(View v) {
			View targetView = (View)v.getParent().getParent();
			final ViewHolder holder = (ViewHolder)targetView.getTag();
			
			int flags = Constants.SET_MUSIC_VOLUME_FLAGS;
			if (holder.item.StreamId == AudioManager.STREAM_RING) {
				flags = Constants.SET_RINGER_VOLUME_FLAGS;
			}

			final AudioManager manager = (AudioManager) v.getContext().getSystemService(Context.AUDIO_SERVICE);
			manager.adjustStreamVolume(holder.item.StreamId, this.direction, flags);
		}
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			convertView = getInflater().inflate(R.layout.appwidget_listvolumes_item, parent, false);
			holder = new ViewHolder();
			holder.bindView(convertView);
			convertView.setTag(holder);
			holder.NudgeDownButton.setOnClickListener(new VolumeAdjustOnClickListener(AudioManager.ADJUST_LOWER));
			holder.NudgeUpButton.setOnClickListener(new VolumeAdjustOnClickListener(AudioManager.ADJUST_RAISE));
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.setItem(volumeStreams.get(position));
		
		return convertView;
	}
	private LayoutInflater getInflater() {
		return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
}
