package it.fdc.dynamicbrand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GuideActivity extends AppCompatActivity {
public String[] mArray;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        mTextView = (TextView)findViewById(R.id.guideText);
        mTextView.setText(R.string.guide_strings);
    }
}
