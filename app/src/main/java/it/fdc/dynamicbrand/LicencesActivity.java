package it.fdc.dynamicbrand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class LicencesActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licences);
        mTextView = (TextView)findViewById(R.id.licenseText);

        String[] lines = getResources().getStringArray(R.array.name_array_like_library_list);
        String content = "";
        for(String line: lines) content += line + "\n\n";
        mTextView.setText(content);
    }
}
