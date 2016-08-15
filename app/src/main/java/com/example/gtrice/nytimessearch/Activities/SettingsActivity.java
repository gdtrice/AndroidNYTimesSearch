package com.example.gtrice.nytimessearch.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gtrice.nytimessearch.Models.SearchSettings;
import com.example.gtrice.nytimessearch.R;

import java.util.ArrayList;
import java.util.Calendar;


public class SettingsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static int settingsResponseCode = 333;
    EditText tvBeginDate;
    Spinner spnSortOrder;
    String sortOrder;

    CheckBox cbSports;
    CheckBox cbFashion;
    CheckBox cbArts;

    Calendar c;
    ArrayList<String> newsDesk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spnSortOrder = (Spinner) findViewById(R.id.spnSortOrder);
        spnSortOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortOrder = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cbArts = (CheckBox) findViewById(R.id.cbArts);
        cbFashion = (CheckBox) findViewById(R.id.cbFashion);
        cbSports = (CheckBox) findViewById(R.id.cbSports);

        newsDesk = new ArrayList<>();
        cbArts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !newsDesk.contains("Arts")) {
                    newsDesk.add("Arts");
                }
            }
        });

        cbSports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !newsDesk.contains("Sports")) {
                    newsDesk.add("Sports");
                }
            }
        });

        cbFashion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !newsDesk.contains("Fashion & Style")) {
                    newsDesk.add("Fashion & Style");
                }
            }
        });
    }

    public void onSettingsSaveClicked(View view) {
        tvBeginDate = (EditText) findViewById(R.id.tvBeginDate);
        SearchSettings settings = new SearchSettings(sortOrder, c.getTime(), newsDesk);
        Intent i = new Intent();
        i.putExtra("settings", settings);
        setResult(settingsResponseCode, i);
        finish();
    }

    public void onBeginDateTextClick(View view) {
        //launch dialog fragment
        DatePickerFragment dtFragment = new DatePickerFragment();
        dtFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        tvBeginDate = (EditText) findViewById(R.id.tvBeginDate);
        tvBeginDate.setText(c.getTime().toString());
    }
}
