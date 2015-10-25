package com.example.crejzer.shopapp;

import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //TextView tv = (TextView)findViewById(R.id.nazwa);

        SklepFactory sklep_factory = new SklepFactory(this);
        ProduktFactory pr_fa = new ProduktFactory(this);
        String [] nazwa = {"Chleb","Maslo","Piwo","Wódka","Szynka/100gg","Ser żółty/150","Jajka/10szt","Sok/1l","Cola/2l","Banany/1kg","Jabłka","Ziemniaki/2.5kg","Cebula/1kg","Pasztet","Makaron"};
        double [] cena_BiedroCena ={3.0,3.70,2.12,20.0,3.5,2.90,5.0,2.80,5.0,2.7,2.10,3,2.20,1.19,1.90};
        double [] cena_KauflandCena ={2.60,4.55,2.45,21,2.5,3.80,4,2.80,5,2.7,2.10,2.20,1.60,1.90};
        double [] cena_LewiatanCena={2.49,4.20,2.30,22,4.7,3.0,4.6,3.10,5.1,2.7,2.20,3.2,2.10,1.45,2.10};
        double [] cena_Zabkacena ={2.29,3.50,2.34,23,5.0,3.0,5.0,3.0,4.70,2.5,2.25,3,2.10,1.39,1.70};
//        for(int i=0; i< ala; i++)
//        {
//            sklep_factory.kasujSklepy(s2[i].getId());
//            pr_fa.kasujProdukty(s2[i].getId());
//        }
        MapsActivity polozenie = new MapsActivity();
//        polozenie.askApi("Biedronka");
        sklep_factory.dodajSklep("Biedronka", 0, 0);
//        polozenie.askApi("Kaufland");
        sklep_factory.dodajSklep("Kaufland", 0, 0);
//        polozenie.askApi("Lewiatan");
        sklep_factory.dodajSklep("Lewiatan", 0, 0);
//        polozenie.askApi("Zabka");
        sklep_factory.dodajSklep("Zabka", 0, 0);
        Sklep[] sklepy = sklep_factory.getSklepy();

        for(int i =0 ; i < nazwa.length; i++)
        {
            pr_fa.dodajProdukt(sklepy[0].getId(),nazwa[i],cena_BiedroCena[i],10);
            pr_fa.dodajProdukt(sklepy[1].getId(),nazwa[i],cena_KauflandCena[i],10);
            pr_fa.dodajProdukt(sklepy[2].getId(),nazwa[i],cena_LewiatanCena[i],10);
            pr_fa.dodajProdukt(sklepy[3].getId(),nazwa[i],cena_Zabkacena[i],10);



        }
        sklepy[0].setProdukty(pr_fa.getProduktyBySklepId(sklepy[0].getId()));
        sklepy[1].setProdukty(pr_fa.getProduktyBySklepId(sklepy[1].getId()));
        sklepy[2].setProdukty(pr_fa.getProduktyBySklepId(sklepy[2].getId()));
        sklepy[3].setProdukty(pr_fa.getProduktyBySklepId(sklepy[3].getId()));
        String[] skladniki = {"masło", "mleko", "kiełbasa"};

        SklepController sc = new SklepController(this, skladniki);  //inicjalizacja
        SklepSumy[] ss = sc.getSklepySumy();                        //pobieranie sklepów z najniższymi ofertami






        double x =10.3; //pobierane
        double y =-5.3; //pobierane
        int odleglosc;
//        for(int i = 0; i < ss.length; i++)
//        {
//            odleglosc = (int)sc.getOdleglosc(x,y, ss[i].getPozX(), ss[i].getPozY());
//            tv.setText(tv.getText()+"\n "+ss[i].getSuma() + " odległość do celu: "+ odleglosc);
//            Produkt[]produkty = ss[i].getProdukty();
//            for()
//                produkty[j].
//        }


    }



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
//
}
