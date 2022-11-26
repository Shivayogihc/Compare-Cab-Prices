package com.codinginflow.sharedviewmodelexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.Nullable;


public class Fragment_Uber extends Fragment {

    WebView webView_u;
    private SharedViewModel_t viewModel_t;
    private SharedViewModel viewModel;

    public Fragment_Uber() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__uber, container, false);
        webView_u = v.findViewById(R.id.webview_uber);


        WebSettings webSettings =webView_u.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView_u.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView_u.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//NEW
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        webView_u.getSettings().setAppCacheEnabled(true);
        webView_u.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView_u.setWebViewClient(new WebViewClient());
        webView_u.setWebViewClient(new WebViewClient());

        webView_u.setWebChromeClient(new WebChromeClient(){
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback){
                callback.invoke(origin,true,false);
            }
        });

        webView_u.getSettings().setGeolocationDatabasePath(getContext().getFilesDir().getPath());

        webView_u.getSettings().setDatabaseEnabled(true);
        webView_u.getSettings().setDomStorageEnabled(true);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        viewModel_t = ViewModelProviders.of(getActivity()).get(SharedViewModel_t.class);
        viewModel_t.getText_u().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                //editText.setText(charSequence);


                /*
                Place place = (Place) charSequence;

                LatLng hj=place.getLatLng();
                String hi = charSequence.toString();

                String[] data = hi.split(" ");

                String latitude = String.valueOf(hj.toString().split(","));


                String h1="https://book.olacabs.com/home?pickup_name=Current%20Location&lat=";
                String h2 = String.valueOf(place.getLatLng());
                String h3 = "";

                 */

                String gh="https://www.google.com";

                webView_u.loadUrl(gh);
            }
        });




    }
}