package stanislav.volnjanskij.popularmovies.ui.settings;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;

import stanislav.volnjanskij.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
    
    }
