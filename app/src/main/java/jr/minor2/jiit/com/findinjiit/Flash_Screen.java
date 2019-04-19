package jr.minor2.jiit.com.findinjiit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Flash_Screen extends AppCompatActivity {
    CircleImageView imageView3;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash__screen);
        imageView3=(CircleImageView) findViewById(R.id.imageView3);
        Picasso.with(this).load(R.drawable.logo2).fit().into(imageView3);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        final boolean status=sharedPreferences.getBoolean("login_status",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(status){
                    startActivity(new Intent(Flash_Screen.this,Home_Page.class));
                    finish();
                }
                else{
                    startActivity(new Intent(Flash_Screen.this,MainActivity.class));
                    finish();
                }
            }
        },5000);
    }
}
