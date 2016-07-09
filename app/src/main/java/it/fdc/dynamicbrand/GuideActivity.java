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

        mArray = getResources().getStringArray(R.array.guide_strings);
        int i;
        String content = "";
        for(i=0;i<mArray.length;i++)
        {
            content += mArray[i] + "\n"+ "\n";
        }
        mTextView.setText(content);
    }
}
