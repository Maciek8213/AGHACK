package com.example.crejzer.shopapp;

import android.content.Intent;
//import android.graphics.drawable.GradientDrawable;
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

    public void createListView(){

        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list); //tworzę adapter
        listView = (ListView)findViewById(R.id.listView); //dodaję element opisany w xmlu
        listView.setAdapter(listViewAdapter);//wyświetlam adapter
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public void createButton(){

        Button button = (Button) findViewById(R.id.submit);

        StateListDrawable roundedRectangle = (StateListDrawable) this.getResources().getDrawable(R.drawable.button_selector);
        button.setText("OK");
        button.setBackgroundDrawable(roundedRectangle);

        Button button2 = (Button) findViewById(R.id.usun);

        StateListDrawable roundedRectangle1 = (StateListDrawable) this.getResources().getDrawable(R.drawable.button_selector);
        button2.setText("USUN");
        button2.setBackgroundDrawable(roundedRectangle1);
    }

    public void createTextView(){
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ITEAM);
        textView = (AutoCompleteTextView)
                findViewById(R.id.textView);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listViewAdapter.add(textView.getText().toString());
                textView.setText("");

            }
        });
        textView.setAdapter(adapter);
    }
    private static final String[] ITEAM = new String[] {"Ania","Jest","Nie Jest","Fajna"};

    public void setIteam(){


    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        createListView();
        createButton();
        setIteam();
        createTextView();

    }

    public void sendToMap(View view) {
        this.view = view;
        Intent intent = getIntent();
        intent.putExtra("towary", list);
        if(listView.getAdapter().getCount() > 0) {
            setResult(222, intent);
            finish();
        }
    }

    public void usunElement(View view) {

        for (int i = 0; i < list.size(); i++){
            if (listView.isItemChecked(i)) {
                listViewAdapter.remove(listViewAdapter.getItem(i));
            }
        }

    }
}
