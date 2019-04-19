package jr.minor2.jiit.com.findinjiit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;

public class Teacher_S_Act extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> branch,campus,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__s_);
        recyclerView=(RecyclerView)findViewById(R.id.recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        branch=new ArrayList<>();
        campus=new ArrayList<>();
        name=new ArrayList<>();



    }

    @Override
    protected void onStart() {
        super.onStart();
        branch.clear();
        campus.clear();
        name.clear();
        initialize();
        recyclerView.setAdapter(new Adapter_ts(this,branch,campus,name));
    }

    private void initialize() {
        for(int i = 0; i< Public_Data.teacher_short_list.size(); i++){
            branch.add(Public_Data.teacher_short_list.get(i).branch);
            campus.add(Public_Data.teacher_short_list.get(i).campus);
            name.add(Public_Data.teacher_short_list.get(i).name);
        }
        //Toast.makeText(this,Public_Data.teacher_short_list.get(0).name,Toast.LENGTH_SHORT).show();
    }
}
