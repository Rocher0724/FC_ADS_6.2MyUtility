package choongyul.android.com.myutility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iv = (ImageView) findViewById(R.id.iv);
        Intent intent = getIntent();
        String adress = intent.getExtras().getString("adress");
        Glide.with(this).load(adress).into(iv);



    }
}
