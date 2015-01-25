package com.futonredemption.volumewidget.fragments;

import com.futonredemption.volumewidget.R;
import com.futonredemption.volumewidget.VolumeStreamListAdapter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VolumeAdjustListFragment extends ListFragment {

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_volumelist, container);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new VolumeStreamListAdapter(getActivity()));
	}
}
