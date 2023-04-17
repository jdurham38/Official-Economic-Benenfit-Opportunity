package de.androidcrypto.nfchcendefemulator;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class PayNow extends Fragment implements NfcAdapter.ReaderCallback {

    EditText dataToWriteOnTag;
    TextView readContent;
    String ndefMessageString;



    private NfcAdapter mNfcAdapter;
    private Toast tapReaderToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pay_now, container, false);

        dataToWriteOnTag = rootView.findViewById(R.id.etWriteContent);
        readContent = rootView.findViewById(R.id.tvReadContent);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
        tapReaderToast = Toast.makeText(getActivity(), "Tap Reader", Toast.LENGTH_LONG);


        Button payNowButton = rootView.findViewById(R.id.payNowButton);
        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // NFC reading and writing code goes here
                if (mNfcAdapter != null) {
                    Bundle options = new Bundle();
                    // Work around for some broken Nfc firmware implementations that poll the card too fast
                    options.putInt(NfcAdapter.EXTRA_READER_PRESENCE_CHECK_DELAY, 250);

                    // Enable ReaderMode for all types of card and disable platform sounds
                    mNfcAdapter.enableReaderMode(getActivity(),
                            PayNow.this,
                            NfcAdapter.FLAG_READER_NFC_A |
                                    NfcAdapter.FLAG_READER_NFC_B |
                                    NfcAdapter.FLAG_READER_NFC_F |
                                    NfcAdapter.FLAG_READER_NFC_V |
                                    NfcAdapter.FLAG_READER_NFC_BARCODE |
                                    NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS,
                            options);

                    if (tapReaderToast != null) {
                        tapReaderToast.cancel();
                    }
                    tapReaderToast = Toast.makeText(getActivity(), "Tap Reader", Toast.LENGTH_LONG);
                    tapReaderToast.show();
                }
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
            mNfcAdapter.disableReaderMode(this.getActivity());
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        Ndef ndefTag = Ndef.get(tag);
        if (ndefTag == null) {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(),
                        "This tag is not NDEF formatted",
                        Toast.LENGTH_SHORT).show();
            });
            return;
        }

        NdefMessage ndefMessage = new NdefMessage(buildNdefRecords());

        try {
            // Connect to the tag and write the NDEF message
            ndefTag.connect();
            ndefTag.writeNdefMessage(ndefMessage);

            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(),
                        "Message sent to the tag",
                        Toast.LENGTH_SHORT).show();
            });

            // Make a Sound
            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                r.play();
            } catch (Exception e) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(),
                            "Error playing sound: " + e,
                            Toast.LENGTH_SHORT).show();
                });
            }
        } catch (IOException | FormatException e) {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(),
                        "Error writing message to tag: " + e,
                        Toast.LENGTH_SHORT).show();
            });
        } finally {
            try {
                ndefTag.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    private NdefRecord[] buildNdefRecords() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cardInfoPrefs", Context.MODE_PRIVATE);
        String pan = sharedPreferences.getString("pan", "");
        String expiry = sharedPreferences.getString("expiry", "");
        String timeNow = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("uuuu.MM.dd HH.mm.ss"));

        // Build NDEF records
        NdefRecord panRecord = NdefRecord.createTextRecord("en", pan);
        NdefRecord expiryRecord = NdefRecord.createTextRecord("en", expiry);
        NdefRecord timestampRecord = NdefRecord.createTextRecord("en", timeNow);

        return new NdefRecord[]{panRecord, expiryRecord, timestampRecord};
    }
}

// This method is run in another thread when a card is discovered
// !!!! This method cannot cannot direct interact with the UI Thread
// Use `runOnUiThread`
