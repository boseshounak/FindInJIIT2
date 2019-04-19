package jr.minor2.jiit.com.findinjiit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jr.minor2.jiit.com.findinjiit.Details.Public_Data;

public class Adapter_tt extends RecyclerView.Adapter {

    Context context;
    ArrayList<String> time_slot;
    boolean vacant[];
    CircleImageView imageView;
    TextView textView;
    CardView cardView;
    String email,day;

    public Adapter_tt(Context context, ArrayList<String> time_slot, boolean[] vacant,String email,String day) {
        this.context = context;
        this.time_slot = time_slot;
        this.vacant = vacant;
        this.email=email;
        this.day=day;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            textView=(TextView)view.findViewById(R.id.tt_text);
            imageView=(CircleImageView) view.findViewById(R.id.tt_img);
            cardView=(CardView)view.findViewById(R.id.card3);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_tt,parent,false);
        return new Adapter_tt.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        textView.setText(time_slot.get(position));
        if(!vacant[position]){
            Picasso.with(context).load(R.drawable.ic_check_circle_black_24dp).centerCrop().fit().into(imageView);
            cardView.setBackgroundColor(Color.parseColor("#9dd194"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Public_Data.curr_time_slot=time_slot.get(position);
                    AlertDialog.Builder b=new AlertDialog.Builder(context);
                    b.setTitle("Confirmation");
                    b.setMessage("Booking confirmed Please check your inbox");
                    b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(i==DialogInterface.BUTTON_POSITIVE){
                                //Toast.makeText(context,email+" "+Public_Data.curr_teacher+" "+day+" "+time_slot.get(position),Toast.LENGTH_SHORT).show();
                                new Cloud_Handle(context).execute("confirm_book",email,Public_Data.curr_teacher,
                                        day,Public_Data.curr_time_slot);
                            }
                        }
                    });
                    b.create().show();
                }
            });
        }
        else if(vacant[position]){
            Picasso.with(context).load(R.drawable.ic_error_black_24dp).centerCrop().fit().into(imageView);
            cardView.setBackgroundColor(Color.parseColor("#d19494"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Faculty engaged Please choose another time slot",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return time_slot.size();
    }
}
