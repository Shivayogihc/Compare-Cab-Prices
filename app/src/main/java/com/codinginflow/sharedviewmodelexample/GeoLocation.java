package com.codinginflow.sharedviewmodelexample;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

class GeoLocation {

    public static void getAddress(final String locationAddress, final Context context, final Handler handler)
    {
        Thread thread = new Thread(){
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result_1 = null;
                String result_2 = null;

                try
                {
                    List addressList = geocoder.getFromLocationName(locationAddress,1);
                    if(addressList !=null && addressList.size() >0){
                        Address address = (Address)addressList.get(0);           //address entered
                        StringBuilder stringBuilder = new StringBuilder();

                       StringBuilder lol_1 = stringBuilder.append(address.getLatitude()).append("\n");
                        StringBuilder lol_2 = stringBuilder.append(address.getLongitude()).append("\n");

                        result_1=lol_1.toString();
                        result_2=lol_2.toString();
                       // result=stringBuilder.toString();                           //latitude and longitude
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if(result_1!=null && result_2!=null){
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        String result_4 =""+result_1;

                        String string = result_4;
                        String[] parts = string.split("\n");
                        String part1 = parts[0];
                        String part2 = parts[1];




                        String result_5 = "\n\nLongitude:" + result_2;

                        bundle.putString("address_1",part1);
                        bundle.putString("address_2",part2);
                        //transfer of data takes place
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }

            }
        };

        thread.start();
    }





}
