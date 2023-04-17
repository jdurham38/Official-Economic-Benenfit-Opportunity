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
        mGroupTitles.add("How to add a card?");
        mGroupTitles.add("FAQ2?");
        mGroupTitles.add("FAQ3?");
        mChildMessages = new HashMap<>();
        List<String> group1Messages = new ArrayList<>();
        group1Messages.add("Navigate to the 'Add Card' button on the navigation bar or home-screen, then enter a valid card number and expiration date from valid government benefit issuers (EBT, SNAP, ETC).");
        List<String> group2Messages = new ArrayList<>();
        group2Messages.add("FAQ2 Description Text.");
        List<String> group3Messages = new ArrayList<>();
        group3Messages.add("FAQ3 Description Text.");

        mChildMessages.put(mGroupTitles.get(0), group1Messages);
        mChildMessages.put(mGroupTitles.get(1), group2Messages);
        mChildMessages.put(mGroupTitles.get(2), group3Messages);

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(), mGroupTitles, mChildMessages);
        mExpandableListView.setAdapter(adapter);



        return view;
    }

}
