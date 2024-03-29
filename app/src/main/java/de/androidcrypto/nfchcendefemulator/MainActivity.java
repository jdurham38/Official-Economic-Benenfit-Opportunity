package de.androidcrypto.nfchcendefemulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setOnItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    //private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            selectedFragment = new HomeFragment();
        } else if(itemId == R.id.pay_now){
            selectedFragment = new PayNow();
        }
        else if (itemId == R.id.transaction_history) {
            selectedFragment = new TransactionDetailsFragment();
        }
        else if (itemId == R.id.add_card) {
            selectedFragment = new AddCardFragment();
        } //else if (itemId == R.id.receive) {
           // selectedFragment = new ReceiveFragment();}
        else if (itemId == R.id.help) {
            selectedFragment = new FAQFragment();
        }



        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };
}