package com.example.kazajii.local;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class ListPharmacisActivity extends AppCompatActivity {

    private RecyclerView pharmacie;
    private List<Pharmacie> pharmacies;
    private PharmacieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pharmacis);

        pharmacie = findViewById(R.id.List_Pharmacis);


        pharmacies = new ArrayList<>();
        pharmacies.add(new Pharmacie("Pharmacie De LOGPOM","Ouvert de LUNDI a DIMANCHE CONTACT:6940334556",9.770459,4.082797,"Douala","AKWA"));
        pharmacies.add(new Pharmacie("Pharmacie MANENGOUMBA ","Ouvert tous les jours 8h 22h CONTACT:2 33 06 42 44",9.764889,4.087271,"Nkonsamba","Patenon"));
        pharmacies.add(new Pharmacie("Pharmacie De YAOUNDE","Ouvert de 8h a 16h CONTACT:233475555",9.755205,4.084965,"Yaounde","Mvan"));
        pharmacies.add(new Pharmacie("Pharmacie De KOTTO","Ouvert de 8h a 16h CONTACT:697041056",9.749494 ,4.092272,"Yaounde","Mvan"));
        pharmacies.add(new Pharmacie("Pharmacie SACRE COEUR","Ouvert de 8h a 16h CONTACT:non renseigner",9.759854 ,4.065940,"Yaounde","Mvan"));
        pharmacies.add(new Pharmacie("Pharmacie De PK 11","Ouvert de 8h a 16h CONTACT:6 99 12 24 49",9.776792 ,4.051216,"Douala","AKWA"));
        pharmacies.add(new Pharmacie("Pharmacie De LA REPUBLIQUE PK 14","Ouvert de 8h a 16h CONTACT:6 96 23 57 75",9.741312 ,4.090018,"Douala","AKWA"));
        pharmacies.add(new Pharmacie("Pharmacie De PK 8","Ouvert de 07h30 a 21h CONTACT:2 33 37 64 96",9.753631 ,4.046282,"Nkonsamba","Patenon"));
        pharmacies.add(new Pharmacie("Pharmacie De L'AEROPORT","Ouvert de 8h a 21h CONTACT:2 33 42 28 76",9.741312 ,4.090018,"Douala","Bali"));
        pharmacies.add(new Pharmacie("Pharmacie OLYMPIQUE DE NDOKOTTI","Ouvert de 8h a 16h CONTACT:2 33 37 02 97",9.741312 ,4.090018,"Douala","Bonapriso"));
        pharmacies.add(new Pharmacie("Pharmacie Du RAIL","Ouvert de 07h30 a 20h CONTACT:2 33 42 66 18",9.711910 ,4.037775,"Douala","Bonaberie"));


        adapter = new PharmacieAdapter(pharmacies,ListPharmacisActivity.this);
        pharmacie.setLayoutManager(new LinearLayoutManager(ListPharmacisActivity.this));
        pharmacie.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater  inflater =  getMenuInflater();
        inflater.inflate(R.menu.pharmacie_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;

    }
}
