package jr.minor2.jiit.com.findinjiit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import jr.minor2.jiit.com.findinjiit.Details.Public_Data;

public class My_Booking extends AppCompatActivity {
    public static RecyclerView recyclerView_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__booking);
        recyclerView_book=(RecyclerView)findViewById(R.id.book_recycler);
        recyclerView_book.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        My_Booking.recyclerView_book.setAdapter(new Adapter_book(this, Public_Data.book_fname,Public_Data.book_date,Public_Data.book_time));
    }

}
