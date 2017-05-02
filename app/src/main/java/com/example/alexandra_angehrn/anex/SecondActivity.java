package com.example.alexandra_angehrn.anex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by alexandra_angehrn on 03/11/2016.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Permet d'appeler la methode onCreate de la class Activity
        super.onCreate(savedInstanceState);

        // Permet d'indiquer l'interface graphique de l'Activity
        setContentView(R.layout.activity_second);

        // Permet d'acceder aux paramètres de la FirstActivity
        Intent old = getIntent();
        // Permet de lire les paramètres (ici l'url)
        String url = old.getStringExtra("url");

        // Permet d'identifier la view webView et de la déclarer comme webView
        WebView wv = (WebView) findViewById(R.id.webview);
        // Permet de modifier les paramètres de cette webView pour y mettre la bonne url
        wv.getSettings().setJavaScriptEnabled(true);
        // Permet de charger le site ciblé
        wv.loadUrl(url);

    }
}
