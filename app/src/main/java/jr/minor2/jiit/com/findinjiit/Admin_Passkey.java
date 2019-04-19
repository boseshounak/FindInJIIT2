package jr.minor2.jiit.com.findinjiit;

import android.content.Intent;
import android.os.VibrationEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;
import mehdi.sakout.fancybuttons.FancyButton;

public class Admin_Passkey extends AppCompatActivity implements View.OnClickListener {
    MaterialEditText editText;
    FancyButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__passkey);
        editText=(MaterialEditText)findViewById(R.id.admin_pass);
        btn=(FancyButton)findViewById(R.id.admin_login);
        btn.setOnClickListener(this);
        Public_Data.admin_teacher="";
    }

    @Override
    public void onClick(View view) {
        String teacher=editText.getText().toString();
        if(teacher.equals("1234")){
            Public_Data.admin_teacher="Dr. Satyendra Kumar";
        }
        else if(teacher.equals("5678")){
            Public_Data.admin_teacher="Dr. Jitendra Mohan";
        }
        else{
            Public_Data.admin_teacher="Dr. Santosh Kumar Verma";
        }
        startActivity(new Intent(this,Teacher_Report.class));
        //Toast.makeText(this,Public_Data.admin_teacher,Toast.LENGTH_SHORT).show();
    }
}
