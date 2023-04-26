package de.androidcrypto.nfchcendefemulator;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

public class MyExpandableListAdapter extends ExpandableListAdapter {

    public MyExpandableListAdapter(Context context, List<String> groupTitles, HashMap<String, List<String>> childMessages) {
        super(context, groupTitles, childMessages);
    }

    // Implement any abstract methods here
}

