package com.example.crejzer.shopapp;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
    ArrayList<String> codeLearnChapters = new ArrayList<String>(); //Tworzę kontener przechowujący elementy do wyświetlenia na liście
    ArrayAdapter<String> listViewAdapter = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ITEAM);
        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.edit);
        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, codeLearnChapters); //tworzę adapter
        final ListView listView = (ListView)findViewById(R.id.listView); //dodaję element opisany w xmlu
        listView.setAdapter(listViewAdapter);//wyświetlam adapter
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        GradientDrawable roundedRectangle = (GradientDrawable) this.getResources().getDrawable(R.drawable.rounded_rectangle);
        Button button = (Button) findViewById(R.id.submit);

        button.setText("OK");
        button.setBackgroundDrawable(roundedRectangle);

        Button button2 = (Button) findViewById(R.id.usun);

        button2.setText("USUN");
        button2.setBackgroundDrawable(roundedRectangle);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listViewAdapter.add(textView.getText().toString());
                textView.setText("");

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
        intent.putExtra("towary",codeLearnChapters);
        setResult(222, intent);
        finish();
    }

    public void usun(View view) {

        listViewAdapter.clear();
    }
}
