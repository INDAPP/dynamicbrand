package it.fdc.dynamicbrand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.card1).setOnClickListener(this);
        findViewById(R.id.card2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.card1:
                intent = new Intent(this,GenerationActivity.class);
                break;
            case R.id.card2:
                intent = new Intent(this,BrandsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
