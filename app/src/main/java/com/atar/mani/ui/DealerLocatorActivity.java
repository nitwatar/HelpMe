package com.atar.mani.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atar.mani.R;
import com.atar.mani.model.DealersData;

import java.util.ArrayList;

/**
 * Created by atarmanipandey on 27/9/17.
 */

public class DealerLocatorActivity extends AppCompatActivity {

    private static final String TAG = "DealerLocatorActivity";
    RecyclerView recyclerView;
    AutoCompleteTextView actv;
    Button buttonSearch;
    LinearLayout llinner,llouter,llouter1 ;
    ImageView img;
    private boolean isAnimationDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_locator);
        actv = (AutoCompleteTextView)findViewById(R.id.autocomplete_city);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        llinner = (LinearLayout) findViewById(R.id.ll_finddealer_inner);
        llouter = (LinearLayout) findViewById(R.id.ll_finddealer_outer);
        llouter1 = (LinearLayout) findViewById(R.id.ll_finddealer_outer1);
        img = (ImageView) findViewById(R.id.ivDealerLocator);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAnimationDone) {
                    animateView();
                }else{
                    loadDealerList();
                }

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_dealers);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this ,2);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void animateView() {
        Log.d(TAG, "animateView: from = "+llouter.getY() +" to ="+(img.getY()-llouter1.getY()));
        ObjectAnimator animate =  ObjectAnimator.ofFloat(llouter,"y",llouter.getY(),(img.getY()-llouter1.getY()) );
        animate.setDuration(1000);
        animate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d(TAG, "onAnimationEnd: ");

                llouter.requestLayout();
                 loadDealerList();
                isAnimationDone = true;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animate.start();
    }

    private void loadDealerList() {
        recyclerView.requestLayout();
        recyclerView.setVisibility(View.VISIBLE);
        DealersListAdpater adapter = new DealersListAdpater(DealersData.getDealerList());
        recyclerView.setAdapter(adapter);

    }





    private class DealersListAdpater  extends RecyclerView.Adapter<DealersListAdpater.DealersViewHolder>{
        ArrayList<DealersData> dealerslist;

        public DealersListAdpater(ArrayList<DealersData> dealerslist) {
            this.dealerslist= dealerslist;
        }

        @Override
        public DealersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view =  LayoutInflater.from(DealerLocatorActivity.this).inflate(R.layout.dealer_location_item,parent,false);
            return new DealersViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DealersViewHolder holder, int position) {

            holder.dealerName.setText(dealerslist.get(position).getDealerName());
            holder.dealerAddress.setText(dealerslist.get(position).getDealerAddress());
        }

        @Override
        public int getItemCount() {
            return dealerslist.size();
        }

        class DealersViewHolder extends RecyclerView.ViewHolder{

             private TextView dealerName;
             private TextView dealerAddress;

            public DealersViewHolder(View itemView) {
                super(itemView);
                dealerName = (TextView) itemView.findViewById(R.id.textViewDealerName);
                dealerAddress = (TextView) itemView.findViewById(R.id.textViewDealerAddress);
            }
        }
    }


}
