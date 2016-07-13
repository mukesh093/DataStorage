package bridgelabz.com.savingdata;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by bridgeit on 5/7/16.
 */
public class PrefsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
