package de.androidcrypto.nfchcendefemulator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;
public class TransactionDetailsFragment extends Fragment {

    TextView panTextView, expiryTextView, amountTextView, transactionTypeTextView;

    private static final String CARD_INFO_PREFS = "cardInfoPrefs";
    private static final String PAN_KEY = "pan";
    private static final String AMOUNT_KEY = "amount";
    private static final String TRANSACTION_TYPE_KEY = "transaction_type";

    public TransactionDetailsFragment() {
        // Required empty public constructor
    }

    public static TransactionDetailsFragment newInstance() {
        TransactionDetailsFragment fragment = new TransactionDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        panTextView = view.findViewById(R.id.tv_pan);
        amountTextView = view.findViewById(R.id.tv_amount);
        transactionTypeTextView = view.findViewById(R.id.tv_transaction_type);

        // Retrieve the saved PAN value from shared preferences and set it to the PAN text view
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(CARD_INFO_PREFS, Context.MODE_PRIVATE);
        String pan = sharedPreferences.getString(PAN_KEY, "");
        if (!TextUtils.isEmpty(pan)) {
            panTextView.setText(pan);

            // Display the other card information
            amountTextView.setText(sharedPreferences.getString(AMOUNT_KEY, ""));
            transactionTypeTextView.setText(sharedPreferences.getString(TRANSACTION_TYPE_KEY, ""));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
