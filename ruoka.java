package com.example.harkkaty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Ruoka extends AppCompatActivity {

    String ruoka;
    String id;
    TextView teksti;
    TextView teksti2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruoka);
        setData();
    }

    public void setData()
    {
        Intent intent2 = getIntent();
        ruoka = intent2.getStringExtra("ruoka");
        id = intent2.getStringExtra("id");
        teksti = (TextView) findViewById(R.id.textView9);
        teksti.setText(ruoka);
        teksti2 = (TextView) findViewById(R.id.textView13);
        kommentit();
    }

    public void kommentit()
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream is = getAssets().open("Kommentit.xml");
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("id" + id);

            Node node;

            for (int i = 0; i < nList.getLength(); i++)
            {
                node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    sb.append(element.getElementsByTagName("nimi").item(0).getTextContent() + "\n");
                    sb.append(element.getElementsByTagName("laatu").item(0).getTextContent() + "\n");
                    sb.append(element.getElementsByTagName("kokemus").item(0).getTextContent() + "\n");
                    sb.append(element.getElementsByTagName("arvio").item(0).getTextContent() + "\n\n");
                }
            }

            teksti2.setText(sb);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLomake(View v)
    {
        Intent intent3 = new Intent(this, Arvostelulomake.class);
        intent3.putExtra("id", id);
        startActivity(intent3);
    }
}
