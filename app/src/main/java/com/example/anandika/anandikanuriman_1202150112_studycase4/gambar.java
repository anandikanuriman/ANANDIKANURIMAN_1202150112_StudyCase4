package com.example.anandika.anandikanuriman_1202150112_studycase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class gambar extends AppCompatActivity {
    //mendeklarasikan variabel yang dibutuhkan
    ImageView iv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);
        setTitle("AsyncTask"); //set title pada tampilan

        //memanggil variabel yang ada pada layout
        iv = (ImageView)findViewById(R.id.picture);
        et = (EditText)findViewById(R.id.link);
    }


    public void cari(View view) {
        //loading gambar dari internet ke imageview
        Picasso.with(gambar.this).load(et.getText().toString())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }
}
