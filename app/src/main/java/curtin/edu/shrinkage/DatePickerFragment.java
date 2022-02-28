package curtin.edu.shrinkage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    //public static Date date = new Date();
    @Override
    public Dialog onCreateDialog(Bundle b)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
    {
        i1 = i1+1;
        //Log.d("testing date:", String.valueOf(datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear()));
        Date date = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth(), 1, 1, 1);
        Bundle result = new Bundle();
        result.putSerializable("date", date);
        getParentFragmentManager().setFragmentResult("requestKey", result);
        Log.d("testing date:", date.toString());

    }
}