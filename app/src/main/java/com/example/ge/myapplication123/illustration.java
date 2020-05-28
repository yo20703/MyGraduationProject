package com.example.ge.myapplication123;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;
import java.lang.reflect.Field;
public class illustration extends AppCompatActivity {
    private static Toast toast;
    Button btn2;
    TextView tv2;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    private int c;

    private String bag="";
    private GridView grid;
    private String SearchStr = "";
    private String SearchAttributes;
    private int IT= 0;
    private String[] Itentext;
    private int[] Itenimage;
    private String[] Itenarr=new String[16];
    private String[] SearchAttributesItem = new String[18];
    private String[] SearchPartItem = new String[15];
    private String  SearchPart="";
    private String[] Partarr ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    private boolean check[] = new boolean[18];
    private String[] ATBarr = new String[18];
    private Boolean[] pcheck = new Boolean[15];
    private String[] PSelect = {"PokemonNumber", "Part", "Star", "Hp", "Atk","Def","Spd"};
    private String Pstring=" ORDER BY PokemonNumber ";
    private String SBBB = " ASC";
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;
    CheckBox checkBox10;
    CheckBox checkBox11;
    CheckBox checkBox12;
    CheckBox checkBox13;
    CheckBox checkBox14;
    CheckBox checkBox15;
    CheckBox checkBox16;
    CheckBox checkBox17;
    CheckBox checkBox18;
    CheckBox pcheckBox1;
    CheckBox pcheckBox2;
    CheckBox pcheckBox3;
    CheckBox pcheckBox4;
    CheckBox pcheckBox5;
    CheckBox pcheckBox6;
    CheckBox pcheckBox7;
    CheckBox pcheckBox8;
    CheckBox pcheckBox9;
    CheckBox pcheckBox10;
    CheckBox pcheckBox11;
    CheckBox pcheckBox12;
    CheckBox pcheckBox13;
    CheckBox pcheckBox14,pcheckBox15;
    CheckBox pcheckBoxbag;

    TextView NUMBER2 ;
    TextView NAME2 ;
    TextView STAR2 ;
    TextView ATTRIBUTES2 ;
    TextView HP2 ;
    TextView ATK2 ;
    TextView DEF2 ;
    TextView SPD2 ;
    TextView SKL12 ;
    TextView SKL22 ;
    TextView SKL32 ;
    ImageView POKEMON;
    Spinner SP1;
    Button SB;
    Boolean SBB = true;
    Button BAG;
    Button all;
    Button unall;
    private boolean BAGG = false;

    private String BAGstring = "";
    TextView hit1,hit2,hit3;
    private String[] hit = new String[3];
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
            intent.setClass(illustration.this , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_attributes1) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "屬性表", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this , attributes1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_attributes) {
            // 按下「關於」要做的事
            Toast.makeText(this, "圖鑑", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this , illustration.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_battle) {
            // 按下「關於」要做的事
            Toast.makeText(this, "快速對戰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this , battle.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_main2) {
            // 按下「關於」要做的事
            Toast.makeText(this, "基礎教學", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this , Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_map) {
            // 按下「關於」要做的事
            Toast.makeText(this, "進階玩法", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this , map.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_guide3) {
            // 按下「關於」要做的事
            Toast.makeText(this, "遊戲攻略", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this , guide2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_guide4) {
            // 按下「關於」要做的事
            Toast.makeText(this, "活動訊息", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(illustration.this ,guide3.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //第幾彈按鈕
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illustration);
        bag=" AND bag = 0 ";
        all = (Button)findViewById(R.id.all);
        unall = (Button)findViewById(R.id.unall);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        SB = (Button)findViewById(R.id.SB);
        SB.setBackgroundColor(Color.parseColor("#ffffff"));
        SP1 = (Spinner)findViewById(R.id.SP);
        ArrayAdapter<CharSequence> PList = ArrayAdapter.createFromResource(illustration.this,
                R.array.SPARR, android.R.layout.simple_spinner_dropdown_item);
        SP1.setAdapter(PList);
        for (int i = 0; i < SearchAttributesItem.length; i++) {
            SearchAttributesItem[i] = "";
            check[i] = true;
            ATBarr = getResources().getStringArray(R.array.Attributes);
        }
        for(int i =0;i<15;i++){
            pcheck[i] = false;
        }
        pcheck[0] = true;
        EditText SearchEdtext = (EditText) findViewById(R.id.et_Name);
        SearchEdtext.addTextChangedListener(textWatcher);
        //全選按鈕
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allcheck();
            }
        });
        unall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unallcheck();
            }
        });
//        getSupportActionBar().hide(); //隱藏標題
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        //進入圖鑑時預先載入所有PokemonName
        getall();
        SB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SBB == true){
                    SB.setText("↓");
                    SBBB = " DESC";
                    getall();
                    SBB = false;
                    Log.d("QWE", String.valueOf(SBB));
                }
                else{
                    SB.setText("↑");
                    SBBB = " ASC";
                    getall();
                    SBB = true;
                    Log.d("QWE", String.valueOf(SBB));
                }
            }
        });
        SP1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pstring =" ORDER BY "+ PSelect[position];
                getall();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String Item = Itentext[position];
                int bagstate=0;
                getItemtext(Item);
                bagstate = BAGstate(Item);
                if(bagstate == BAGstate(Item)){
                    inBAG(Item);
                }
                else{
                    delBAG(Item);
                }
                return false;
            }
        });
        //點擊gridviewItem載入Layout
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int bagstate=0;
                AlertDialog .Builder mBuilder = new AlertDialog.Builder(illustration.this);

                View mView = getLayoutInflater().inflate(R.layout.activity_illustration2,null);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                final String Item = Itentext[position]; //該layout的PokemonNumber EX:9_00
                int imageitem2 = Itenimage[position];
                BAG = (Button)mView.findViewById(R.id.Bag);
                POKEMON= (ImageView)mView.findViewById(R.id.imageView);
                NUMBER2=(TextView)mView.findViewById(R.id.Number2);
                NAME2 = (TextView)mView.findViewById(R.id.Name2);
                STAR2 = (TextView)mView.findViewById(R.id.Star2);
                ATTRIBUTES2 = (TextView)mView.findViewById(R.id.Attributes2);
                HP2 = (TextView)mView.findViewById(R.id.Hp2);
                ATK2 = (TextView)mView.findViewById(R.id.Atk2);
                DEF2 = (TextView)mView.findViewById(R.id.Def2);
                SPD2 = (TextView)mView.findViewById(R.id.Spd2);
                SKL12 = (TextView)mView.findViewById(R.id.Skl12);
                SKL22 = (TextView)mView.findViewById(R.id.Skl22);
                SKL32 = (TextView)mView.findViewById(R.id.Skl32);
                hit1 = (TextView)mView.findViewById(R.id.hit11);
                hit2 = (TextView)mView.findViewById(R.id.hit22);
                hit3 = (TextView)mView.findViewById(R.id.hit33);
                getItemtext(Item);
                POKEMON.setImageResource(imageitem2);
                NUMBER2.setText(Itenarr[0]);
                NAME2.setText(Itenarr[2]);
                STAR2.setText(Itenarr[3]);
                ATTRIBUTES2.setText(Itenarr[5]);
                HP2.setText(Itenarr[7]);
                ATK2.setText(Itenarr[8]);
                DEF2.setText(Itenarr[9]);
                SPD2.setText(Itenarr[10]);
                SKL12.setText(Itenarr[11]);
                SKL22.setText(Itenarr[12]);
                SKL32.setText(Itenarr[13]);
                hit1.setText(hit[0]);
                hit2.setText(hit[1]);
                hit3.setText(hit[2]);
                //查詢背包狀態
                bagstate = BAGstate(Item);
                if(bagstate==0){
                    BAG.setText("☆");
                }
                else{
                    BAG.setText("★");
                }
                final String BAGtext = String.valueOf(BAG.getText());
                BAG.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (BAGtext){
                            case "☆":
                                BAG.setText("★");
                                inBAG(Item);
                                break;
                            case"★":
                                BAG.setText("☆");
                                delBAG(Item);
                                break;
                        };
                    }
                });
                DbManager manager = new DbManager(illustration.this);
                manager.openDataBase();
                SQLiteDatabase db = manager.getDb();
                Cursor a = db.rawQuery("SELECT * FROM Pokemon WHERE PokemonNumber= "+"'"+Item+"'",null);
                String[] colNames = a.getColumnNames();
                String[] text = new String[a.getCount()];
                a.moveToFirst();  // 第1筆
                for(int i = 0;i<14;i++){
                    Itenarr[i]=a.getString(a.getColumnIndex(colNames[i]));
                }

            }

        });

        btn2 = (Button) findViewById(R.id.button2);
        tv2 = (TextView) findViewById(R.id.TV2);
        pcheckBox1 = (CheckBox)findViewById(R.id.p1);
        pcheckBox2 = (CheckBox)findViewById(R.id.p2);
        pcheckBox3 = (CheckBox)findViewById(R.id.p3);
        pcheckBox4 = (CheckBox)findViewById(R.id.p4);
        pcheckBox5 = (CheckBox)findViewById(R.id.p5);
        pcheckBox6 = (CheckBox)findViewById(R.id.p6);
        pcheckBox7 = (CheckBox)findViewById(R.id.p7);
        pcheckBox8 = (CheckBox)findViewById(R.id.p8);
        pcheckBox9 = (CheckBox)findViewById(R.id.p9);
        pcheckBox10 = (CheckBox)findViewById(R.id.p10);
        pcheckBox11 = (CheckBox)findViewById(R.id.p11);
        pcheckBox12 = (CheckBox)findViewById(R.id.p12);
        pcheckBox13 = (CheckBox)findViewById(R.id.p13);
        pcheckBox14 = (CheckBox)findViewById(R.id.p14);
        pcheckBox15 = (CheckBox)findViewById(R.id.p15);
        pcheckBoxbag = (CheckBox)findViewById(R.id.bag123);
        pcheckBoxbag.setChecked(false);

        CompoundButton.OnCheckedChangeListener plistener = new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()){
                    case R.id.p1:
                        if(pcheckBox1.isChecked()) {
                            pcheck[0] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[0] = false;
                            getall();
                            break;
                        }
                    case R.id.p2:
                        if(pcheckBox2.isChecked()) {
                            pcheck[1] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[1] = false;
                            getall();
                            break;
                        }
                    case R.id.p3:
                        if(pcheckBox3.isChecked()) {
                            pcheck[2] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[2] = false;
                            getall();
                            break;
                        }
                    case R.id.p4:
                        if(pcheckBox4.isChecked()) {
                            pcheck[3] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[3] = false;
                            getall();
                            break;
                        }
                    case R.id.p5:
                        if(pcheckBox5.isChecked()) {
                            pcheck[4] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[4] = false;
                            getall();
                            break;
                        }
                    case R.id.p6:
                        if(pcheckBox6.isChecked()) {
                            pcheck[5] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[5] = false;
                            getall();
                            break;
                        }
                    case R.id.p7:
                        if(pcheckBox7.isChecked()) {
                            pcheck[6] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[6] = false;
                            getall();
                            break;
                        }
                    case R.id.p8:
                        if(pcheckBox8.isChecked()) {
                            pcheck[7] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[7] = false;
                            getall();
                            break;
                        }
                    case R.id.p9:
                        if(pcheckBox9.isChecked()) {
                            pcheck[8] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[8] = false;
                            getall();
                            break;
                        }
                    case R.id.p10:
                        if(pcheckBox10.isChecked()) {
                            pcheck[9] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[9] = false;
                            getall();
                            break;
                        }
                    case R.id.p11:
                        if(pcheckBox11.isChecked()) {
                            pcheck[10] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[10] = false;
                            getall();
                            break;
                        }
                    case R.id.p12:
                        if(pcheckBox12.isChecked()) {
                            pcheck[11] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[11] = false;
                            getall();
                            break;
                        }
                    case R.id.p13:
                        if(pcheckBox13.isChecked()) {
                            pcheck[12] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[12] = false;
                            getall();
                            break;
                        }
                    case R.id.p14:
                        if(pcheckBox14.isChecked()) {
                            pcheck[13] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[13] = false;
                            getall();
                            break;
                        }
                    case R.id.p15:
                        if(pcheckBox15.isChecked()) {
                            pcheck[14] = true;
                            getall();
                            break;
                        }
                        else {
                            pcheck[14] = false;
                            getall();
                            break;
                        }
                    case R.id.bag123:
                        if(pcheckBoxbag.isChecked()) {
                            bag=" AND bag = 1 ";
                            getall();
                            break;
                        }
                        else {
                            bag="";
                            getall();
                            break;
                        }


                }

            }

        };
        pcheckBox1.setOnCheckedChangeListener(plistener);
        pcheckBox2.setOnCheckedChangeListener(plistener);
        pcheckBox3.setOnCheckedChangeListener(plistener);
        pcheckBox4.setOnCheckedChangeListener(plistener);
        pcheckBox5.setOnCheckedChangeListener(plistener);
        pcheckBox6.setOnCheckedChangeListener(plistener);
        pcheckBox7.setOnCheckedChangeListener(plistener);
        pcheckBox8.setOnCheckedChangeListener(plistener);
        pcheckBox9.setOnCheckedChangeListener(plistener);
        pcheckBox10.setOnCheckedChangeListener(plistener);
        pcheckBox11.setOnCheckedChangeListener(plistener);
        pcheckBox12.setOnCheckedChangeListener(plistener);
        pcheckBox13.setOnCheckedChangeListener(plistener);
        pcheckBox14.setOnCheckedChangeListener(plistener);
        pcheckBox15.setOnCheckedChangeListener(plistener);
        pcheckBoxbag.setOnCheckedChangeListener(plistener);
        //篩選器
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog .Builder mBuilder = new AlertDialog.Builder(illustration.this);
                View mView = getLayoutInflater().inflate(R.layout.filter,null);
                //CheckBox checkBox = (CheckBox)view;
                checkBox1 = (CheckBox)mView.findViewById(R.id.Normal);
                checkBox2 = (CheckBox)mView.findViewById(R.id.Fighter);
                checkBox3 = (CheckBox)mView.findViewById(R.id.Poison);
                checkBox4 = (CheckBox)mView.findViewById(R.id.Ground);
                checkBox5 = (CheckBox)mView.findViewById(R.id.Fly) ;
                checkBox6 = (CheckBox)mView.findViewById(R.id.Insect);
                checkBox7 = (CheckBox)mView.findViewById(R.id.Rock);
                checkBox8 = (CheckBox)mView.findViewById(R.id.Ghost);
                checkBox9 = (CheckBox)mView.findViewById(R.id.Steel);
                checkBox10 = (CheckBox)mView.findViewById(R.id.Inflammation);
                checkBox11 = (CheckBox)mView.findViewById(R.id.Water);
                checkBox12 = (CheckBox)mView.findViewById(R.id.Electricity);
                checkBox13 = (CheckBox)mView.findViewById(R.id.Grass);
                checkBox14 = (CheckBox)mView.findViewById(R.id.Ice);
                checkBox15 = (CheckBox)mView.findViewById(R.id.Superpower);
                checkBox16 = (CheckBox)mView.findViewById(R.id.Dragon);
                checkBox17 = (CheckBox)mView.findViewById(R.id.Evil);
                checkBox18 = (CheckBox)mView.findViewById(R.id.Fairy);
                checkBox1.setChecked(check[0]);
                checkBox2.setChecked(check[1]);
                checkBox3.setChecked(check[2]);
                checkBox4.setChecked(check[3]);
                checkBox5.setChecked(check[4]);
                checkBox6.setChecked(check[5]);
                checkBox7.setChecked(check[6]);
                checkBox8.setChecked(check[7]);
                checkBox9.setChecked(check[8]);
                checkBox10.setChecked(check[9]);
                checkBox11.setChecked(check[10]);
                checkBox12.setChecked(check[11]);
                checkBox13.setChecked(check[12]);
                checkBox14.setChecked(check[13]);
                checkBox15.setChecked(check[14]);
                checkBox16.setChecked(check[15]);
                checkBox17.setChecked(check[16]);
                checkBox18.setChecked(check[17]);
                CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switch (buttonView.getId()){
                            case R.id.Normal:
                                if(checkBox1.isChecked()) {
                                    check[0] = true;
                                    break;
                                }
                                else {
                                    check[0] = false;
                                    break;
                                }
                            case R.id.Fighter:
                                if(checkBox2.isChecked()) {
                                    check[1] = true;
                                    break;
                                }
                                else {
                                    check[1] = false;
                                    break;
                                }
                            case R.id.Poison:
                                if(checkBox3.isChecked()) {
                                    check[2] = true;
                                    break;
                                }
                                else {
                                    check[2] = false;
                                    break;
                                }
                            case R.id.Ground:
                                if(checkBox4.isChecked()) {
                                    check[3] = true;
                                    break;
                                }
                                else {
                                    check[3] = false;
                                    break;
                                }
                            case R.id.Fly:
                                if(checkBox5.isChecked()) {
                                    check[4] = true;
                                    break;
                                }
                                else {
                                    check[4] = false;
                                    break;
                                }
                            case R.id.Insect:
                                if(checkBox6.isChecked()) {
                                    check[5] = true;
                                    break;
                                }
                                else {
                                    check[5] = false;
                                    break;
                                }
                            case R.id.Rock:
                                if(checkBox7.isChecked()) {
                                    check[6] = true;
                                    break;
                                }
                                else {
                                    check[6] = false;
                                    break;
                                }
                            case R.id.Ghost:
                                if(checkBox8.isChecked()) {
                                    check[7] = true;
                                    break;
                                }
                                else {
                                    check[7] = false;
                                    break;
                                }
                            case R.id.Steel:
                                if(checkBox9.isChecked()) {
                                    check[8] = true;
                                    break;
                                }
                                else {
                                    check[8] = false;
                                    break;
                                }
                            case R.id.Inflammation:
                                if(checkBox10.isChecked()) {
                                    check[9] = true;
                                    break;
                                }
                                else {
                                    check[9] = false;
                                    break;
                                }
                            case R.id.Water:
                                if(checkBox11.isChecked()) {
                                    check[10] = true;
                                    break;
                                }
                                else {
                                    check[10] = false;
                                    break;
                                }
                            case R.id.Electricity:
                                if(checkBox12.isChecked()) {
                                    check[11] = true;
                                    break;
                                }
                                else {
                                    check[11] = false;
                                    break;
                                }
                            case R.id.Grass:
                                if(checkBox13.isChecked()) {
                                    check[12] = true;
                                    break;
                                }
                                else {
                                    check[12] = false;
                                    break;
                                }
                            case R.id.Ice:
                                if(checkBox14.isChecked()) {
                                    check[13] = true;
                                    break;
                                }
                                else {
                                    check[13] = false;
                                    break;
                                }
                            case R.id.Superpower:
                                if(checkBox15.isChecked()) {
                                    check[14] = true;
                                    break;
                                }
                                else {
                                    check[14] = false;
                                    break;
                                }
                            case R.id.Dragon:
                                if(checkBox16.isChecked()) {
                                    check[15] = true;
                                    break;
                                }
                                else {
                                    check[15] = false;
                                    break;
                                }
                            case R.id.Evil:
                                if(checkBox17.isChecked()) {
                                    check[16] = true;
                                    break;
                                }
                                else {
                                    check[16] = false;
                                    break;
                                }
                            case R.id.Fairy:
                                if(checkBox18.isChecked()) {
                                    check[17] = true;
                                    break;
                                }
                                else {
                                    check[17] = false;
                                    break;
                                }
                        }
                    }
                };
                checkBox1.setOnCheckedChangeListener(listener);
                checkBox2.setOnCheckedChangeListener(listener);
                checkBox3.setOnCheckedChangeListener(listener);
                checkBox4.setOnCheckedChangeListener(listener);
                checkBox5.setOnCheckedChangeListener(listener);
                checkBox6.setOnCheckedChangeListener(listener);
                checkBox7.setOnCheckedChangeListener(listener);
                checkBox8.setOnCheckedChangeListener(listener);
                checkBox9.setOnCheckedChangeListener(listener);
                checkBox10.setOnCheckedChangeListener(listener);
                checkBox11.setOnCheckedChangeListener(listener);
                checkBox12.setOnCheckedChangeListener(listener);
                checkBox13.setOnCheckedChangeListener(listener);
                checkBox14.setOnCheckedChangeListener(listener);
                checkBox15.setOnCheckedChangeListener(listener);
                checkBox16.setOnCheckedChangeListener(listener);
                checkBox17.setOnCheckedChangeListener(listener);
                checkBox18.setOnCheckedChangeListener(listener);
                mBuilder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getall();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            EditText SearchEdtext = (EditText) findViewById(R.id.et_Name);
            SearchStr = SearchEdtext.getText().toString();
            getview();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditText SearchEdtext = (EditText) findViewById(R.id.et_Name);
            SearchStr = SearchEdtext.getText().toString();
            getview();
        }

        @Override
        public void afterTextChanged(Editable s) {
            EditText SearchEdtext = (EditText)findViewById(R.id.et_Name);
            SearchStr = SearchEdtext.getText().toString();
            getview();
        }
    };
    /*public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;//禁止GrSearchAttributesidview进行滑动
        }
        return super.dispatchTouchEvent(ev);
    }*/
    public void getABItem(){
        for(int i =0;i<18;i++){
            if(check[i]==true){
                SearchAttributesItem[i] = ATBarr[i];
            }
            else {
                SearchAttributesItem[i] = "";
            }
        }
    }
    public void getpItem(){
        for(int i =0;i<15;i++){
            if(pcheck[i]==true){
                SearchPartItem[i] = Partarr[i];
            }
            else {
                SearchPartItem[i] = "";
            }
        }
    }
    public void getItemtext(String Item){
        DbManager manager = new DbManager(illustration.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT * FROM Pokemon WHERE PokemonNumber= "+"'"+Item+"'",null);
        String[] colNames = c.getColumnNames();
        String[] text = new String[c.getCount()];
        c.moveToFirst();  // 第1筆
        for(int i = 0;i<14;i++){
            Itenarr[i]=c.getString(c.getColumnIndex(colNames[i]));
        }
        for(int i=11,j=0;i<14;i++,j++){
            Cursor a = db.rawQuery("SELECT * FROM Skill WHERE 中文名= "+"'"+Itenarr[i]+"'",null);
            colNames = a.getColumnNames();
            a.moveToFirst();  // 第1筆
            hit[j] = a.getString(a.getColumnIndex(colNames[7]));
            a.moveToNext();
        }
    }
    public void getview() {
        DbManager manager = new DbManager(illustration.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonName like " + "'%" + SearchStr + "%' "+SearchAttributes+") "+SearchPart+") "+bag+Pstring+SBBB , null);
        String[] colNames = c.getColumnNames();
        String[] text = new String[c.getCount()];
        String[] text1 = new String[c.getCount()];
        int[] imageid = new int[c.getCount()];
        String[] image = new String[c.getCount()];
        IT = c.getCount();
        String[] imageitem1=new String[c.getCount()];
        Itenimage=new int[IT];
        Itentext = new String[IT];
        c.moveToFirst();  // 第1筆
        for (int i = 0; i < c.getCount(); i++) {
            text[i] = c.getString(c.getColumnIndex(colNames[2]));
            text1[i] = c.getString(c.getColumnIndex(colNames[3]));
            Itentext[i]=c.getString(c.getColumnIndex(colNames[0]));
            imageitem1[i]=c.getString(c.getColumnIndex(colNames[0]));
            image[i]=c.getString(c.getColumnIndex(colNames[0]));
            c.moveToNext();
            Log.i("dfsadewf","hey");
        }
        c.close();
        db.close();
        for(int i = 0; i < c.getCount(); i++) {
            imageid[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            Itenimage[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            Log.d("image[i]", "a_"+image[i]+"_1");
            Log.i("dfsadewf","heyya");
        }
        manager.closeDataBase();
        CustomGrid adapter = new CustomGrid(illustration.this, text,text1,imageid);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setFocusable(false);
    }

    public void getattributes() {
        for (int i = 0; i < SearchAttributesItem.length; i++) {
            if (i == 0)
                SearchAttributes = " AND (Attributes like '" + SearchAttributesItem[i] + "' ";
            else
                SearchAttributes += " OR Attributes like '" + SearchAttributesItem[i] + "' ";
            //.............如果還要顯示其它欄位，就要再複製上面一行
        }
    }
    public void getpart() {
        for (int i = 0; i < SearchPartItem.length; i++) {

            if (i == 0)
                SearchPart = " AND (Part = '" + SearchPartItem[i]+ "' ";
            else
                SearchPart += " OR Part = '" + SearchPartItem[i]+ "' " ;
            //.............如果還要顯示其它欄位，就要再複製上面一行
        }

    }
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //查詢背包狀態
    public int BAGstate(String Number){
        int state;
        DbManager manager = new DbManager(illustration.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "SELECT * from Pokemon WHERE PokemonNumber = '"+Number+"'";
        Cursor c = db.rawQuery(TED,null);
        c.moveToFirst();
        String[] colNames = c.getColumnNames();
        state= Integer.parseInt(c.getString(c.getColumnIndex(colNames[14])));
        return state;
    }
    //加入背包
    public void inBAG(String Number){
        DbManager manager = new DbManager(illustration.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "UPDATE Pokemon SET bag = 1 WHERE PokemonNumber = '"+Number+"'";
        db.execSQL(TED);
        db.close();
    }
    public void delBAG(String Number){
        DbManager manager = new DbManager(illustration.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "UPDATE Pokemon SET bag = 0 WHERE PokemonNumber = '"+Number+"'";
        db.execSQL(TED);
        db.close();
    }
    public void getall(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getpItem();
                getpart();
                getABItem();
                getattributes();
                getview();
            }
        };
        runnable.run();

    }
    public void allcheck(){
        pcheckBox1.setChecked(true);
        pcheckBox2.setChecked(true);
        pcheckBox3.setChecked(true);
        pcheckBox4.setChecked(true);
        pcheckBox5.setChecked(true);
        pcheckBox6.setChecked(true);
        pcheckBox7.setChecked(true);
        pcheckBox8.setChecked(true);
        pcheckBox9.setChecked(true);
        pcheckBox10.setChecked(true);
        pcheckBox11.setChecked(true);
        pcheckBox12.setChecked(true);
        pcheckBox13.setChecked(true);
        pcheckBox14.setChecked(true);
        pcheckBox15.setChecked(true);
        getall();
    }
    public void unallcheck(){
        pcheckBox1.setChecked(false);
        pcheckBox2.setChecked(false);
        pcheckBox3.setChecked(false);
        pcheckBox4.setChecked(false);
        pcheckBox5.setChecked(false);
        pcheckBox6.setChecked(false);
        pcheckBox7.setChecked(false);
        pcheckBox8.setChecked(false);
        pcheckBox9.setChecked(false);
        pcheckBox10.setChecked(false);
        pcheckBox11.setChecked(false);
        pcheckBox12.setChecked(false);
        pcheckBox13.setChecked(false);
        pcheckBox14.setChecked(false);
        pcheckBox15.setChecked(false);
        pcheckBoxbag.setChecked(false);
        getall();
    }
}