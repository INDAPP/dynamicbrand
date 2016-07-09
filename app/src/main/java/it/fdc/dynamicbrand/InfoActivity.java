package it.fdc.dynamicbrand;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    public static final String nameCompany = "name";
    public static final String companyDescription = "description";
    public static final String urlDetails = "url";
    public static final String logoID ="logo";
    private String companyName;
    private String descriptionCompany;
    private String url;
    private int idLogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Bundle p = getIntent().getExtras();
        if (p !=null) {
            companyName = p.getString("name");
            descriptionCompany = p.getString("description");
            url = p.getString("url");
            idLogo = p.getInt("logo");
        }


        assert collapsingToolbar != null;
        collapsingToolbar.setTitle(companyName);

        ImageView logo = (ImageView) findViewById(R.id.logo_company);
        assert logo != null;
        logo.setImageResource(idLogo);
        TextView description = (TextView) findViewById(R.id.description_company);
        assert description != null;
        description.setText(descriptionCompany);

        Button moreInfo = (Button) findViewById(R.id.more_info);
        assert moreInfo != null;
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(Intent.createChooser(intent, getString(R.string.choose_browser)));
            }
        });






    }
}
