package com.aunthtoo.welcomevoting.selectionadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aunthtoo.welcomevoting.R;
import com.aunthtoo.welcomevoting.choosepages.BestCouple;
import com.aunthtoo.welcomevoting.dbhandler.BoyDBHandler;
import com.aunthtoo.welcomevoting.dbhandler.GirlDBHandler;
import com.aunthtoo.welcomevoting.model.Selection;
import com.aunthtoo.welcomevoting.premanager.PrefManager;

import java.util.List;

/**
 * Created by Aunt Htoo on 12/28/2017.
 */

public class BestCoupleAdapter extends RecyclerView.Adapter<BestCoupleAdapter.BestCoupleViewHolder> {

    private List<Selection> bList, gList;
    private Context context;
    private RelativeLayout r;
    int chkforchoose;
    BoyDBHandler handler;
    GirlDBHandler dbHandler;


    Selection bsel, gsel;
    PrefManager prefManager;


    public class BestCoupleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView cName, cTitle;

        public CardView cardView;
        public Button choose;
        public ImageView bimageView, gimageView;

        public BestCoupleViewHolder(View view) {
            super(view);

            // cardView = (CardView) view.findViewById(R.id.kingr);


            cTitle = (TextView) view.findViewById(R.id.coupletitle);
            cName = (TextView) view.findViewById(R.id.couplename);

            choose = (Button) view.findViewById(R.id.choose);

            bimageView = (ImageView) view.findViewById(R.id.boy);
            gimageView = (ImageView) view.findViewById(R.id.girl);

            handler = new BoyDBHandler(context);
            dbHandler = new GirlDBHandler(context);

            prefManager = new PrefManager(context);

            choose.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.choose:

                    bsel = bList.get(getAdapterPosition());
                    chkforchoose = bsel.getChoose();

                    if (chkforchoose == 0) {

                        handler.setCouple(bsel.getId(), 1);
                        chkforchoose = 1;
                        choose.setText("Selected");
                        prefManager.setCOUPLE("Couple " + bsel.getId());


                    } else {

                        handler.setCouple(bsel.getId(), 0);
                        chkforchoose = 0;
                        choose.setText("Select");

                        prefManager.setCOUPLE(context.getString(R.string.tap_to_choose));
                    }

                    notifyDataSetChanged();

                    context.startActivity(new Intent(context, BestCouple.class));

                    break;


            }
        }
    }

    public BestCoupleAdapter(Context context, List<Selection> bList, List<Selection> gList, RelativeLayout r) {
        this.bList = bList;
        this.gList = gList;
        this.context = context;
        this.r = r;

    }

    @Override
    public BestCoupleAdapter.BestCoupleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View selectionView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couple_item, parent, false);

        return new BestCoupleAdapter.BestCoupleViewHolder(selectionView);
    }

    @Override
    public void onBindViewHolder(BestCoupleViewHolder holder, int position) {

        Selection bselection = bList.get(position);
        Selection gselection = gList.get(position);

        holder.cTitle.setText("Couple " + bselection.getId());
        holder.cName.setText(bselection.getName() + " & " + gselection.getName());

        Log.e("Best Couple", bselection.getImg());

        //image name to id
        holder.bimageView.setImageResource(context.getResources().getIdentifier("com.aunthtoo.welcomevoting:drawable/" + bselection.getImg(), null, null));

        holder.gimageView.setImageResource(context.getResources().getIdentifier("com.aunthtoo.welcomevoting:drawable/" + gselection.getImg(), null, null));


        if (bselection.getChoose() == 0) {
            holder.choose.setText("Select");
            holder.choose.setBackgroundResource(R.drawable.press);
        } else {
            holder.choose.setText("Selected");
            holder.choose.setBackgroundResource(R.drawable.normal);
        }


    }

    @Override
    public int getItemCount() {
        return bList.size();
    }
}

