package com.example.downloadjava;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, Boolean> {
    private WeakReference<Context> weakContext;
    private long begin;

    public DownloadTask(WeakReference<Context> context) {
        this.weakContext = context;
    }

    protected Boolean doInBackground(String... urls) {
        begin = System.nanoTime();
        try {
            URL u = new URL("https://devdactic.com/html/5-simple-hacks-LBT.pdf");
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            File sdCard = Environment.getExternalStorageDirectory();// storage/emulated/0
            File file = new File(sdCard, "/Download/downloadJava.pdf");
            FileOutputStream f = new FileOutputStream(file);

            InputStream in = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ( (len1 = in.read(buffer)) > 0 ) {
                f.write(buffer,0, len1);
            }
            f.close();
            return Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return Boolean.FALSE;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        String resultMsg = result ? "Success" : "Fail";
        Integer duration = Toast.LENGTH_SHORT;

        long difference = (System.nanoTime() - begin)/1000000;

        String message = resultMsg + " in " + difference + "ms";

        Toast toast = Toast.makeText(this.weakContext.get(), message, duration);
        toast.show();
    }
}
