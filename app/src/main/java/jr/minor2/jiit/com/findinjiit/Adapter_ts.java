package jr.minor2.jiit.com.findinjiit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;

public class Adapter_ts extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> arr_branch,arr_campus,arr_name;
    TextView tname,tcamp,tbranch;
    public Adapter_ts(Context context, ArrayList<String> arr_branch, ArrayList<String> arr_campus, ArrayList<String> arr_name) {
        this.context = context;
        this.arr_branch = arr_branch;
        this.arr_campus = arr_campus;
        this.arr_name = arr_name;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            tname=(TextView)view.findViewById(R.id.tname);
            tcamp=(TextView)view.findViewById(R.id.tcamp);
            tbranch=(TextView) view.findViewById(R.id.tbranch);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_ts,parent,false);
        return new Adapter_ts.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        tbranch.setText(arr_branch.get(position));
        tcamp.setText(arr_campus.get(position));
        tname.setText(arr_name.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Public_Data.curr_teacher=Public_Data.teacher_short_list.get(position).name;
                context.startActivity(new Intent(context,Choice_Filling.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr_name.size();
    }
}
