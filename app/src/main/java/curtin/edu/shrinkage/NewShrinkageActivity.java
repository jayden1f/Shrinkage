package curtin.edu.shrinkage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class NewShrinkageActivity extends AppCompatActivity {

    private Date startDate;
    private Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shrinkage);

        Button selectStartDate = findViewById(R.id.selectStartDate);
        Button selectEndDate = findViewById(R.id.selectEndDate);
        Button beginShrinkage = findViewById(R.id.beginShrinkage);
        TextView endDateView = findViewById(R.id.endDateView);
        TextView startDateView = findViewById(R.id.startDateView);

        selectStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment startDateFrag = new DatePickerFragment();
                startDateFrag.show(getSupportFragmentManager(), "datePicker");
                getSupportFragmentManager().setFragmentResultListener("requestKey", NewShrinkageActivity.this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        startDate = (Date)result.getSerializable("date");
                        Log.d("testing acti", startDate.toString());
                        startDateView.setText("Start Date:"+startDate);
                    }
                });
            }
        });

        selectEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment startDateFrag = new DatePickerFragment();
                startDateFrag.show(getSupportFragmentManager(), "datePicker");
                getSupportFragmentManager().setFragmentResultListener("requestKey", NewShrinkageActivity.this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        endDate = (Date)result.getSerializable("date");
                        Log.d("testing acti", endDate.toString());
                        endDateView.setText("End Date:"+endDate);
                    }
                });
            }
        });

        beginShrinkage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putSerializable("startDate", startDate);
                b.putSerializable("endDate", endDate);
                Intent i = new Intent(NewShrinkageActivity.this, ShrinkageActivity.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}