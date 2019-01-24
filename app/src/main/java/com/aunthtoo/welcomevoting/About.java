package com.aunthtoo.welcomevoting;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Aunt Htoo on 1/9/2017.
 */

public class About extends AppCompatActivity implements View.OnClickListener{

    Button ahaFb,ahaPh,k3Fb,k3Ph;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

getSupportActionBar().setTitle("About");
        ahaFb=(Button)findViewById(R.id.ahaFb);
        ahaFb.setOnClickListener(this);

        ahaPh=(Button)findViewById(R.id.ahaPh);
        ahaPh.setOnClickListener(this);

        k3Fb=(Button)findViewById(R.id.k3Fb);
        k3Fb.setOnClickListener(this);

        k3Ph=(Button)findViewById(R.id.k3Ph);
        k3Ph.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ahaFb:
                startActivity(getOpenFacebook(About.this,"fb://profile/100008563238909","http://www.facebook.com/aunthtooaung.mmt"));

                break;

            case R.id.ahaPh:

                callPhone("09458676496");

                break;

            case R.id.k3Fb:
                startActivity(getOpenFacebook(About.this,"fb://profile/100010839391997","http://www.facebook.com/profile.php?id=100010839391997"));


                break;

            case R.id.k3Ph:

                callPhone("09423696548");

                break;

        }
    }

    public Intent getOpenFacebook(Context con, String id, String link)
    {
        try{

            con.getPackageManager().getPackageInfo("com.facebook.katana",0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse(id));
        }
        catch (Exception e)
        {
            return new Intent(Intent.ACTION_VIEW,Uri.parse(link));
        }
    }

    public void callPhone(String ph)
    {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+ph));

        if (ActivityCompat.checkSelfPermission(About.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startActivity(phoneIntent);
    }
}
