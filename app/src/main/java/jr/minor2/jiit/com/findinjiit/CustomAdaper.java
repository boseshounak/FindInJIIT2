package jr.minor2.jiit.com.findinjiit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;
import mehdi.sakout.fancybuttons.FancyButton;

public class CustomAdaper extends BaseAdapter {
    Context context;
    ArrayList<String> images,colors;
    LayoutInflater inflater;

    public CustomAdaper(Context context, ArrayList<String> images,ArrayList<String> colors) {
        this.context = context;
        this.images = images;
        this.colors=colors;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.grid_col,null); // inflate the layout
        FancyButton btn=(FancyButton)view.findViewById(R.id.btn_grid);
        btn.setText(images.get(i));
        btn.setBackgroundColor(Color.parseColor(colors.get(i)));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Public_Data.branch=images.get(i);
                //context.startActivity(new Intent(context,Teacher_S_Act.class));
                 new Cloud_Handle(context).execute("fetch_teachers",Public_Data.campus,Public_Data.branch);
                //Toast.makeText(context,Public_Data.campus+" "+Public_Data.branch,Toast.LENGTH_SHORT).show();
            }
        });
        //btn.setBackgroundColor(Color.parseColor(""));
        return view;
    }
}
