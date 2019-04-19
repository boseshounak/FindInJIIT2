package jr.minor2.jiit.com.findinjiit;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;

public class TimeTable extends AppCompatActivity {
    HashSet<Integer> hashSet;
    boolean vac[];
    RecyclerView recyclerView;
    ArrayList<String> slots;
    String day,email;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        recyclerView=(RecyclerView)findViewById(R.id.tt_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Public_Data.curr_time_slot="";
        slots=new ArrayList<>();
        initialize_list();
        hashSet=new HashSet<>();
        vac=new boolean[9];
        for(int i=0; i<8; i++)
            vac[i]=true;
        initialize_date();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        email=sharedPreferences.getString("user_email","shoun.official@gmail.com");
        day=getIntent().getExtras().getString("day");
        //Toast.makeText(this,day,Toast.LENGTH_SHORT).show();
        recyclerView.setAdapter(new Adapter_tt(this,slots,vac,email,day));
    }

    private void initialize_list() {
        slots.add("9:00AM - 9:50AM");
        slots.add("9:55AM- 10:45AM");
        slots.add("10:50AM- 11:40AM");
        slots.add("11:45AM- 12:35PM");
        slots.add("12:40PM- 1:30PM");
        slots.add("1:35PM- 2:25PM");
        slots.add("2:30PM- 3:20PM");
        slots.add("3:25PM- 4:15PM");
        slots.add("4:20PM- 5:10PM");
    }

    private void initialize_date() {
        int vacant=0;
        while (true){
            if(vacant>4)
                break;
            int no=generate();
            if(!hashSet.contains(no)){
                vac[no]=false;
                hashSet.add(no);
                vacant++;
            }
        }
        String temp="";
        for(int i=0; i<8; i++){
            temp+=Boolean.toString(vac[i]);
        }
        //Toast.makeText(this,temp,Toast.LENGTH_SHORT).show();
    }

    private int generate() {
        Random rand = new Random();
        int randomNum = rand.nextInt((7 - 0) + 1) + 0;
        return randomNum;
    }
}
