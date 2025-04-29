package com.xcstats.views.custom;

/**
 * Created by Mobile on 1/27/2017.
 */

public interface collection {
    ConnectivityReceiverListener connectivityReceiverListener=new ConnectivityReceiverListener() {
        @Override
        public void onNetworkConnectionChanged(boolean isConnected) {

        }
    };
}
