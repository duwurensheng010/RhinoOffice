package com.fzc.rhinooffice.module;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter{
	protected Context context;
	protected List<T> datas;
	protected LayoutInflater mInflater;
	protected int layoutId;
	
	public CommonAdapter(Context context,List<T> datas,int layoutId){
		this.datas = datas;
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.layoutId = layoutId;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}
	
	@Override
	public T getItem(int position) {
		return datas.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = getViewHolder(position, convertView, parent);
		convert(holder, getItem(position));
		return holder.getConvertView();
	}
	
	//通过viewholder获得组件，通过item获得组件所要呈现的信息
	public abstract void convert(ViewHolder holder,T item);
	
	private ViewHolder getViewHolder(int position, View convertView,ViewGroup parent){
		return ViewHolder.get(context, convertView, parent, layoutId, position);
	}
}
