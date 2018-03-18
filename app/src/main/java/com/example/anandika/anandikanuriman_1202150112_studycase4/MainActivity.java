package com.example.anandika.anandikanuriman_1202150112_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.listMh);
    }

    public void mulai(View view) {
        new getData(lv).execute(); //proses asynctask dimulai
    }
    //subclass assynctask
    class getData extends AsyncTask<String, Integer, String> {
        ListView lv;
        ArrayAdapter adapter;
        ArrayList<String> listNama;
        ProgressDialog dialog;

        //constructor saat asynctask diinisialisasi
        public getData(ListView listMhs) {
            this.lv = listMhs;
            dialog = new ProgressDialog(MainActivity.this);
            listNama = new ArrayList<>();
        }

        //method ketika proses asynctask belum dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //menampilkan proses dialog
            dialog.setTitle("Loading Data");
            dialog.setIndeterminate(true);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(true);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                    getData.this.cancel(true);
                }
            });

            dialog.show();
        }

        //method saat proses asynctask dijalankan
        @Override
        protected String doInBackground(String... strings) {
            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listNama); //membuat adapter

            //menyimpan array pada sebuah variabel
            String[] mhs = getResources().getStringArray(R.array.nama);
            //perulangan untuk menyimpan array
            for (int a = 0; a < mhs.length; a++) {
                final long persen = 100L * a / mhs.length;
                try {
                    Runnable change = new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage((int) persen+"%");
                        }
                    };
                    runOnUiThread(change);
                    Thread.sleep(300);
                    listNama.add(mhs[a]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //method sesudah asynctask sudah dijalankan
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            lv.setAdapter(adapter);
            dialog.dismiss();
        }
    }
}
