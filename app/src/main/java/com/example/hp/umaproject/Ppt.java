package com.example.hp.umaproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class Ppt extends AppCompatActivity {
    private ArrayList<File> fileList;
    private ArrayList<String> fileNamesList;

    int MyVersion = Build.VERSION.SDK_INT;

    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ppt);

        fileList = new ArrayList<File>();
        listView = (ListView) findViewById(R.id.list);
        fileNamesList = new ArrayList<String>();

        File root = new File(Environment.getExternalStorageDirectory() + "/Download/");
        ListDir(root);
    }


    void ListDir(File f) {
        final File[] files = f.listFiles();
        fileList.clear();
        for (File file : files) {
            if(file.getName().endsWith(".ppt")){
                fileList.add(file);
                fileNamesList.add(file.getName().toString());}

        }
        Log.d("message", fileNamesList + "");

        FileAdapter directoryList = new FileAdapter(this,fileNamesList,fileList);
        listView.setAdapter(directoryList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String filePath = fileList.get(i).getAbsolutePath();
                File myFile = new File(filePath);
                Uri uri = Uri.fromFile(myFile);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);




            }
        })


        ;

    }
}