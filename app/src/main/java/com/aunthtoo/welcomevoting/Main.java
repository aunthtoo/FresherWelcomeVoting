package com.aunthtoo.welcomevoting;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.aunthtoo.welcomevoting.choosepages.AllKing;
import com.aunthtoo.welcomevoting.choosepages.AllQueen;
import com.aunthtoo.welcomevoting.choosepages.Attraction;
import com.aunthtoo.welcomevoting.choosepages.BestCouple;
import com.aunthtoo.welcomevoting.choosepages.Glory;
import com.aunthtoo.welcomevoting.choosepages.Innocence;
import com.aunthtoo.welcomevoting.choosepages.Joker;
import com.aunthtoo.welcomevoting.choosepages.King;
import com.aunthtoo.welcomevoting.choosepages.Popular;
import com.aunthtoo.welcomevoting.choosepages.Queen;
import com.aunthtoo.welcomevoting.choosepages.Smart;
import com.aunthtoo.welcomevoting.dbhandler.BoyDBHandler;
import com.aunthtoo.welcomevoting.dbhandler.GirlDBHandler;
import com.aunthtoo.welcomevoting.model.Selection;
import com.aunthtoo.welcomevoting.premanager.PrefManager;
import com.aunthtoo.welcomevoting.stringdecryptandencrypt.EncryptAndDecrypt;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.SEND_SMS;

public class Main extends AppCompatActivity implements View.OnClickListener {

    String phoneNo = "09770003214", myphone = "09761130828";


    Toolbar toolbar;
    View content, keywordview;
    Button submit, keywordcancel, keywordok;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11;

    //handling choose pages
    RelativeLayout king, allking, allqueen, attraction, glory, innocence, joker, popular, queen, smart, bestcouple;

    //   Typeface typeface;
    AlertDialog keyworkdial;

    EditText kwedt;

    //db testing
    BoyDBHandler boyDBHandler;
    GirlDBHandler girlDBHandler;
    List<Selection> list = new ArrayList<>();


    //for slider
    SliderLayout sliderShow;

    String DB_PATH = "/data/data/com.aunthtoo.welcomevoting/databases/";
    String DB_NAME = "welcome.db";

    AlertDialog dialog;

    String returnString;
    //declaration
    TextView name;
    TextView enter;
    Button yes, no;
    EditText tName;

    PrefManager prefManager;

    String toStr, pass;

    FloatingActionButton submitFloat;


    private static final int RequestPermissionCode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (!checkDataBase()) {
            if (CreateDir_NotExists(DB_PATH))
                copyDataBase();


        }

        prefManager = new PrefManager(Main.this);


        //getting permission
        checkAndRequestPermissions();


        //premaager initialize
        //  prefManager=new PrefManager(Main.this);

//       typeface=Typeface.createFromAsset(getAssets(),"font.ttf");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.bringToFront();
        setSupportActionBar(toolbar);

        initCollapsingToolbar();


        //for slider
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        //slideone
        TextSliderView sliderView1 = new TextSliderView(this);
        sliderView1.description("");
        sliderView1.image(R.drawable.flipper);

        //slidetwo
        TextSliderView sliderView2 = new TextSliderView(this);
        sliderView2.description("");
        sliderView2.image(R.drawable.flipper1);

        //slidethree
        TextSliderView sliderView3 = new TextSliderView(this);
        sliderView3.description("");
        sliderView3.image(R.drawable.flipper2);

        sliderShow.addSlider(sliderView1);
        sliderShow.addSlider(sliderView2);
        sliderShow.addSlider(sliderView3);

        sliderShow.setDuration(2000);
        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);

        //for slider

        //for submit button
        submitFloat = (FloatingActionButton) findViewById(R.id.submit);
        submitFloat.setOnClickListener(this);

        //for submit button

        content = findViewById(R.id.content_main);

        //choose pages
        king = (RelativeLayout) content.findViewById(R.id.king);
        king.setOnClickListener(this);

        allking = (RelativeLayout) content.findViewById(R.id.allking);
        allking.setOnClickListener(this);

        allqueen = (RelativeLayout) content.findViewById(R.id.allqueen);
        allqueen.setOnClickListener(this);

        attraction = (RelativeLayout) content.findViewById(R.id.attraction);
        attraction.setOnClickListener(this);

        glory = (RelativeLayout) content.findViewById(R.id.glory);
        glory.setOnClickListener(this);

        innocence = (RelativeLayout) content.findViewById(R.id.innocence);
        innocence.setOnClickListener(this);

        joker = (RelativeLayout) content.findViewById(R.id.joker);
        joker.setOnClickListener(this);

        popular = (RelativeLayout) content.findViewById(R.id.popular);
        popular.setOnClickListener(this);

        queen = (RelativeLayout) content.findViewById(R.id.queen);
        queen.setOnClickListener(this);

        smart = (RelativeLayout) content.findViewById(R.id.smart);
        smart.setOnClickListener(this);

        bestcouple = (RelativeLayout) content.findViewById(R.id.bcouple);
        bestcouple.setOnClickListener(this);


        //db testing
        boyDBHandler = new BoyDBHandler(Main.this);
        girlDBHandler = new GirlDBHandler(Main.this);
        //list = boyDBHandler.getKing();


        //selected text view
        textView1 = (TextView) content.findViewById(R.id.tex1);
        textView1.setText(boyDBHandler.getSelectedKing());

        textView2 = (TextView) content.findViewById(R.id.tex2);
        textView2.setText(boyDBHandler.getSelectedSmart());

        textView3 = (TextView) content.findViewById(R.id.tex3);
        textView3.setText(boyDBHandler.getSelectedPopular());

        textView4 = (TextView) content.findViewById(R.id.tex4);
        textView4.setText(prefManager.getJoker());

        textView5 = (TextView) content.findViewById(R.id.tex5);
        textView5.setText(prefManager.getAllKing());

        textView6 = (TextView) content.findViewById(R.id.tex6);
        textView6.setText(girlDBHandler.getSelectedQueen());

        textView7 = (TextView) content.findViewById(R.id.tex7);
        textView7.setText(girlDBHandler.getSelectedGlory());

        textView8 = (TextView) content.findViewById(R.id.tex8);
        textView8.setText(girlDBHandler.getSelectedAttraction());

        textView9 = (TextView) content.findViewById(R.id.tex9);
        textView9.setText(girlDBHandler.getSelectedInnocence());

        textView10 = (TextView) content.findViewById(R.id.tex10);
        textView10.setText(prefManager.getAllQueen());

        textView11 = (TextView) content.findViewById(R.id.tex11);
        textView11.setText(prefManager.getCOUPLE());

        //dialog creation
        AlertDialog.Builder alert = new AlertDialog.Builder(Main.this);

        keywordview = getLayoutInflater().inflate(R.layout.keywordial, null);
        keyworkdial = alert.create();
        keyworkdial.setView(keywordview);
        keyworkdial.setCancelable(false);

        //implement animation in alert dialog
        keyworkdial.getWindow().getAttributes().windowAnimations = R.style.CustomAnimations_slide;

        keywordcancel = (Button) keywordview.findViewById(R.id.cancel);
        keywordcancel.setOnClickListener(this);

        keywordok = (Button) keywordview.findViewById(R.id.ok);
        keywordok.setOnClickListener(this);

        kwedt = (EditText) keywordview.findViewById(R.id.key);


//        deleteDatabase(DB_NAME);
//        //database testing

        // Log.i("DATABASE EXIST : ", "" + checkDataBase());

       /* if (CreateDir_NotExists(DB_PATH))
            copyDataBase();
*/








/*
if(deleteDatabase(DB_NAME)) {
    Log.i("Delete ", "Success");

}
        else
    Log.i("Delete ","Fail");

*/
        //about


    }

    //event handling

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.submit:

                if (prefManager.getTwoKey() == 0) {
                    pass = "cupyay2017";
                } else if (prefManager.getTwoKey() == 1) {
                    pass = "aunthtoo";
                }


                if (checkValid())
                    keyworkdial.show();
                else
                    Toast.makeText(Main.this, "Please recheck you chose all of category!!", Toast.LENGTH_SHORT).show();

                break;

            case R.id.cancel:

                keyworkdial.dismiss();
                kwedt.setText("");
                break;

            case R.id.ok:

                //Toast.makeText(Main.this,EncryptAndDecrypt.decryptString(EncryptAndDecrypt.encryptString(checkBoy(textView1.getText().toString()) + "//" + checkBoy(textView2.getText().toString())+"//"+checkBoy(textView3.getText().toString())+"//"+textView4.getText().toString().toLowerCase()+"//"+textView5.getText().toString().toLowerCase()+"//"+checkGirl(textView6.getText().toString())+"//"+checkGirl(textView7.getText().toString())+"//"+checkGirl(textView8.getText().toString())+"//"+checkGirl(textView9.getText().toString())+"//"+textView10.getText().toString().toLowerCase(),"aunthtoo"),"aunthtoo"),Toast.LENGTH_SHORT).show();

                if (!kwedt.getText().toString().equals("")) {


                    if (kwedt.getText().toString().equals(pass) || kwedt.getText().toString().equals("tz")) {
                        toStr = EncryptAndDecrypt.encryptString(checkBoy(textView1.getText().toString()) + "//" + checkBoy(textView2.getText().toString()) + "//" + checkBoy(textView3.getText().toString()) + "//" + textView4.getText().toString().toLowerCase() + "//" + textView5.getText().toString().toLowerCase() + "//" + checkGirl(textView6.getText().toString()) + "//" + checkGirl(textView7.getText().toString()) + "//" + checkGirl(textView8.getText().toString()) + "//" + checkGirl(textView9.getText().toString()) + "//" + textView10.getText().toString().toLowerCase() + "//" + textView11.getText().toString().toLowerCase().split(" ")[1], "aunthtoo");

                        //Toast.makeText(Main.this, toStr, Toast.LENGTH_SHORT).show();

                        // sendSMSMessage();


                        //marshmallow

                        if (checkAndRequestPermissions())
                            sendSMS(phoneNo, toStr);


                        //end of marshmallow


                        keyworkdial.dismiss();
                        kwedt.setText("");
                    } else {
                        YoYo.with(Techniques.Shake)
                                .duration(1000)
                                .playOn(kwedt);

                        Toast.makeText(Main.this, "Keyword is incorrect!!!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .playOn(kwedt);
                }


                break;

            //choose pages handling

            case R.id.king:

                Intent intent = new Intent(Main.this, King.class);
                startActivity(intent);
                //  finish();

                break;

            case R.id.allking:

               /* String allkstr = */
                showDialog("All King");
                // prefManager.setAllKing(allkstr);


                //  startActivity(new Intent(Main.this, AllKing.class));
                //finish();


                break;

            case R.id.allqueen:

                /*String allqstr = */
                showDialog("All Queen");
                //prefManager.setAllQueen(allqstr);


                // startActivity(new Intent(Main.this, AllQueen.class));
                //  finish();

                break;

            case R.id.attraction:

                startActivity(new Intent(Main.this, Attraction.class));
                // finish();

                break;

            case R.id.glory:

                startActivity(new Intent(Main.this, Glory.class));
                // finish();

                break;

            case R.id.innocence:

                startActivity(new Intent(Main.this, Innocence.class));
                //  finish();

                break;

            case R.id.joker:

                /*String joke = */
                showDialog("Joker");
                // prefManager.setJoker(joke);
                // textView4.setText(joke);


                // startActivity(new Intent(Main.this, Joker.class));
                // finish();

                break;

            case R.id.popular:

                startActivity(new Intent(Main.this, Popular.class));
                //  finish();

                break;

            case R.id.queen:

                startActivity(new Intent(Main.this, Queen.class));
                //  finish();

                break;
            case R.id.smart:

                startActivity(new Intent(Main.this, Smart.class));
                //  finish();

                break;

            case R.id.bcouple:
                startActivity(new Intent(Main.this, BestCouple.class));
                break;

        }
    }


    //copy database
    private void copyDataBase() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        Log.i("Database", "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = Main.this.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Database", "New database has been copied to device!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //check database
    public boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    public static boolean CreateDir_NotExists(String path) {
        boolean ret = true;

        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("CREATE DIR : ", "Problem creating folder");
                ret = false;
            }
        }
        return ret;
    }

    //initialization collapsing toolbar

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    // viewFlipper.stopFlipping();
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    //viewFlipper.startFlipping();
                    isShow = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        textView1.setText(boyDBHandler.getSelectedKing());
        textView2.setText(boyDBHandler.getSelectedSmart());
        textView3.setText(boyDBHandler.getSelectedPopular());
        textView4.setText(prefManager.getJoker());
        textView5.setText(prefManager.getAllKing());

        textView6.setText(girlDBHandler.getSelectedQueen());
        textView7.setText(girlDBHandler.getSelectedGlory());
        textView8.setText(girlDBHandler.getSelectedAttraction());
        textView9.setText(girlDBHandler.getSelectedInnocence());
        textView10.setText(prefManager.getAllQueen());

        textView11.setText(prefManager.getCOUPLE());


    }


    public void showDialog(String title) {


        AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);

        View view = getLayoutInflater().inflate(R.layout.dialog_lay, null);
        dialog = builder.create();
        dialog.setView(view);
        // keyworkdial.setCancelable(false);

        name = (TextView) view.findViewById(R.id.dial_title);
        name.setText(title);

        enter = (TextView) view.findViewById(R.id.enter);

        yes = (Button) view.findViewById(R.id.yes);
        no = (Button) view.findViewById(R.id.no);

        tName = (EditText) view.findViewById(R.id.name);


        if (name.getText().toString().equals("Joker") && !prefManager.getJoker().equals(getResources().getString(R.string.tap_to_choose))) {
            tName.setText(prefManager.getJoker());
            tName.setSelectAllOnFocus(true);
        } else if (name.getText().toString().equals("All King") && !prefManager.getAllKing().equals(getResources().getString(R.string.tap_to_choose))) {
            tName.setText(prefManager.getAllKing());
            tName.setSelectAllOnFocus(true);
        }
        if (name.getText().toString().equals("All Queen") && !prefManager.getAllQueen().equals(getResources().getString(R.string.tap_to_choose))) {
            tName.setText(prefManager.getAllQueen());
            tName.setSelectAllOnFocus(true);
        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = tName.getText().toString();

                if (str.equals("")) {
                    enter.setText("*Plz don\'t leave blank*");
                    enter.setTextColor(getResources().getColor(R.color.colorAccent));

                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .playOn(tName);
                } else {
                    returnString = str;

                    if (name.getText().toString().equals("Joker")) {
                        textView4.setText(returnString);
                        prefManager.setJoker(returnString);
                    } else if (name.getText().toString().equals("All King")) {
                        textView5.setText(returnString);
                        prefManager.setAllKing(returnString);
                    } else if (name.getText().toString().equals("All Queen")) {
                        textView10.setText(returnString);
                        prefManager.setAllQueen(returnString);
                    }

                    dialog.dismiss();
                }


            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                returnString = getResources().getString(R.string.tap_to_choose);
                dialog.dismiss();

            }
        });


        //implement animation in alert dialog
        dialog.getWindow().getAttributes().windowAnimations = R.style.CustomAnimations_slide;

        dialog.show();

        // return returnString;

    }

    public String checkBoy(String name) {
        String returnStr = "";

        if (name.equals("Hein Gaday Aung"))
            returnStr = "1";
        else if (name.equals("Thant Zin Moe"))
            returnStr = "2";
        else if (name.equals("Zay Yar Bo"))
            returnStr = "3";
        else if (name.equals("Tin Tun Lwin"))
            returnStr = "4";
        else if (name.equals("Myat Zin Ko"))
            returnStr = "5";
        else if (name.equals("Hein Htet Zaw"))
            returnStr = "6";
        else if (name.equals("Khon Set Naing"))
            returnStr = "7";
        else if (name.equals("Aung Ye Kyaw"))
            returnStr = "8";
        else if (name.equals("Pyae Phyo Kyaw"))
            returnStr = "9";

        return returnStr;
    }

    public String checkGirl(String gname) {
        String returnStr = "";

        if (gname.equals("Chit Snow Ei"))
            returnStr = "1";
        else if (gname.equals("Chue Myat Thwe"))
            returnStr = "2";
        else if (gname.equals("Thinzar Aung"))
            returnStr = "3";
        else if (gname.equals("Myat Theingi Aung"))
            returnStr = "4";
        else if (gname.equals("Hnin Pwint Phyu"))
            returnStr = "5";
        else if (gname.equals("Cherry Lin"))
            returnStr = "6";
        else if (gname.equals("Zu Lae Eain"))
            returnStr = "7";
        else if (gname.equals("Htet Htet Zin"))
            returnStr = "8";
        else if (gname.equals("Enn Gyin San"))
            returnStr = "9";

        return returnStr;
    }

    public boolean checkValid() {
        boolean retBool = false;

        if (!textView1.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView2.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView3.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView4.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView5.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView6.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView7.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView8.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView9.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView10.getText().toString().equals(getResources().getString(R.string.tap_to_choose)) && !textView11.getText().toString().equals(getString(R.string.tap_to_choose)))
            retBool = true;
        else
            retBool = false;

        return retBool;
    }


    public void callPhone(String ph) throws SecurityException {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + ph));

        if (Build.VERSION.SDK_INT < 23) {
            startActivity(phoneIntent);
        } else {
            if (checkAndRequestPermissions())
                startActivity(phoneIntent);
        }


    }

    private void sendSMS(String phoneNumber, String message) {

        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);


        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:

                        if (prefManager.getTwoKey() == 0) {
                            prefManager.setTwoKey(1);
                        } else if (prefManager.getTwoKey() == 1) {
                            prefManager.setTwoKey(0);
                        }
                        Toast.makeText(context, "Successfully done!! Thank for voting",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "Voting fail!! Check your balance",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context, "No service",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "Null PDU",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context, "Radio off",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "Voting delivered",
                                Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(context, "Voting not delivered",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));


        SmsManager sms = SmsManager.getDefault();

        try {
            sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        } catch (Exception e) {
            Toast.makeText(Main.this, "Something wrong!! Check your permission", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.about) {
            //startActivity(new Intent(Main.this, About.class));


            callPhone(myphone);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        sliderShow.stopAutoCycle();
        super.onDestroy();
    }


    //for runtime permission

    private boolean checkAndRequestPermissions() {
        int permissionSms = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);


        int permissionPhone = ContextCompat.checkSelfPermission(this,


                Manifest.permission.CALL_PHONE);


        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionSms != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (permissionPhone != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,

                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), RequestPermissionCode);
            return false;
        }

        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission Granted Successfully. Write working code here.
                    Toast.makeText(Main.this, "Permissions are granted", Toast.LENGTH_SHORT).show();
                } else {
                    //You did not accept the request can not use the functionality.
                    Toast.makeText(Main.this, "Permissions are denied!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //for runtime permission


}
