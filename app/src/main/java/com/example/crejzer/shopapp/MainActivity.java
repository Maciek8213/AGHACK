//package com.example.crejzer.shopapp;
//
//import android.app.Activity;
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        TextView tv = (TextView)findViewById(R.id.nazwa);
//
//        SklepFactory sklep_factory = new SklepFactory(this);
//        ProduktFactory pr_fa = new ProduktFactory(this);
//
//        Sklep[] s2 = sklep_factory.getSklepy();
//        int ala = s2.length;
//        for(int i=0; i< ala; i++)
//        {
//            sklep_factory.kasujSklepy(s2[i].getId());
//            pr_fa.kasujProdukty(s2[i].getId());
//        }
//        sklep_factory.dodajSklep("zjebany", 230.0, 540.0);
//        sklep_factory.dodajSklep("zajebisty", 670.0, 440.0);
//        sklep_factory.dodajSklep("ultra", 5.0, 25.0);
//        Sklep[] sklepy = sklep_factory.getSklepy();
//
//
//        pr_fa.dodajProdukt(sklepy[0].getId(),"masło",10,1);
//        pr_fa.dodajProdukt(sklepy[0].getId(),"chleb",2,1);
//        pr_fa.dodajProdukt(sklepy[0].getId(), "mleko", 5, 1);
//        pr_fa.dodajProdukt(sklepy[0].getId(), "kiełbasa", 30, 1);
//        pr_fa.dodajProdukt(sklepy[0].getId(), "baton", 3, 1);
//        sklepy[0].setProdukty(pr_fa.getProduktyBySklepId(sklepy[0].getId()));
//
//        pr_fa.dodajProdukt(sklepy[1].getId(), "masło", 3, 1);
//        pr_fa.dodajProdukt(sklepy[1].getId(), "chleb",5,1);
//        pr_fa.dodajProdukt(sklepy[1].getId(), "mleko", 1, 1);
//        pr_fa.dodajProdukt(sklepy[1].getId(), "kiełbasa", 3, 1);
//        pr_fa.dodajProdukt(sklepy[1].getId(), "baton", 2, 1);
//        sklepy[1].setProdukty(pr_fa.getProduktyBySklepId(sklepy[1].getId()));
//
//        pr_fa.dodajProdukt(sklepy[2].getId(), "masło", 3, 1);
//        pr_fa.dodajProdukt(sklepy[2].getId(),"chleb",15,1);
//        pr_fa.dodajProdukt(sklepy[2].getId(), "mleko", 31, 1);
//        pr_fa.dodajProdukt(sklepy[2].getId(), "kiełbasa", 7, 1);
//        pr_fa.dodajProdukt(sklepy[2].getId(), "baton", 9, 1);
//        sklepy[2].setProdukty(pr_fa.getProduktyBySklepId(sklepy[2].getId()));
//
//        String[] skladniki = {"masło", "mleko", "kiełbasa"};
//
//        SklepController sc = new SklepController(this, skladniki);  //inicjalizacja
//        SklepSumy[] ss = sc.getSklepySumy();                        //pobieranie sklepów z najniższymi ofertami
//
//
//
//
//
//
//        double x =10.3; //pobierane
//        double y =-5.3; //pobierane
//        int odleglosc;
//        for(int i = 0; i < ss.length; i++)
//        {
//            odleglosc = (int)sc.getOdleglosc(x,y, ss[i].getPozX(), ss[i].getPozY());
//            tv.setText(tv.getText()+"\n "+ss[i].getSuma() + " odległość do celu: "+ odleglosc);
//            Produkt[]produkty = ss[i].getProdukty();
//            for()
//                produkty[j].
//        }
//
//
//    }
//
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
//}
