package it.fdc.dynamicbrand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class LicencesActivity extends AppCompatActivity {
public String[] mArray;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licences);

        mTextView = (TextView)findViewById(R.id.textView);

        mArray = getResources().getStringArray(R.array.name_array_like_library_list);
        int i;
        String content = "";
        for(i=0;i<mArray.length;i++)
        {
            content += mArray[i] + "\n"+ "\n";
        }
        mTextView.setText(content);
    }
}
