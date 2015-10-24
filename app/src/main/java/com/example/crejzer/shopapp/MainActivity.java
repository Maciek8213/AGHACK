package com.example.crejzer.shopapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        TextView tv = (TextView)findViewById(R.id.nazwa);
//
//        ZarzadcaBazy zb = new ZarzadcaBazy(this);
//        /*zb.dodajProdukt("dupa","zbita");
//        zb.dodajProdukt("Maciek","google.pl");
//        zb.dodajProdukt("Dudson","cwel");*/
//        Cursor k = zb.dajWszystkie();
//        String tekst ="";
//        while(k.moveToNext())
//        {
//            int id = k.getInt(0);
//            String nazwa = k.getString(1);
//            String opis = k.getString(2);
//            tekst +="\n"+nazwa+" "+opis;
//
//        }
//        tv.setText(tekst);
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
