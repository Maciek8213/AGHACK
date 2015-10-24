package com.example.crejzer.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by Crejzer on 2015-10-24.
 */
public class Menu extends FragmentActivity{

    final ArrayList<String> listaZakupow = new ArrayList<String>();
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ITEAM);
        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.edit);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listaZakupow.add(textView.getText().toString());
                TextView lista = (TextView) findViewById(R.id.list);
                textView.setText("");
                String wywietlListe = "";
                for (int i = 0; i < listaZakupow.size(); i++){
                    Log.d("listaMenu",listaZakupow.get(i));

                    wywietlListe = wywietlListe + listaZakupow.get(i) + "\n";
                    lista.setText(wywietlListe);
                }
            }
        });
        textView.setAdapter(adapter);
    }

    private static final String[] ITEAM = new String[] {
            "LSD","BROWAR","FAJKI"
    };

    public void addList(View view) {
        this.view = view;
        Intent intent = getIntent();
        listaZakupow.add(listaZakupow.size(), "ok");
        intent.putExtra("towary",listaZakupow);
        setResult(222, intent);
        finish();
    }
}
