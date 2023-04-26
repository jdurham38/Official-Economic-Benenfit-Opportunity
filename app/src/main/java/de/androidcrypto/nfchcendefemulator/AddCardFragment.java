package de.androidcrypto.nfchcendefemulator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCardFragment extends Fragment {

    com.google.android.material.textfield.TextInputLayout panLayout, expiryLayout;
    com.google.android.material.textfield.TextInputEditText panEditText, expiryEditText;
    Button sendButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCardFragment newInstance(String param1, String param2) {
        AddCardFragment fragment = new AddCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private String getSavedPan() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("pan", "");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        panLayout = getView().findViewById(R.id.til_pan);
        panEditText = getView().findViewById(R.id.et_pan);
        expiryLayout = getView().findViewById(R.id.til_expiry);
        expiryEditText = getView().findViewById(R.id.et_expiry);
        sendButton = getView().findViewById(R.id.btn_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pan = panEditText.getText().toString();
                String expiry = expiryEditText.getText().toString();
                if (TextUtils.isEmpty(pan) || TextUtils.isEmpty(expiry)) {
                    Toast.makeText(view.getContext(), "Enter Card Number and Expiration Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pan.length() != 16 || expiry.length() != 4) {
                    Toast.makeText(view.getContext(), "Invalid Card Number or Expiration Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("AddCardFragment", "Card Number: " + pan + " Expiration Date: " + expiry);

                // Save the card information to shared preferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cardInfoPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pan", pan);
                editor.putString("expiry", expiry);
                editor.putString("amount", "100");
                editor.putString("transaction_type", "Purchase");
                editor.apply();
                Toast.makeText(view.getContext(), "Card information saved successfully!", Toast.LENGTH_SHORT).show();

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.transaction_details_container, TransactionDetailsFragment.newInstance());
                transaction.commit();

                // Pass the PAN value to the TransactionDetailsFragment
                TransactionDetailsFragment transactionDetailsFragment = (TransactionDetailsFragment) getChildFragmentManager().findFragmentById(R.id.transaction_details_container);
                if (transactionDetailsFragment != null) {
                    TextView panTextView = transactionDetailsFragment.getView().findViewById(R.id.tv_pan);
                    panTextView.setText(pan);
                }
            }
        });



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
