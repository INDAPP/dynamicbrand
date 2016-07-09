package it.fdc.dynamicbrand;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnBrands).setOnClickListener(this);
        findViewById(R.id.btnContacts).setOnClickListener(this);
        findViewById(R.id.btnGeneration).setOnClickListener(this);
        findViewById(R.id.btnGuide).setOnClickListener(this);
        findViewById(R.id.btnLicence).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnGeneration:
                intent = new Intent(this, GenerationActivity.class);
                break;
            case R.id.btnBrands:
                intent = new Intent(this,BrandsActivity.class);
                break;
            case R.id.btnGuide:
                intent = new Intent(this, GuideActivity.class);
                break;
            case R.id.btnContacts:
                intent = new Intent(this, DescriptionActivity.class);
                break;
            case R.id.btnLicence:
                intent = new Intent(this,LicencesActivity.class);
                break;
        }
        startActivity(intent);
    }
}
