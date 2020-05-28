package com.example.ge.myapplication123;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button3;
    private Button button4;
    private Button button5;
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
            return true;
        }
        else if (id == R.id.nav_attributes1) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "屬性表", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , attributes1.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_attributes) {
            // 按下「關於」要做的事
            Toast.makeText(this, "圖鑑", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , illustration.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_battle) {
            // 按下「關於」要做的事
            Toast.makeText(this, "快速對戰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , battle.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_main2) {
            // 按下「關於」要做的事
            Toast.makeText(this, "基礎教學", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , Main2Activity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_map) {
            // 按下「關於」要做的事
            Toast.makeText(this, "進階玩法", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , map.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_guide3) {
            // 按下「關於」要做的事
            Toast.makeText(this, "遊戲攻略", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , guide2.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_guide4) {
            // 按下「關於」要做的事
            Toast.makeText(this, "活動訊息", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,guide3.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_no) {
            // 按下「關於」要做的事
            Toast.makeText(this, "關於TRETTA資料站", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,Main3Activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager manager = new DbManager(MainActivity.this);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
       // getSupportActionBar().hide(); //隱藏標題
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setMessage("\t\t           本程式僅供學術研究使用\t\t\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t恕不開放下載\t\t");
        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton("不同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , attributes1.class);
                startActivity(intent);

            }

        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , illustration.class);
                startActivity(intent);
            }

        });
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , battle.class);
                startActivity(intent);
            }

        });
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , guide.class);
                startActivity(intent);
            }


        });
    }

    protected void Stop(){
        super.onStop();
    }
}
