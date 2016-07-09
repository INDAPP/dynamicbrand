package it.fdc.dynamicbrand;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView tTextView = (TextView) findViewById(R.id.description_title);
        TextView dTextView = (TextView) findViewById(R.id.description_text);

        tTextView.setText(R.string.info_t);
        dTextView.setText(R.string.info1);

        findViewById(R.id.facebookButton).setOnClickListener(this);
        findViewById(R.id.mailButton).setOnClickListener(this);
        findViewById(R.id.webButton).setOnClickListener(this);
        findViewById(R.id.callButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.facebookButton:
                try {
                    this.getPackageManager()
                            .getPackageInfo("com.facebook.katana", 0);
                    intent= new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://page/113054755551004"));
                } catch (Exception e) {
                    intent= new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/113054755551004/"));
                }
                break;
            case R.id.mailButton:
                String url = "mailto:g.giunta@fdcmessina.org";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                break;
            case R.id.webButton:
                String mail = "http://www.fdcmessina.org";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mail));
                break;
            case R.id.callButton:
                String number = "0909023226";
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +number));
                break;
            }
        startActivity(intent);
        }

}

