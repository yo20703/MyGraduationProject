package com.example.ge.myapplication123;
import android.app.Activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

import static com.example.ge.myapplication123.R.id.button;

public class battle extends AppCompatActivity {
    private static Toast toast;
    private static void makeTextAndShow(final Context context, final String text, final int duration) {
        if (toast == null) {
            //如果還沒有用過makeText方法，才使用
            toast = android.widget.Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }
    private ListView listView;
    private ListView listView2;
    private ListView listView3;
    private String plevel[] = new String[]{"第一彈", "第二彈", "第三彈", "第四彈", "第五彈", "第六彈", "第七彈", "第八彈", "第九彈", "第十彈", "第十一彈", "第十二彈", "第十三彈"};
    private String plevel2[] = new String[]{"48", "48", "48", "48", "48", "48", "48", "48", "48", "48", "48", "48", "48"};
    private String plevel3[] = new String[]{plevel2[0] + "/48", plevel2[1] + "/48", plevel2[2] + "/48", plevel2[3] + "/48", plevel2[4] + "/48", plevel2[5] + "/48", plevel2[6] + "/48", plevel2[7] + "/48", plevel2[8] + "/48", plevel2[9] + "/48", plevel2[10] + "/48", plevel2[11] + "/48", plevel2[12] + "/48"};
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
            intent.setClass(battle.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_attributes1) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "屬性表", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, attributes1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_attributes) {
            // 按下「關於」要做的事
            Toast.makeText(this, "圖鑑", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, illustration.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_battle) {
            // 按下「關於」要做的事
            Toast.makeText(this, "快速對戰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, battle.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_main2) {
            // 按下「關於」要做的事
            Toast.makeText(this, "基礎教學", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_map) {
            // 按下「關於」要做的事
            Toast.makeText(this, "進階玩法", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, map.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.nav_guide3) {
            // 按下「關於」要做的事
            Toast.makeText(this, "遊戲攻略", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, guide2.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_guide4) {
            // 按下「關於」要做的事
            Toast.makeText(this, "活動訊息", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(battle.this, guide3.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listView = (ListView) findViewById(R.id.Level);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //getSupportActionBar().hide(); //隱藏標題
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plevel);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plevel3);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                makeTextAndShow(battle.this, plevel[position], Toast.LENGTH_SHORT);
                battle2 mybp = new battle2();
                int bp = position + 1;
                int state;
                state=partstate(String.valueOf(position+1));
                Log.d("state", String.valueOf(state));
                //切換Activity
                Intent intent = new Intent();
                if(state==0)
                    intent.setClass(battle.this, battle2.class);
                else
                    intent.setClass(battle.this, random.class);
                //new一個Bundle物件，並將要傳遞的資料傳入
                Bundle bundle = new Bundle();
                bundle.putInt("mybp", bp);//傳遞Double
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });


    }

    public int partstate(String part){
        int state;
        DbManager manager = new DbManager(battle.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "SELECT * from Pokemon WHERE Part = '"+part+"'";
        Cursor c = db.rawQuery(TED,null);
        c.moveToFirst();
        String[] colNames = c.getColumnNames();
        int it = c.getCount();
        int[] bagstate = new int[c.getCount()];
        for(int i=0;i<it;i++){
            if(c.getInt(c.getColumnIndex(colNames[15]))==1){
                return 0;
            }
            c.moveToNext();
        }
        return 1;
    }
}



