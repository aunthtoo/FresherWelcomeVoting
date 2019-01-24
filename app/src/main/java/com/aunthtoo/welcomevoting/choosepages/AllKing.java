package com.aunthtoo.welcomevoting.choosepages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.aunthtoo.welcomevoting.Main;
import com.aunthtoo.welcomevoting.R;
import com.aunthtoo.welcomevoting.dbhandler.BoyDBHandler;
import com.aunthtoo.welcomevoting.model.Selection;
import com.aunthtoo.welcomevoting.selectionadapter.SelectionAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aunt Htoo on 12/28/2016.
 */

public class AllKing extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView rcv;
    RelativeLayout r;
    List<Selection> selectionList = new ArrayList<>();
    SelectionAdapter adapter;

    BoyDBHandler boyDBHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allking);

        toolbar=(Toolbar)findViewById(R.id.tballking);

        //set action bar

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All King");

        //db testing
        boyDBHandler = new BoyDBHandler(AllKing.this);
        selectionList = boyDBHandler.getAllKing();

        r = (RelativeLayout) findViewById(R.id.rel);

        rcv = (RecyclerView) findViewById(R.id.recycler_viewking);
        adapter = new SelectionAdapter(AllKing.this, selectionList, r, 5);

        RecyclerView.LayoutManager rl = new LinearLayoutManager(AllKing.this);

        rcv.setLayoutManager(rl);

        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setAdapter(adapter);


    }

   /* @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Main.class));
        finish();
    }*/

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
