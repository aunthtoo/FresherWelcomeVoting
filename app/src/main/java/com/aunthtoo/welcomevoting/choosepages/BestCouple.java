package com.aunthtoo.welcomevoting.choosepages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.aunthtoo.welcomevoting.R;
import com.aunthtoo.welcomevoting.dbhandler.BoyDBHandler;
import com.aunthtoo.welcomevoting.dbhandler.GirlDBHandler;
import com.aunthtoo.welcomevoting.model.Selection;
import com.aunthtoo.welcomevoting.selectionadapter.BestCoupleAdapter;
import com.aunthtoo.welcomevoting.selectionadapter.SelectionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aunt Htoo on 12/28/2017.
 */

public class BestCouple extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rcv;
    RelativeLayout r;
    List<Selection> bselectionList = new ArrayList<>();
    List<Selection> gselectionList = new ArrayList<>();

    BestCoupleAdapter adapter;

    GirlDBHandler girlDBHandler;
    BoyDBHandler boyDBHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allking);

        toolbar = (Toolbar) findViewById(R.id.tballking);

        //set action bar

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Best Couple");

        //db testing
        girlDBHandler = new GirlDBHandler(BestCouple.this);
        boyDBHandler = new BoyDBHandler(BestCouple.this);

        bselectionList = boyDBHandler.getCouple();
        gselectionList = girlDBHandler.getAllGirls();

        r = (RelativeLayout) findViewById(R.id.rel);

        rcv = (RecyclerView) findViewById(R.id.recycler_viewking);
        adapter = new BestCoupleAdapter(BestCouple.this, bselectionList, gselectionList, r);

        RecyclerView.LayoutManager rl = new LinearLayoutManager(BestCouple.this);

        rcv.setLayoutManager(rl);

        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();


        if(id==R.id.done) {
            //  Toast.makeText(King.this, "You click done", Toast.LENGTH_SHORT).show();

            finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
