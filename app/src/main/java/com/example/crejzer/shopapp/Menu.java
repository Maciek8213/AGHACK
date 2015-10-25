package com.example.crejzer.shopapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Crejzer on 2015-10-24.
 */
public class Menu extends FragmentActivity{

    private View view;

    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> listViewAdapter = null;
    ListView listView = null;

    AutoCompleteTextView textView = null;
    ArrayAdapter<String> adapter = null;
    //wg ceny 1 wg odleglosci 2
    int result = 0;

    public void createListView(){

        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list); //tworzę adapter
        listView = (ListView)findViewById(R.id.listView); //dodaję element opisany w xmlu
        listView.setAdapter(listViewAdapter);//wyświetlam adapter
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public void createButton(){

        Button button = (Button) findViewById(R.id.submit);

        StateListDrawable roundedRectangle = (StateListDrawable) this.getResources().getDrawable(R.drawable.button_selector);
        button.setText("Znajdz Sklep");
        button.setBackgroundDrawable(roundedRectangle);

        Button button2 = (Button) findViewById(R.id.usun);

        StateListDrawable roundedRectangle1 = (StateListDrawable) this.getResources().getDrawable(R.drawable.button_selector);
        button2.setText("Usun");
        button2.setBackgroundDrawable(roundedRectangle1);

        Button button3 = (Button) findViewById(R.id.dialog);

        StateListDrawable roundedRectangle2 = (StateListDrawable) this.getResources().getDrawable(R.drawable.button_selector);
        button3.setText("Filtruj");
        button3.setBackgroundDrawable(roundedRectangle2);
    }

    public void createTextView(){
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ITEAM);
        textView = (AutoCompleteTextView)
                findViewById(R.id.textView);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Button buttonUsun = (Button) findViewById(R.id.usun);
                listViewAdapter.add(textView.getText().toString());
                buttonUsun.setVisibility(View.VISIBLE);
                textView.setText("");
            }
        });
        textView.setAdapter(adapter);
    }
    private static final String[] ITEAM = new String[] {"Chleb","Maslo","Piwo","Wódka","Szynka/100gg","Ser żółty/150","Jajka/10szt","Sok/1l","Cola/2l","Banany/1kg","Jabłka","Ziemniaki/2.5kg","Cebula/1kg","Pasztet","Makaron"};

    public void setIteam(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Toast.makeText(this, "Kilknij na wyszukany produkt aby go dodac do listy", Toast.LENGTH_LONG).show();

        createListView();
        createButton();
        setIteam();
        createTextView();

    }

    public void sendToMap(View view) {
        this.view = view;
        Intent intent = getIntent();
        intent.putExtra("towary", list);
        Log.d("jakis", String.valueOf(result));
        if(listView.getAdapter().getCount() > 0) {
            if(result==2) {
                setResult(222, intent);
                finish();
            } else  if (result == 1){
                setResult(111, intent);
                finish();
            }
        }
    }

    public void usunElement(View view) {

        for (int i = 0; i < list.size(); i++){
            if (listView.isItemChecked(i)) {
                Button buttonUsun = (Button) findViewById(R.id.usun);
                listViewAdapter.remove(listViewAdapter.getItem(i));
                if(listViewAdapter.getCount() == 0) {
                    buttonUsun.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void buildDialog(Context context) {
        Log.d("TAG", "buildDialog() called with: " + "");
        AlertDialog.Builder dialog  = new AlertDialog.Builder(context)
                .setTitle("Filtruj wyniki:")
                .setPositiveButton("Wg najlepszej ceny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = 1;
                        return;
                    }
                }).setNegativeButton("Wg odleglosci", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = 2;
                        return;
                    }
                });
        dialog.show();
    }

    public void showDialog(View view) {
        buildDialog(this);
    }
}
