package curtin.edu.shrinkage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShrinkageActivity extends AppCompatActivity {

    private ArrayList<Date> dates = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrinkage);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Date startDate = (Date) b.getSerializable("startDate");
        Date endDate = (Date) b.getSerializable("endDate");

        if(startDate.getDate() < endDate.getDate())
        {
            for (int i = startDate.getDate(); i <= endDate.getDate(); i++)
            {
                dates.add(new Date(startDate.getYear(), startDate.getMonth()+1, i));
            }
        }
        else //in the event the endDate is a date from the next month
        {
                //Calendar calendar = Calendar.getInstance();
            YearMonth yearMonth = YearMonth.of(startDate.getYear(), startDate.getMonth()+1);
                int maxDays = yearMonth.lengthOfMonth();
                Log.d("testing max days", String.valueOf(maxDays)+"test");

                for(int i = startDate.getDate(); i <= maxDays; i++)
                {
                    dates.add(new Date(startDate.getYear(), startDate.getMonth()+1, i));
                }

                for(int i = 1; i <= endDate.getDate(); i++)
                {
                    dates.add(new Date(endDate.getYear(), endDate.getMonth()+1, i));
                }
        }

        Bundle bu = new Bundle();
        bu.putSerializable("datesList", dates);
        bu.putString("test", "test String");
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.selectDateContainer, SelectDateFragment.class, bu)
                .commit();
    }

    public ArrayList<Date> getDatesList()
    {
        return dates;
    }
}