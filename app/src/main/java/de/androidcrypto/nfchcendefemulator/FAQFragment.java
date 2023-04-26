package de.androidcrypto.nfchcendefemulator;

import static android.content.Context.VIBRATOR_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FAQFragment extends Fragment {

    private ExpandableListView mExpandableListView;
    private List<String> mGroupTitles;
    private HashMap<String, List<String>> mChildMessages;

    public FAQFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        mExpandableListView = view.findViewById(R.id.expandable_list_view);

        // Initialize the group titles and child messages
        mGroupTitles = new ArrayList<>();
        mGroupTitles.add("How do you use the card in ‘Pay Now’?");
        mGroupTitles.add("How do you add a card in “Add-a-Card”");
        mGroupTitles.add("What is NFC");

        mChildMessages = new HashMap<>();
        List<String> group1Messages = new ArrayList<>();
        group1Messages.add("To use the 'Pay Now' feature, follow these steps: First, go to the 'Pay Now' tab. " +
                "Next, click on the 'Pay Now' button. Then, hold your NFC-compatible phone close to the terminal to initiate the transaction. After the transaction has been processed, your phone will make a noise to indicate that it's been completed.");        List<String> group2Messages = new ArrayList<>();
        group2Messages.add("To add a card using the \"Add-a-Card\" feature, go to the \"Add a Card\" tab in the Hotbar below. Once you have selected this tab, you will be prompted to enter your card number and expiration date. You can find these numbers on the approved issued card that you wish to add to your account. " +
                "Enter these digits into the corresponding text boxes and click the \"Add Card\" button to complete the process. Once the card has been successfully added, you should be able to use the ‘Pay Now’ function. Additionally, make sure that the device you are doing can use NFC capabilities. ");
        List<String> group3Messages = new ArrayList<>();
        group3Messages.add("Near Field Communication (NFC) is a wireless technology that enables devices to exchange data with each other when they are in close proximity, usually within a few centimeters. " +
                "This Digital Wallet incorporates NFC technology to read and write data to compatible cards, such as credit cards or transportation passes. By simply holding the device close to the card, users can easily access the information stored on it or update it with new data, making the process fast and seamless.\n");


        mChildMessages.put(mGroupTitles.get(0), group1Messages);
        mChildMessages.put(mGroupTitles.get(1), group2Messages);
        mChildMessages.put(mGroupTitles.get(2), group3Messages);

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(), mGroupTitles, mChildMessages);
        mExpandableListView.setAdapter(adapter);



        return view;
    }

}
