package com.example.exempleemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        final EditText txtObjet = findViewById(R.id.txtObjet);
        final EditText txtDestinataire = findViewById(R.id.txtDestinataire);
        final EditText txtMessage = findViewById(R.id.txtMessage);

        Button btnEnvoyer = findViewById(R.id.btnEnvoyer);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String objet = txtObjet.getText().toString().trim();
                String destinataire = txtDestinataire.getText().toString().trim();
                String message = txtMessage.getText().toString().trim();

                if (objet.isEmpty()) {
                    Toast.makeText(context, "Veuillez saisir un objet", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (destinataire.isEmpty()) {
                    Toast.makeText(context, "Veuillez saisir un destinataire", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (message.isEmpty()) {
                    Toast.makeText(context, "Veuillez saisir un message", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendEmail(objet, destinataire, message);
            }
        });
    }

    private void sendEmail(String objet, String destinataire, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{destinataire});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, objet);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Envoi du message..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "Impossible d'envoyer le message.", Toast.LENGTH_SHORT).show();
        }
    }
}
