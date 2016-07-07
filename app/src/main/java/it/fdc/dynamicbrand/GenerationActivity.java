package it.fdc.dynamicbrand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GenerationActivity extends AppCompatActivity implements PrincipleFragment.OnPrincipleFragmentInteractionListener {
    private int[] mPrinciples = new int[] {1,0,0,0,0,0,0,1,0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, PrincipleFragment.newInstance(1))
                .commit();
    }

    @Override
    public int onRequestPrincipleValue(int index) {
        return mPrinciples[index];
    }

    @Override
    public void onSetPrincipleValue(int index, int value) {
        mPrinciples[index] = value;
        //TODO: refresh dell'elemento
    }
}
