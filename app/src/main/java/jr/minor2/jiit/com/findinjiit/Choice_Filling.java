package jr.minor2.jiit.com.findinjiit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;
import mehdi.sakout.fancybuttons.FancyButton;

public class Choice_Filling extends AppCompatActivity implements View.OnClickListener {
    TextView sname,fname;
    MaterialEditText enroll;
    Spinner spinner,date;
    ArrayList<String> local_branch,day;
    String lbranch,lday;
    FancyButton btn;
    Calendar c;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice__filling);
        spinner=(Spinner)findViewById(R.id.fill_spinner);
        date=(Spinner) findViewById(R.id.date_spinner);
        enroll=(MaterialEditText)findViewById(R.id.fill_enroll);
        sname=(TextView)findViewById(R.id.fill_name);
        fname=(TextView)findViewById(R.id.fill_fname);
        btn=(FancyButton)findViewById(R.id.btn_book);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        local_branch=new ArrayList<>() ;
        day=new ArrayList<>() ;
        initialize();
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,local_branch));
        date.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,day));

        btn.setOnClickListener(this);
        lbranch=local_branch.get(0);
        lday=day.get(0);
        date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lday=day.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                lday=day.get(0);
            }
        });

    }

    private void initialize() {
        local_branch.add("CSE");
        local_branch.add("ECE");

        day.add("Monday");
        day.add("Tueday");
        day.add("Wednesday");
        day.add("Thursday");
        day.add("Friday");
    }


    @Override
    public void onClick(View view) {
        String roll=enroll.getText().toString().trim();
        if(roll.length()<8){
            Toast.makeText(this,"Invalid Enroll",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(this,TimeTable.class);
            intent.putExtra("day",lday);
            startActivity(intent);
        }
        //Toast.makeText(this,lday,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        String name=sharedPreferences.getString("user_name","Kushgra");
        sname.setText("Students name- "+name);
        fname.setText("Faculty's name- "+ Public_Data.curr_teacher);
    }
}
