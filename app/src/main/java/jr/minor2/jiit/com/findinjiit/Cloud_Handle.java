package jr.minor2.jiit.com.findinjiit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import dmax.dialog.SpotsDialog;
import jr.minor2.jiit.com.findinjiit.Details.Public_Data;
import jr.minor2.jiit.com.findinjiit.Details.Teacher_Short;


public class Cloud_Handle extends AsyncTask implements DialogInterface.OnClickListener {
    Context context;
    AlertDialog alertDialog;
    String choice;
    SharedPreferences sharedPreferences;
    String link,encode,temp_data,read_data,user_name,user_mail,sign_mail,sign_user;

    public Cloud_Handle(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog=new SpotsDialog(context,"Connecting");
        alertDialog.show();
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
        Public_Data.book_fname.clear();
        Public_Data.book_date.clear();
        Public_Data.book_time.clear();
        Public_Data.teacher_short_list.clear();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        choice=objects[0].toString();
        link="https://phpandroid.000webhostapp.com/TrackJIIT/scripts.php";
        if(choice.equals("login")){
            user_mail=objects[1].toString();
            String pass=objects[2].toString();
            encode=URLEncoder.encode("type")+"="+URLEncoder.encode("login")+"&"+
                    URLEncoder.encode("user_id")+"="+URLEncoder.encode(user_mail)+"&"+
                    URLEncoder.encode("pass")+"="+URLEncoder.encode(pass);
        }
        else if(choice.equals("signup")){
            String user=objects[1].toString();
            String pass=objects[2].toString();
            String name=objects[3].toString();
            sign_mail=user;
            sign_user=name;
            user_name=name;
            encode=URLEncoder.encode("type")+"="+URLEncoder.encode("signup")+"&"+
                    URLEncoder.encode("user_id")+"="+URLEncoder.encode(user)+"&"+
                    URLEncoder.encode("pass")+"="+URLEncoder.encode(pass)+"&"+
                    URLEncoder.encode("user_name")+"="+URLEncoder.encode(name);
        }
        else if(choice.equals("fetch_teachers")){
            String campus=objects[1].toString();
            String branch=objects[2].toString();
            encode=URLEncoder.encode("type")+"="+URLEncoder.encode("fetch_teachers")+"&"+
                    URLEncoder.encode("campus")+"="+URLEncoder.encode(campus)+"&"+
                    URLEncoder.encode("branch")+"="+URLEncoder.encode(branch);
        }
        else if(choice.equals("confirm_book")){
            String sname=objects[1].toString();
            String fname=objects[2].toString();
            String day=objects[3].toString();
            String time_slot=objects[4].toString();

            encode=URLEncoder.encode("type")+"="+URLEncoder.encode("confirm_book")+"&"+
                    URLEncoder.encode("email")+"="+URLEncoder.encode(sname)+"&"+
                    URLEncoder.encode("fname")+"="+URLEncoder.encode(fname)+"&"+
                    URLEncoder.encode("day")+"="+URLEncoder.encode(day)+"&"+
                    URLEncoder.encode("time_slot")+"="+URLEncoder.encode(time_slot);
        }
        else if(choice.equals("get_book")){
            String email=objects[1].toString();
            encode=URLEncoder.encode("type")+"="+URLEncoder.encode("get_book")+"&"+
                    URLEncoder.encode("email")+"="+URLEncoder.encode(email);
        }
        read_data="";
        try {
            URL url= null;
            url = new URL(link);
            HttpURLConnection httpURLConnection= null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream out = null;
            out = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= null;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            bufferedWriter.write(encode);
            bufferedWriter.flush();
            bufferedWriter.close();
            out.close();
            InputStream inputStream= null;
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            while((temp_data=bufferedReader.readLine())!=null){
                read_data+=temp_data;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        alertDialog.dismiss();
        //Toast.makeText(context,read_data,Toast.LENGTH_SHORT).show();
        if(!read_data.equals("")){
            if(choice.equals("login")){
                if(read_data.equals("not_exists")){
                    //Toast.makeText(context,"No Account Found with this Email",Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,SignUp_Act.class));
                }
                else{
                    SharedPreferences.Editor op= sharedPreferences.edit();
                    op.putBoolean("login_status",true);
                    op.putString("user_name",read_data);
                    //op.putString("user_email",user_mail);
                    op.commit();
                    context.startActivity(new Intent(context,Home_Page.class));
                }
            }
            else if(choice.equals("signup")){
                if(read_data.equals("exists")){
                    Toast.makeText(context,"Email Already registered with Us",Toast.LENGTH_SHORT).show();
                    //context.startActivity(new Intent(context,SignUp_Act.class));
                }
                else if(read_data.equals("success")){
                    SharedPreferences.Editor op= sharedPreferences.edit();
                    op.putBoolean("login_status",true);
                    op.putString("user_name",user_name);
                    op.putString("user_email",sign_mail);
                    op.commit();
                    context.startActivity(new Intent(context,Home_Page.class));
                }
                else{
                    Toast.makeText(context,"Error 845",Toast.LENGTH_SHORT).show();
                }
            }
            else if(choice.equals("fetch_teachers")){
                String temp[]=read_data.split("@");
                for(int i=0; i<temp.length; i++){
                    Teacher_Short ts=new Teacher_Short();
                    String temp2[]=temp[i].split("#");
                    ts.campus=temp2[0];
                    ts.branch=temp2[1];
                    ts.name=temp2[2];
                    Public_Data.teacher_short_list.add(ts);
                }
                context.startActivity(new Intent(context,Teacher_S_Act.class));
            }
            else if(choice.equals("get_book")){
                String temp[]=read_data.split("@");
                for(int i=0; i<temp.length; i++){
                    String temp2[]=temp[i].split("#");
                    Public_Data.book_fname.add(temp2[0]);
                    Public_Data.book_date.add(temp2[1]);
                    Public_Data.book_time.add(temp2[2]);
                    //Toast.makeText(context,Public_Data.book_fname.get(i),Toast.LENGTH_SHORT).show();
                }
                context.startActivity(new Intent(context,My_Booking.class));


                //Toast.makeText(context,read_data,Toast.LENGTH_SHORT).show();
            }
            else if(choice.equals("confirm_book")){
                if(read_data.equals("success")){
                    context.startActivity(new Intent(context,Home_Page.class));
                }
                else{
                    Toast.makeText(context,"Unknown Error",Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(context,"Check Internet Connectivity",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i==DialogInterface.BUTTON_POSITIVE){
            context.startActivity(new Intent(context,Home_Page.class));
        }
    }
}
