package com.atar.mani.model;

import java.util.ArrayList;

/**
 * Created by atarmanipandey on 28/9/17.
 */

public class DealersData {
    public static ArrayList<DealersData> list;
    private String dealerName;
    private String dealerAddress;

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public DealersData(String dealerName, String dealerAddress) {
        this.dealerName = dealerName;
        this.dealerAddress = dealerAddress;
    }



    public static ArrayList<DealersData>  getDealerList(){
        init();
        return list;

    }

    private static void init() {
      list = new ArrayList<>();
        list.add(new DealersData("Dealer Name1 ","Dealer Address 1"));
        list.add(new DealersData("Dealer Name2 ","Dealer Address 2"));
        list.add(new DealersData("Dealer Name3 ","Dealer Address 3"));
        list.add(new DealersData("Dealer Name4 ","Dealer Address 4"));
        list.add(new DealersData("Dealer Name5 ","Dealer Address 5"));
    }
}
