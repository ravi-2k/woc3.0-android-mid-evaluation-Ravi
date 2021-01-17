package com.example.da_timetable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.example.da_timetable.Utils.LetterImageView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String DAY_CHOSEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        initToolbar();

        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarMain);
        listView = (ListView)findViewById(R.id.lvMain);
        sharedPreferences = getSharedPreferences("CURR_DAY", MODE_PRIVATE);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Time-Table");
    }

    private void setupListView(){
        String[] week = getResources().getStringArray(R.array.Days);

        WeekAdapter adapter = new WeekAdapter(this, R.layout.main_activity_single_item, week);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        startActivity(new Intent(MainActivity.this, Today_activity.class));
                        sharedPreferences.edit().putString(DAY_CHOSEN, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(MainActivity.this, Today_activity.class));
                        sharedPreferences.edit().putString(DAY_CHOSEN, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(MainActivity.this, Today_activity.class));
                        sharedPreferences.edit().putString(DAY_CHOSEN, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(MainActivity.this, Today_activity.class));
                        sharedPreferences.edit().putString(DAY_CHOSEN, "Thursday").apply();
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(MainActivity.this, Today_activity.class));
                        sharedPreferences.edit().putString(DAY_CHOSEN, "Friday").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(MainActivity.this, Today_activity.class));
                        sharedPreferences.edit().putString(DAY_CHOSEN, "Saturday").apply();
                        break;
                    }
                    default:break;
                }
            }
        });

    }

    public class WeekAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] days = new String[]{};

        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.days = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = convertView.findViewById(R.id.ivLetter);
                holder.tvWeek = convertView.findViewById(R.id.tvMain);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(days[position].charAt(0));
            holder.tvWeek.setText(days[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvWeek;
        }
    }
}