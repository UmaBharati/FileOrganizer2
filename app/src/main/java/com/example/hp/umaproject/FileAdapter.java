package com.example.hp.umaproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class FileAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> fileNamesList;
    ArrayList<File> fileList;
    public FileAdapter(Context context, ArrayList<String> fileListNames,ArrayList<File> fileList) {
        this.context = context;
        this.fileNamesList = fileListNames;
        this.fileList=fileList;

        Log.d("imgurllist1",fileList+"");
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fileNamesList.size();
    }

    @Override
    public Object getItem(int i) {
        return getItemId(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder {
        TextView File_N;
        RelativeLayout File_layout;
        ImageView Bus_img;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Log.d("imgurllist",fileNamesList+"");
        FileAdapter.Holder holder = new FileAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.file_list, null);
        holder.File_N = (TextView) rowView.findViewById(R.id.file_name);
        holder.Bus_img = (ImageView) rowView.findViewById(R.id.file_img);
        holder.File_layout=(RelativeLayout) rowView.findViewById(R.id.file_layout);

        holder.File_N.setText(fileNamesList.get(i));

        return rowView;
    }
}
