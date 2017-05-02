package com.example.alexandra_angehrn.anex;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Permet d'appeler la methode onCreate de la class Activity
        super.onCreate(savedInstanceState);

        // Permet d'indiquer l'interface graphique de l'Activity
        setContentView(R.layout.activity_main);

        // Permet d'identifier la view button et de la déclarer comme bouton
        Button b = (Button) findViewById(R.id.button);

        // Permet de savoir quand le bouton est cliqué et lancer une fonction
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Appel à la fonction executée lors du clic de button
                        doClickOnButton();
                    }
                });
            }
        });

        // Permet d'identifier la view button2 et de la déclarer comme bouton
        Button b2 = (Button) findViewById(R.id.button2);

        // Permet de savoir quand le bouton est cliqué et lancer une fonction
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Appel à la fonction executée lors du clic de button2
                        doClickOnButton2();
                    }
                });
            }
        });

        // Chemin vers le cache (INTERNAL STORAGE)
        File internal = getFilesDir();

        // Création de l'objet File à partir du chemin du cache
        File folder = new File(internal.toString());
        // Fais un tableau des files du cache
        File[] listOfFiles = folder.listFiles();

        // Pour chaque file du tableau
        for (File file : listOfFiles) {
            if (file.isFile()) {

                // Création de l'objet File à partir du chemin du cache et du nom du file
                File files = new File(internal.toString(), file.getName());
                // Permet d'obtenir la date de dernière modification du file, soit la création sachant qu'on ne fait pas d'update
                Date lastModified = new Date(files.lastModified());

                // Calcul d'un jour en milisecondes
                long DAY_IN_MS = 1000 * 60 * 60 * 24;

                // Permet d'obtenir la date 7 jour après la date de dernière modification du file
                Date lastModified7 = new Date(lastModified.getTime() + (7 * DAY_IN_MS));

                // Permet d'obtenir la date d'aujourd'hui
                Date today = Calendar.getInstance().getTime();

                // Si la date d'aujourd'hui est après la date 7 jour après la date de dernière modification du file
                if(today.after(lastModified7) || today.equals(lastModified7)){
                    // on supprime le file du cache
                    files.delete();
                }

            }
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    private void doClickOnButton() {

        // Permet d'identifier la view editText et de la déclarer comme input text
        EditText et = (EditText) findViewById(R.id.editText);

        // Permet d'obtenir la value de editText
        String url = et.getText().toString();

        try {

            // Création de l'objet URL à partir de l'url tapée
            URL u = new URL(url);

            // openConnection() ouvre une connexion vers la ressource et renvoie un objet de type URLConnexion
            final URLConnection c = u.openConnection();

            // Permet de mettre u de type Sting
            String test = u.toString();

            // Chemin vers le cache (INTERNAL STORAGE)
            File internal = getFilesDir();

            // Permet de demander l'implémentation en MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Permet d'ajouter des données à traiter et de hasher
            md.update(test.getBytes());

            // Hash l'URL
            byte byteData[] = md.digest();

            //convertit les bytes en hexadecimal
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Création de l'objet File à partir du chemin du cache et du nom du File
            final File f = new File(internal, sb.toString());

            // Check si le File existe dans le cache
            if (f.exists()) {

                // Permet d'identifier la view button2 et de la déclarer comme bouton
                Button b2 = (Button) findViewById(R.id.button2);
                // Permet de rendre visible le button2
                b2.setVisibility(View.VISIBLE);

                FileInputStream is = null;
                try {
                    //Construit une instance de FileInputStream associé au fichier
                    is = new FileInputStream(f);
                } catch (FileNotFoundException e1) {
                    // Si le fichier n'existe pas, ou s'il ne peut pas être ouvert en lecture pour une raison quelconque
                    e1.printStackTrace();
                }

                // Permet de lire des octets et les traduit en caractères en utilisant un décodage spécifique
                InputStreamReader isr = new InputStreamReader(is);

                // Permet de lire les blocks du fichier
                BufferedReader br = new BufferedReader(isr);

                    try {

                        // Création de la String Line
                        String line = br.readLine();

                        // Création d'un String modifiable
                        final StringBuilder sbr = new StringBuilder();

                        // Vérification si le fichier est fini
                        while (line != null) {
                            // Ajout des lignes à la suite
                            sbr.append(line + "\n");
                            line = br.readLine();
                        }

                        // Permet d'identifier la view textView3 et de la déclarer comme texte
                        TextView t = (TextView) findViewById(R.id.textView3);
                        //  Permet de remplacer le texte par le contenu récupéré de l'url
                        t.setText("Fichier déjà exitant ! \n" + sbr.toString());
                        // permet de rendre la partie texte scrollable à partir de 10 lignes
                        t.setMovementMethod(new ScrollingMovementMethod());
                    }
                    catch(IOException e){}; // si erreur

                // Ferme le bufferReader
                br.close();

            } else {

                //Créer le fichier
                f.createNewFile();

                // Permet d'identifier la view button2 et de la déclarer comme bouton
                Button b2 = (Button) findViewById(R.id.button2);
                // Permet de rendre visible le button2
                b2.setVisibility(View.VISIBLE);

                // Créer un nouveau fil pour se connecter au serveur
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        BufferedReader br = null;

                        try {

                            //Construit une instance de InputStream de la connexion url
                            InputStream is = c.getInputStream();

                            // Permet de lire des octets et les traduit en caractères en utilisant un décodage spécifique
                            InputStreamReader isr = new InputStreamReader(is);

                            // Permet de lire les blocks du fichier
                            br = new BufferedReader(isr);

                            // Création d'un String modifiable
                            final StringBuilder sbr = new StringBuilder();

                            try {
                                // Création de la String Line
                                String line = br.readLine();

                                // Vérification si le fichier est fini
                                while (line != null) {
                                    // Ajout des lignes à la suite
                                    sbr.append(line + "\n");
                                    line = br.readLine();
                                }
                            }
                            catch(IOException e){};  // si erreur

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    FileOutputStream isw = null;

                                    try {
                                        // Permet d'identifier la view textView3 et de la déclarer comme texte
                                        TextView t = (TextView) findViewById(R.id.textView3);

                                        //  Permet de remplacer le texte par le contenu du fichier
                                        t.setText("Fichier ajouté au cache ! \n" + sbr.toString());

                                        //Construit une instance de FileOutStream associé au fichier
                                        isw = new FileOutputStream(f);

                                        // Permet d'ecrire des octets et les traduit en bytes
                                        OutputStreamWriter isrw = new OutputStreamWriter(isw);

                                        // Permet d'ecrire les blocks du fichier
                                        BufferedWriter brw = new BufferedWriter(isrw);

                                        // Permet d'ecrire chaque ligne
                                        brw.write(sbr.toString());

                                    } catch (FileNotFoundException e) {
                                        // Fichier non trouvé
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        //si erreur
                                        e.printStackTrace();
                                    }
                                }
                            });


                        } catch (IOException e) {
                            //si erreur
                            e.printStackTrace();
                        }

                    }

                    ;
                });
                t.start();

            }
        } catch (MalformedURLException e) {

            // Création d'un Alerte
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            // Titre de l'Alerte
            alertDialog.setTitle("Alert");
            // Message de l'Alerte
            alertDialog.setMessage("Your URL is invalid");
            //  Montrer l'Alerte
            alertDialog.show();

            e.printStackTrace();

        } catch (IOException e) {
            // si erreur
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // Création d'un Alerte
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            // Titre de l'Alerte
            alertDialog.setTitle("Alert");
            // Message de l'Alerte
            alertDialog.setMessage("Your cryptographic algorithm is invalid");
            //  Montrer l'Alerte
            alertDialog.show();

            // si MD5 non valide
            e.printStackTrace();
        }
    }


    private void doClickOnButton2() {

        // Permet d'identifier la view editText et de la déclarer comme input text
        EditText et = (EditText) findViewById(R.id.editText);
        // Permet d'obtenir la value de editText
        String url = et.getText().toString();

        // Permet de rediriger vers la SecondActivity
        Intent i = new Intent(this,SecondActivity.class);
        // Permet d'envoyer l'URL en paramètres
        i.putExtra("url", url);
        // Permet de lancer la redirection
        startActivity(i);
    }

        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
