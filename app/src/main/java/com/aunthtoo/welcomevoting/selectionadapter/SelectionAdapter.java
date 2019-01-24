package com.aunthtoo.welcomevoting.selectionadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aunthtoo.welcomevoting.R;
import com.aunthtoo.welcomevoting.choosepages.AllKing;
import com.aunthtoo.welcomevoting.choosepages.AllQueen;
import com.aunthtoo.welcomevoting.choosepages.Attraction;
import com.aunthtoo.welcomevoting.choosepages.Glory;
import com.aunthtoo.welcomevoting.choosepages.Innocence;
import com.aunthtoo.welcomevoting.choosepages.Joker;
import com.aunthtoo.welcomevoting.choosepages.King;
import com.aunthtoo.welcomevoting.choosepages.Popular;
import com.aunthtoo.welcomevoting.choosepages.Queen;
import com.aunthtoo.welcomevoting.choosepages.Smart;
import com.aunthtoo.welcomevoting.dbhandler.BoyDBHandler;
import com.aunthtoo.welcomevoting.dbhandler.GirlDBHandler;
import com.aunthtoo.welcomevoting.dialog.ImgDialog;
import com.aunthtoo.welcomevoting.model.Selection;

import java.util.List;

/**
 * Created by Aunt Htoo on 1/5/2017.
 */

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.SelectionViewHolder> {

    private List<Selection> selectionList;
    private Context context;
    private RelativeLayout r;
    int chkforchoose, whichpage;
    BoyDBHandler handler;
    GirlDBHandler dbHandler;

    Selection sel;

    public class SelectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView sName, sBirthday;

        public CardView cardView;
        public Button choose;
        public ImageView imageView;


        public SelectionViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.kingr);


            sName = (TextView) view.findViewById(R.id.sName);
            sBirthday = (TextView) view.findViewById(R.id.sBd);
            choose = (Button) view.findViewById(R.id.choose);
            imageView = (ImageView) view.findViewById(R.id.listimg);

            handler = new BoyDBHandler(context);
            dbHandler = new GirlDBHandler(context);

            choose.setOnClickListener(this);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.choose:

                    sel = selectionList.get(getAdapterPosition());
                    chkforchoose = sel.getChoose();

                    if (chkforchoose == 0) {

                        switch (whichpage) {

                            case 1:
                                handler.setKing(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");

                                break;

                            case 2:

                                handler.setSmart(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");

                                break;

                            case 3:

                                handler.setPopular(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");

                                break;

                            case 4:
                                handler.setJoker(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");
                                break;

                            case 5:
                                handler.setAllKing(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");
                                break;

                            case 6:

                                dbHandler.setQueen(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");

                                break;

                            case 7:

                                dbHandler.setGlory(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");

                                break;

                            case 8:
                                dbHandler.setAttraction(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");

                                break;

                            case 9:
                                dbHandler.setInnocence(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");
                                break;

                            case 10:
                                dbHandler.setAllQueen(sel.getId(), 1);
                                chkforchoose = 1;
                                choose.setText("Selected");
                                break;

                        }


                    } else {

                        switch (whichpage) {

                            case 1:
                                handler.setKing(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");

                                break;

                            case 2:

                                handler.setSmart(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");

                                break;

                            case 3:
                                handler.setPopular(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");

                                break;
                            case 4:

                                handler.setJoker(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");

                                break;

                            case 5:
                                handler.setAllKing(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");

                                break;

                            case 6:
                                dbHandler.setQueen(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");
                                break;

                            case 7:
                                dbHandler.setGlory(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");
                                break;

                            case 8:
                                dbHandler.setAttraction(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");
                                break;

                            case 9:
                                dbHandler.setInnocence(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");

                                break;

                            case 10:
                                dbHandler.setAllQueen(sel.getId(), 0);
                                chkforchoose = 0;
                                choose.setText("Select");
                                break;
                        }

                    }


                    notifyDataSetChanged();
                    // notifyItemRangeChanged(0, getItemCount());

                    if (whichpage == 1)

                        context.startActivity(new Intent(context, King.class));
                    else if (whichpage == 2)
                        context.startActivity(new Intent(context, Smart.class));
                    else if (whichpage == 3)
                        context.startActivity(new Intent(context, Popular.class));
                    else if (whichpage == 4)
                        context.startActivity(new Intent(context, Joker.class));
                    else if (whichpage == 5)
                        context.startActivity(new Intent(context, AllKing.class));
                    else if (whichpage == 6)
                        context.startActivity(new Intent(context, Queen.class));
                    else if (whichpage == 7)
                        context.startActivity(new Intent(context, Glory.class));
                    else if (whichpage == 8)
                        context.startActivity(new Intent(context, Attraction.class));
                    else if (whichpage == 9)
                        context.startActivity(new Intent(context, Innocence.class));
                    else if (whichpage == 10)
                        context.startActivity(new Intent(context, AllQueen.class));


                    break;

                case R.id.kingr:
                    sel = selectionList.get(getAdapterPosition());

                    ImgDialog dialog = new ImgDialog(context);

                    dialog.showDialog(sel.getName(), sel.getImg() + "_1", sel.getImg() + "_2", sel.getImg() + "_3");

                    // Toast.makeText(context,"You Clicked !!"+String .valueOf(selectionList.get(getAdapterPosition()).getId()),Toast.LENGTH_SHORT).show();
                    break;


            }
        }


    }

    public SelectionAdapter(Context context, List<Selection> selectionList, RelativeLayout r, int whichpage) {
        this.selectionList = selectionList;
        this.context = context;
        this.r = r;
        this.whichpage = whichpage;
    }


    @Override
    public SelectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View selectionView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new SelectionViewHolder(selectionView);
    }

    @Override
    public void onBindViewHolder(SelectionViewHolder holder, int position) {

        Selection selection = selectionList.get(position);

        holder.sName.setText(selection.getName());
        holder.sBirthday.setText(selection.getBirthday());

//selection.getImg()
        //image name to id
        holder.imageView.setImageResource(context.getResources().getIdentifier("com.aunthtoo.welcomevoting:drawable/" + selection.getImg(), null, null));


        if (selection.getChoose() == 0) {
            holder.choose.setText("Select");
            holder.choose.setBackgroundResource(R.drawable.press);
        } else {
            holder.choose.setText("Selected");
            holder.choose.setBackgroundResource(R.drawable.normal);
        }


    }

    @Override
    public int getItemCount() {
        return selectionList.size();
    }


}
