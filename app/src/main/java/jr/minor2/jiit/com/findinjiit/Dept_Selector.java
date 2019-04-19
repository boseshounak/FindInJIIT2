package jr.minor2.jiit.com.findinjiit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;

public class Dept_Selector extends AppCompatActivity {
    GridView gridView;
    ArrayList<String> images,color,images2,color2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept__selector);
        gridView=(GridView)findViewById(R.id.grid);
        images=new ArrayList<>();
        color=new ArrayList<>();
        images2=new ArrayList<>();
        color2=new ArrayList<>();
        pic_initialize();
        gridView.setAdapter(new CustomAdaper(this,images2,color2));
        Public_Data.branch="";
        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Public_Data.branch=images.get(i);
                Toast.makeText(getApplicationContext(),Public_Data.campus+" "+Public_Data.branch,Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void pic_initialize() {
        images.add("CSE");
        images.add("ECE");
        images.add("P/M");
        images.add("HSS");
        images.add("IT");
        images.add("Biotec");


        color.add("#4286f4");
        color.add("#22b2f4");
        color.add("#09efe8");
        color.add("#3a37ef");
        color.add("#62c4b3");
        color.add("#9cbbed");

        if(Public_Data.campus.equals("Sector 128")){
            for(int i=0; i<4; i++){
                color2.add(color.get(i));
                images2.add(images.get(i));
            }
        }
        else{
            images2=images;
            color2=color;
        }
    }
}
