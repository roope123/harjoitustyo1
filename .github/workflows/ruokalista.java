package com.example.harkkaty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.XmlSerializer;

import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

public class Arvostelulomake extends AppCompatActivity {

    String id;
    EditText nimi;
    EditText puhnro;
    EditText sposti;
    EditText laatu;
    EditText kokemus;
    EditText arvio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvostelulomake);
        setData();
    }

    public void setData() {
        Intent intent3 = getIntent();
        id = intent3.getStringExtra("id");
        nimi = (EditText) findViewById(R.id.editText);
        puhnro = (EditText) findViewById(R.id.editText2);
        sposti = (EditText) findViewById(R.id.editText3);
        laatu = (EditText) findViewById(R.id.editText4);
        kokemus = (EditText) findViewById(R.id.editText5);
        arvio = (EditText) findViewById(R.id.editText6);
    }

    public void laheta(View v) {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "lista");
            serializer.startTag("", "id" + id);

            serializer.startTag("", "nimi");
            serializer.text(nimi.getText().toString());
            serializer.endTag("", "nimi");

            serializer.startTag("", "laatu");
            serializer.text(laatu.getText().toString());
            serializer.endTag("", "laatu");

            serializer.startTag("", "kokemus");
            serializer.text(kokemus.getText().toString());
            serializer.endTag("", "kokemus");

            serializer.startTag("", "arvio");
            serializer.text(arvio.getText().toString());
            serializer.endTag("", "arvio");

            serializer.endTag("", "id" + id);
            serializer.endTag("", "lista");
            serializer.endDocument();
            String result = writer.toString();


            FileOutputStream fos = openFileOutput("Kommentit.xml", Context.MODE_PRIVATE);
            fos.write(result.getBytes(), 0, result.length());
            fos.close();

            Toast toast = Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT);
            toast.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
