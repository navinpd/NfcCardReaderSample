package com.example.navin.cardreaderproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.devnied.emvnfccard.exception.CommunicationException;
import com.github.devnied.emvnfccard.model.EmvCard;
import com.github.devnied.emvnfccard.parser.EmvParser;
import com.github.devnied.emvnfccard.parser.IProvider;

public class MainActivity extends AppCompatActivity {
    TextView firstName, lastName, expiryDate, cardNumber, cardType;
    Button cardScanner;
    IProvider provider;
    EmvParser parser;
    EmvCard card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = (TextView) findViewById(R.id.first_name);
        lastName = (TextView) findViewById(R.id.last_name);
        expiryDate = (TextView) findViewById(R.id.expiry_date);
        cardNumber = (TextView) findViewById(R.id.card_number);
        cardType =  (TextView) findViewById(R.id.card_type);
        cardScanner = (Button) findViewById(R.id.scan_btn);

        cardScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parser = new EmvParser(provider, true);
                try {
                    card = parser.readEmvCard();
                    cardNumber.setText(card.getCardNumber());
                    firstName.setText(card.getHolderFirstname());
                    lastName.setText(card.getHolderLastname());
                    expiryDate.setText(card.getExpireDate().toString());
                    cardType.setText(card.getType().toString());


                } catch (CommunicationException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class YourProvider implements IProvider {


        @Override
        public byte[] transceive(byte[] pCommand) throws CommunicationException {

            return new byte[0];
        }
    }

}
