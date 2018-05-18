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

public class AllFiles extends AppCompatActivity {
    private ArrayList<File> fileList;
    private ArrayList<String> fileNamesList;

    int MyVersion = Build.VERSION.SDK_INT;

    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_files);

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

                fileList.add(file);
                fileNamesList.add(file.getName().toString());

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
                if (fileNamesList.get(i).contains(".doc") || fileNamesList.get(i).contains(".docx")) {
                    // Word document
                    intent.setDataAndType(uri, "application/msword");
                } else if(fileNamesList.get(i).contains(".pdf")) {
                    // PDF file
                    intent.setDataAndType(uri, "application/pdf");
                } else if(fileNamesList.get(i).contains(".ppt") || fileNamesList.get(i).contains(".pptx")) {
                    // Powerpoint file
                    intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                } else if(fileNamesList.get(i).contains(".xls") || fileNamesList.get(i).contains(".xlsx")) {
                    // Excel file
                    intent.setDataAndType(uri, "application/vnd.ms-excel");
                } else if(fileNamesList.get(i).contains(".zip") || fileNamesList.get(i).contains(".rar")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "application/x-wav");
                } else if(fileNamesList.get(i).contains(".rtf")) {
                    // RTF file
                    intent.setDataAndType(uri, "application/rtf");
                } else if(fileNamesList.get(i).contains(".wav") || fileNamesList.get(i).contains(".mp3")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "audio/x-wav");
                } else if(fileNamesList.get(i).contains(".gif")) {
                    // GIF file
                    intent.setDataAndType(uri, "image/gif");
                } else if(fileNamesList.get(i).contains(".jpg") || fileNamesList.get(i).contains(".jpeg") || fileNamesList.get(i).contains(".png")) {
                    // JPG file
                    intent.setDataAndType(uri, "image/jpeg");
                } else if(fileNamesList.get(i).contains(".txt")) {
                    // Text file
                    intent.setDataAndType(uri, "text/plain");
                } else if(fileNamesList.get(i).contains(".3gp") || fileNamesList.get(i).contains(".mpg") || fileNamesList.get(i).contains(".mpeg") || fileNamesList.get(i).contains(".mpe") || fileNamesList.get(i).contains(".mp4") || fileNamesList.get(i).contains(".avi")) {
                    // Video files
                    intent.setDataAndType(uri, "video/*");
                } else {
                    //if you want you can also define the intent type for any other file

                    //additionally use else clause below, to manage other unknown extensions
                    //in this case, Android will show all applications installed on the device
                    //so you can choose which application to use
                    intent.setDataAndType(uri, "*/*");
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);




            }
        })


        ;

    }
}