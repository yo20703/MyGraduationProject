package com.example.ge.myapplication123;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class backpack extends AppCompatActivity {

    Button btn2;
    TextView tv2;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    private GridView grid;
    private String[] text = {"a100","a101"} ;
   // private int[] imageId ={R.drawable.a100,R.drawable.a101,R.drawable.a102};
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    private Toolbar toolbar;
    public boolean onCreateOptionsMenu(Menu menu) {
        // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        // 取得點選項目的id
        int id = item.getItemId();

        // 依照id判斷點了哪個項目並做相應事件
        if (id == R.id.nav_home) {
            // 按下「設定」要做的事
            Toast.makeText(this, "首頁", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_attributes1) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "屬性表", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , attributes1.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_attributes) {
            // 按下「關於」要做的事
            Toast.makeText(this, "圖鑑", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , illustration.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_battle) {
            // 按下「關於」要做的事
            Toast.makeText(this, "快速對戰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , battle.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_main2) {
            // 按下「關於」要做的事
            Toast.makeText(this, "基礎教學", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , Main2Activity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_map) {
            // 按下「關於」要做的事
            Toast.makeText(this, "進階玩法", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , map.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_guide3) {
            // 按下「關於」要做的事
            Toast.makeText(this, "遊戲攻略", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this , guide2.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_guide4) {
            // 按下「關於」要做的事
            Toast.makeText(this, "活動訊息", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(backpack.this ,guide3.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);
        //CustomGrid adapter = new CustomGrid(backpack.this, text,text1);
        grid = (GridView) findViewById(R.id.grid);
        //grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //[+position] +的功用是?
                                            Toast.makeText(backpack.this, "你選取了" + text[+position], Toast.LENGTH_SHORT).show();
                                        }
                                    });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
//        getSupportActionBar().hide(); //隱藏標題
      //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        ;


        btn2=(Button)findViewById(R.id.button2);
        tv2=(TextView)findViewById(R.id.TV2);
        listItems = getResources().getStringArray(R.array.Attributes);
        checkedItems = new boolean[listItems.length];


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(backpack.this);
                mBuilder.setTitle("篩選");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                        if(isChecked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        tv2.setText(item);
                    }
                });

                mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("清空", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            tv2.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }


    protected void onResume() {

        super.onResume();

    }

}


