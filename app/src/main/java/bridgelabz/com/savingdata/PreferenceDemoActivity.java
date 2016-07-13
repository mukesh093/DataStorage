package bridgelabz.com.savingdata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreferenceDemoActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_demo);

        Button store = (Button) findViewById(R.id.store);
        Button show = (Button) findViewById(R.id.show);
        textView = (TextView) findViewById(R.id.txtPrefs);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.store:
                        Intent intent = new Intent(PreferenceDemoActivity.this,PrefsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.show:
                        displaySharedPreferences();
                        break;
                    default:
                        break;
                }
            }
        };
        store.setOnClickListener(listener);
        show.setOnClickListener(listener);
    }

    private void displaySharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PreferenceDemoActivity.this);
        String username = prefs.getString("username", "Default NickName");
        String passw = prefs.getString("password", "Default Password");
        boolean checkBox = prefs.getBoolean("checkBox", false);
        String listPrefs = prefs.getString("listpref", "Default list prefs");

        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Password: " + passw + "\n");
        builder.append("Keep me logged in: " + String.valueOf(checkBox) + "\n");
        builder.append("List preference: " + listPrefs);
        textView.setText(builder.toString());
    }
}
