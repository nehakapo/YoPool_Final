package com.yahoo.android.yopool.activity;


import java.util.ArrayList;

import com.yahoo.android.yopool.R;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> names;
	private ArrayList<String> pick_up_times;
	private ArrayList<String> pic_urls;
	private LayoutInflater inflater;

	public CustomAdapter(Context context, ArrayList<String> names, ArrayList<String> profile_titles, ArrayList<String> pic_urls) {
		this.context = context;
		this.names = names;
		this.pick_up_times = profile_titles;
		this.pic_urls = pic_urls;
	}

	@Override
	public int getCount() {
		return names.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	public void remove(int arg0)
	{
		names.remove(arg0);
		pick_up_times.remove(arg0);
		pic_urls.remove(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView name;
		TextView pick_up_time;
		ImageView icon;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.list_item, parent, false);

		name = (TextView) itemView.findViewById(R.id.nameTextView);
		pick_up_time = (TextView) itemView.findViewById(R.id.value);
		icon = (ImageView) itemView.findViewById(R.id.profilePicView);

		name.setText(names.get(position));
		pick_up_time.setText(pick_up_times.get(position));
		
		int id = context.getResources().getIdentifier(pic_urls.get(position), "drawable", context.getPackageName());
		icon.setImageResource(id);
		
		return itemView;
	}
	
}
