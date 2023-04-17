package de.androidcrypto.nfchcendefemulator;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;

/*
public class ParsePaymentFragment {




    @Override
    public void onTagDiscovered(Tag tag) {
        // Get the NDEF object for the tag
        Ndef ndef = Ndef.get(tag);

        // Check that the tag is NDEF-capable
        if (ndef != null) {
            try {
                // Connect to the tag
                ndef.connect();

                // Read the NDEF message from the tag
                NdefMessage message = ndef.getNdefMessage();

                // Parse the message to get the payment data
                NdefRecord[] records = message.getRecords();
                String paymentData = new String(records[1].getPayload());

                // Process the payment
                // Here you would implement code to send the payment data to a payment processor or other payment handling service
                // and then display the payment result to the user

                // Notify the user that the payment was successful
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(),
                            "Payment successful",
                            Toast.LENGTH_SHORT).show();
                });

                // Make a sound to indicate payment success
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getActivity(),
                            notification);
                    r.play();
                } catch (Exception e) {
                    // Some error playing sound
                }
            } catch (IOException e) {
                // Handle exceptions that occur while reading from the tag
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(),
                            "Error reading tag: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
            } catch (FormatException e) {
                // Handle exceptions that occur while parsing the NDEF message
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(),
                            "Error parsing NDEF message: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
            } finally {
                // Disconnect from the tag
                try {
                    ndef.close();
                } catch (IOException e) {
                    // Handle exceptions that occur while disconnecting from the tag
                }
            }
        }
    }

}
 */