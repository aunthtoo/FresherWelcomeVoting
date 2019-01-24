package com.aunthtoo.welcomevoting.dialog;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aunthtoo.welcomevoting.R;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.w3c.dom.Text;

/**
 * Created by Aunt Htoo on 1/5/2017.
 */

public class ImgDialog {

    LayoutInflater inflater;
    View view;
    AlertDialog dial;
    Context context;
    TextView title;
    SliderLayout img;
    Button ok;

    public ImgDialog(Context context) {
        this.context = context;

    }

    public void showDialog(String human, String imgname, String imgname2, String imgname3) {
        //dialog creation
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.img_layout, null);

        title = (TextView) view.findViewById(R.id.humaname);
        title.setText(human);

        img = (SliderLayout) view.findViewById(R.id.bigimg);

        //for image slide
        //slideone
        TextSliderView sliderView1 = new TextSliderView(context);
        sliderView1.description("");
        sliderView1.image(context.getResources().getIdentifier("com.aunthtoo.welcomevoting:drawable/" + imgname, null, null));

        //slidetwo
        TextSliderView sliderView2 = new TextSliderView(context);
        sliderView2.description("");
        sliderView2.image(context.getResources().getIdentifier("com.aunthtoo.welcomevoting:drawable/" + imgname2, null, null));

        //slidethree
        TextSliderView sliderView3 = new TextSliderView(context);
        sliderView3.description("");
        sliderView3.image(context.getResources().getIdentifier("com.aunthtoo.welcomevoting:drawable/" + imgname3, null, null));

        img.addSlider(sliderView1);
        img.addSlider(sliderView2);
        img.addSlider(sliderView3);

        img.setDuration(2500);
        img.setPresetTransformer(SliderLayout.Transformer.Stack);

        //for image slide


        ok = (Button) view.findViewById(R.id.hok);


        dial = alert.create();
        dial.setView(view);

        dial.getWindow().getAttributes().windowAnimations = R.style.CustomAnimations_slide;
        // dial.setCancelable(false);

        dial.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial.dismiss();
            }
        });


    }

}
