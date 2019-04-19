package jr.minor2.jiit.com.findinjiit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_book extends RecyclerView.Adapter {
    TextView fac,dat;
    Context context;
    ArrayList<String> date,fname,time;

    public Adapter_book(Context context, ArrayList<String> fname, ArrayList<String> date,ArrayList<String> time) {
        this.context = context;
        this.date = date;
        this.fname = fname;
        this.time = time;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            fac=(TextView)view.findViewById(R.id.book_fname);
            dat=(TextView)view.findViewById(R.id.book_date);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_booking,parent,false);
        return new Adapter_book.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        fac.setText(fname.get(position));
        dat.setText(date.get(position)+" "+time.get(position));
    }

    @Override
    public int getItemCount() {
        return date.size();
    }
}
