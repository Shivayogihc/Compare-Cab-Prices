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

public class FragmentB extends Fragment {
    WebView webView;
    private SharedViewModel viewModel;
    private SharedViewModel_t viewModel_t;

    String link_1;

    GeoLocation geoLocation;

    String address_1;
    String address_2;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        webView = v.findViewById(R.id.webview);

        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//NEW
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient(){
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback){
                callback.invoke(origin,true,false);
            }
        });

        webView.getSettings().setGeolocationDatabasePath(getContext().getFilesDir().getPath());

        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        //geoLocation = new GeoLocation();


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                //editText.setText(charSequence);


                //geoLocation.getAddress((String) charSequence,getActivity().getApplicationContext(),new GeoHandler());

               // link_1= (String) charSequence;


                String gh="https://www.google.com";
                webView.loadUrl(gh);


            }
        });




        viewModel_t = ViewModelProviders.of(getActivity()).get(SharedViewModel_t.class);
        viewModel_t.getText_u().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@androidx.annotation.Nullable CharSequence charSequence) {
                //editText.setText(charSequence);
                // editText_1.setText();


               // geoLocation.getAddress((String) charSequence,getActivity().getApplicationContext(),new GeoHandler());

                String h1 ="https://book.olacabs.com/home?pickup_name=Current%20Location&lat=";
                String h2 = address_1;
                String h3 = "&lng=";
                String h4= address_2;
                String h5="&pickup=&drop_lat=";
                String h6=address_1;
                String h7="&drop_lng=";
                String h8=address_2;
                String h9="&drop_name=";
                String[] addr= ((String) charSequence).split(" ");




                String code =h1+h2+h3+h4+h5+h6+h7+h8+h9+addr[0]+"%20"+addr[1]+"%2C%20"+addr[2]+"%20"+addr[3]+"%20"+addr[4]+"%20"+addr[5]+"%20"+addr[6]+"%20"+addr[7]+"%20"+addr[8];

                String hj="https://www.google.com";


                webView.loadUrl(hj);
               // Geocoder geocoder = new Geocoder();

            }
        });





    }



}