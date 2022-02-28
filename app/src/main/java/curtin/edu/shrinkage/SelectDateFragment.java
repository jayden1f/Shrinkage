package curtin.edu.shrinkage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SelectDateFragment extends Fragment {

    private ArrayList<Date> datesList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    DateAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_date, container, false);
        ArrayList<Button> dateButtons = new ArrayList<>();
        datesList = ((ShrinkageActivity)getActivity()).getDatesList();

        recyclerView = view.findViewById(R.id.datesList);
        setAdapter();

/*
        dateButtons.add(view.findViewById(R.id.date1));
        dateButtons.add(view.findViewById(R.id.date2));
        dateButtons.add(view.findViewById(R.id.date3));
        dateButtons.add(view.findViewById(R.id.date4));
        dateButtons.add(view.findViewById(R.id.date5));
        dateButtons.add(view.findViewById(R.id.date6));
        dateButtons.add(view.findViewById(R.id.date7));

        for(int i = 0; i < 7; i++)
        {
            Date curDate = (Date) datesList.get(i);
            String str = curDate.getDate()+"/"+curDate.getMonth();
            Log.d("testing str", str);
            dateButtons.get(i).setText(str);
        }
        */

        return view;

    }

    public void setAdapter()
    {
        adapter = new DateAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {

        Button dateButton;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            dateButton = itemView.findViewById(R.id.dateButton);
        }
    }

    public class DateAdapter extends RecyclerView.Adapter<DateViewHolder>
    {

        @NonNull
        @Override
        public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.date_entry, parent, false);
            DateViewHolder dateVH = new DateViewHolder(view);

            return dateVH;
        }

        @Override
        public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            holder.dateButton.setBackgroundColor(color);
            String str = datesList.get(position).getDate()+"/"+datesList.get(position).getMonth();
            holder.dateButton.setText(str);

            holder.dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), ScannerActivity.class));
                    //startActivity(new Intent(getActivity(), ScannerActivity2.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return datesList.size();
        }
    }
}