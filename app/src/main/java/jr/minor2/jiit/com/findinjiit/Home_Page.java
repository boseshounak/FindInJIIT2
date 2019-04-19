package jr.minor2.jiit.com.findinjiit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;
import mehdi.sakout.fancybuttons.FancyButton;
import ss.com.bannerslider.Slider;

public class Home_Page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Spinner.OnItemSelectedListener, View.OnClickListener {
    SharedPreferences sharedPreferences;
    TextView nav_name,nav_email;
    Slider slider;
    ImageView imageView2;
    Spinner spinner;
    FancyButton btn;
    ArrayList<String> campus;
    View navHeader;
    TextView txtName,txtWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        imageView2=(ImageView)findViewById(R.id.banner);
        Picasso.with(this).load(R.drawable.bannerpic1).fit().centerCrop().into(imageView2);
        //Slider.init(new ImageLoading(this));
        //slider=(Slider)findViewById(R.id.banner_slider1);
        //slider.setAdapter(new ImageSlider());
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        btn=(FancyButton)findViewById(R.id.btn_gocampus);
        btn.setOnClickListener(this);
        spinner=(Spinner)findViewById(R.id.spinner1);
        campus=new ArrayList<>();
        initialize_array();
        spinner.setAdapter(new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,campus));
        spinner.setOnItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.nav_name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.nav_email);
        print_hello();
    }

    private void initialize_array() {
        campus.add("Sector 128");
        campus.add("Sector 62");
    }

    private void print_hello() {
        String name=sharedPreferences.getString("user_name","Shounak Bose");
        String mail=sharedPreferences.getString("user_email","gamerkanpur@gmail.com");
        Toast.makeText(this,"Welcome "+name,Toast.LENGTH_SHORT).show();
        txtName.setText(name);
        txtWebsite.setText(mail);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.my_appointments){
            String email=sharedPreferences.getString("user_email","shoun.official@gmail.com");
            new Cloud_Handle(this).execute("get_book",email);
        }
        else if(id==R.id.webkiosk){
            startActivity(new Intent(this,Webkiosk.class));
        }
        else if(id==R.id.signout_nav){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("login_status",false);
            editor.commit();
            startActivity(new Intent(this,MainActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Public_Data.campus=campus.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Public_Data.campus=campus.get(0);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,Dept_Selector.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Public_Data.branch="";

    }
}
