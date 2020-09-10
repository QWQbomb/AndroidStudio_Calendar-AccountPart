package com.example.calendarview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase myDatabase;
    CalendarView calendarView;
    TextView myDate;
    private AFragment aFragment;
    private Button mBtnSave, mBtnTest;
    private BFragment bFragment;
    private ListView lv_account;
    private String date;

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_account = findViewById(R.id.accountList);
        /*切换到BFragment*/
        mBtnSave = findViewById(R.id.btn_save);
        mBtnTest = findViewById(R.id.testButton);
        /*button listeners*/
        //记一笔button
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bFragment == null) {
                    bFragment = new BFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.animator.slide_right_in,
                                R.animator.slide_right_out,
                                R.animator.slide_right_in,
                                R.animator.slide_right_out
                        ).replace(R.id.fl_container, bFragment)
                        .addToBackStack(null)
                        .commit();
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).commitAllowingStateLoss();*/

            }

        });

        //this test button is to test why the application will go wrong.
        /*mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                    List<AccountModel> allAccount = dataBaseHelper.getAll();

                    ArrayAdapter accountArrayAdapter = new ArrayAdapter<AccountModel>(MainActivity.this,
                            android.R.layout.simple_list_item_1, allAccount);
                    *//*lv_account.setAdapter(accountArrayAdapter);*//*

                    //测试数据是否正确读出
                    *//*Toast.makeText(MainActivity.this, allAccount.toString(), Toast.LENGTH_SHORT).show();*//*
            }
        });*/


        /*实例化Afragment*/
        /*aFragment = new AFragment();*/
        /*把Afragment添加到Activity中，记得调用commit*/
        /*getSupportFragmentManager().beginTransaction().add(R.id.fl_container,aFragment).commitAllowingStateLoss();*/

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                /*String date = (month + 1) + "/" + dayOfMonth + "/" + year;*/
                date = year + "-" + (month + 1) + "-" + dayOfMonth;
                /*测试设置listView*/

                myDate.setText(date);
                aFragment = AFragment.newInstance(date);
                /*使用setCustomAnimations添加Fragment的过渡动画*/
                getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.animator.slide_left_in,
                        R.animator.slide_left_out,
                        R.animator.slide_left_in,
                        R.animator.slide_left_out
                ).add(R.id.fl_container, aFragment).commitAllowingStateLoss();

                /*pull the date from database*/
                /*DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<AccountModel> allAccount = dataBaseHelper.getAll();
                Toast.makeText(MainActivity.this, allAccount.toString(), Toast.LENGTH_SHORT).show();*/

                getSupportFragmentManager().beginTransaction().remove(aFragment);//清空AFragment 防止出现重叠
            }
        });
    }

}