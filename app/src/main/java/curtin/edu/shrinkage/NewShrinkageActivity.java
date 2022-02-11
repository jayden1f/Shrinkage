package curtin.edu.shrinkage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class NewShrinkageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shrinkage);

        Button selectStartDate = findViewById(R.id.selectStartDate);
        TextView startDateView = findViewById(R.id.startDateView);

        selectStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment startDateFrag = new DatePickerFragment();
                startDateFrag.show(getSupportFragmentManager(), "datePicker");
                getSupportFragmentManager().setFragmentResultListener("requestKey", NewShrinkageActivity.this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String startDate = result.getString("date");
                        Log.d("testing acti", startDate.toString());
                        startDateView.setText("Start Date:"+startDate);
                    }
                });
            }
        });
    }
}