package com.example.ge.myapplication123;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class attributes1 extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private GridView battlegrid2;
    private String[] ATTSORT=new String[3];
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18;
    private String[] PokemonNumber = new String[3];
    private float[][] AttR = new float[18][18];//18*18剋屬表
    private String[] PokemonName = new String[3];
    private String[] PokemonStar = new String[3];
    private int[] PokemonImg = new int[3];
    private int[] PokemonHp = new int[3];
    private int[] PokemonAtk = new int[3];
    private String[] PokemonSkill = new String[3];
    private String[] PokemonSkillAtt = new String[3];
    private String[] PokemonAtt = new String[3];
    private String[] Itenarr= new String[14];
    TextView NUMBER2,NAME2,STAR2,ATTRIBUTES2,HP2,ATK2,DEF2,SPD2,SKL12,SKL22,SKL32 ;
    ImageView POKEMONimage;
    Button BAG;
    TextView fskl1,fskl2,fskl3;//技能
    private TextView[] sa =new TextView[3];//技能屬性
    private TextView[] fa =new TextView[3];//本體屬性
    TextView hit1,hit2,hit3;



    private String[] hit = new String[3];
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
            intent.setClass(attributes1.this , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_attributes1) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "屬性表", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this , attributes1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_attributes) {
            // 按下「關於」要做的事
            Toast.makeText(this, "圖鑑", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this , illustration.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_battle) {
            // 按下「關於」要做的事
            Toast.makeText(this, "快速對戰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this , battle.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_main2) {
            // 按下「關於」要做的事
            Toast.makeText(this, "基礎教學", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this , Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_map) {
            // 按下「關於」要做的事
            Toast.makeText(this, "進階玩法", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this , map.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_guide3) {
            // 按下「關於」要做的事
            Toast.makeText(this, "遊戲攻略", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this , guide2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_guide4) {
            // 按下「關於」要做的事
            Toast.makeText(this, "活動訊息", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(attributes1.this ,guide3.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     *
     */
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributes1);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn10 = (Button)findViewById(R.id.btn10);
        btn11 = (Button)findViewById(R.id.btn11);
        btn12 = (Button)findViewById(R.id.btn12);
        btn13 = (Button)findViewById(R.id.btn13);
        btn14 = (Button)findViewById(R.id.btn14);
        btn15 = (Button)findViewById(R.id.btn15);
        btn16 = (Button)findViewById(R.id.btn16);
        btn17 = (Button)findViewById(R.id.btn17);
        btn18 = (Button)findViewById(R.id.btn18);
        battlegrid2 = (GridView) findViewById(R.id.battlegrid2);
        fskl1 = (TextView)findViewById(R.id.fsk1);
        fskl2 = (TextView)findViewById(R.id.fsk2);
        fskl3 = (TextView)findViewById(R.id.fsk3);
        sa[0] = (TextView)findViewById(R.id.sa1);
        sa[1] = (TextView)findViewById(R.id.sa2);
        sa[2] = (TextView)findViewById(R.id.sa3);
        fa[0] = (TextView)findViewById(R.id.fa1);
        fa[1]= (TextView)findViewById(R.id.fa2);
        fa[2] = (TextView)findViewById(R.id.fa3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        AR();
        getattsort("一般");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("一般");
                getview();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("格鬥");
                getview();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("毒");
                getview();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("飛行");
                getview();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("蟲");
                getview();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("岩石");
                getview();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("鋼");
                getview();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("火");
                getview();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("水");
                getview();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("草");
                getview();
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("冰");
                getview();
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("超能力");
                getview();
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("惡");
                getview();
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("妖精");
                getview();
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("地面");
                getview();
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("幽靈");
                getview();
            }
        });

        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("電");
                getview();
            }
        });

        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getattsort("龍");
                getview();
            }
        });

        battlegrid2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int bagstate=0;
                int imgres=0;
                AlertDialog .Builder mBuilder = new AlertDialog.Builder(attributes1.this);

                View mView = getLayoutInflater().inflate(R.layout.activity_illustration2,null);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                final String Item = ATTSORT[position]; //該layout的PokemonNumber EX:9_00
                BAG = (Button)mView.findViewById(R.id.Bag);
                POKEMONimage= (ImageView)mView.findViewById(R.id.imageView);
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
                POKEMONimage.setImageResource(illustration.getResId("a_"+ATTSORT[position]+"_1", R.drawable.class));
                getItemtext(ATTSORT[position]);
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
                bagstate = BAGstate(ATTSORT[position]) ;
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
                DbManager manager = new DbManager(attributes1.this);
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

    }
    public void AR(){   //回傳剋屬 a屬攻b屬
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  一般,格鬥,毒,地面,飛行,蟲,岩石,幽靈,鋼,火,水,電,草,冰,超能力,龍,惡,妖精 FROM AttributesRelationship ORDER BY Number ASC", null);
        String[] colNames = c.getColumnNames();
        c.moveToFirst();  // 第1筆
        for(int i=0;i<=17;i++){
            for(int j=0;j<=17;j++){
                AttR[i][j] = c.getFloat(c.getColumnIndex(colNames[j]));
            }
            c.moveToNext();
        }
        c.close();
        db.close();
    }

    //丟入屬性字串回傳屬性Number
    public int getattnum(String ATT){
        int attnum = 0;
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        //獲取屬性Number
        String AttNumberSQL = "SELECT AttributesNumber from Attributes WHERE AttributesName = '"+ATT+"'";
        Cursor a = db.rawQuery(AttNumberSQL,null);
        String[] colNames = a.getColumnNames();
        a.moveToFirst();
        attnum = a.getInt(a.getColumnIndex(colNames[0]));
        a.close();
        db.close();
        return attnum;
    }
    //丟入技能回傳技能屬性Number
    public int getskillnumber(String ATT){
        int attnum =0;
        String skillname;
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        //獲取屬性Number
        String AttNumberSQL = "SELECT 屬性 from Skill WHERE 中文名 = '"+ATT+"'";
        Cursor b = db.rawQuery(AttNumberSQL,null);
        String[] colNames = b.getColumnNames();
        b.moveToFirst();
        if(b.getCount()>0){
            skillname = b.getString(b.getColumnIndex(colNames[0]));
            attnum = getattnum(skillname);
            Log.d("attnum", String.valueOf(attnum));
            Log.d("bbbbbbbbbb", String.valueOf(b.getCount()));
            b.close();
            db.close();
            return attnum;
        }
        return 0;

    }
    //傳入技能名稱，回傳技能威力
    public int getskillatk(String SkillName){
        int skillatk=0;
        String a;
        Log.d("SkillName",SkillName);
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        //獲取屬性Number
        String getSklatkSQL = "SELECT 威力 from Skill WHERE 中文名 = '"+SkillName+"'";
        Cursor d = db.rawQuery(getSklatkSQL,null);
        String[] colNames = d.getColumnNames();
        d.moveToFirst();
        if(d.getCount()>0){
            skillatk = Integer.parseInt(d.getString(d.getColumnIndex(colNames[0])));
            Log.d("skillatk", String.valueOf(skillatk));
            d.close();
            db.close();
            return skillatk;
        }
        else{
            Log.d("skillatk","1");
            return 0;
        }

    }
    public double getAMag(int a, int b){
        if(a==b)
            return 1.5;
        else
            return 1;
    }
    //傳入點擊按鈕的屬性(ATT)，回傳排序好的排列(Number)
    public void getattsort(String ATT){
        int a,b;                            //a=攻擊方,b=被攻擊方
        int bAtr = getattnum(ATT);
        Log.d("spdjgposjp2", String.valueOf(bAtr));
        int aAtk;    //攻擊方 攻擊力,技能攻擊力,本體屬性
        double aSkillAtk=0,total;
        int aAtr;
        float ATTMag = 0,aMag = 1;
        String allnumber[];
        Double[] allatk;
        Double[] ATKSORT = new Double[3];
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        a = getattnum(ATT);
        //搜尋所有背包卡片
        String BagCardSQL = "SELECT * from Pokemon WHERE bag = 1 ";
        Cursor c = db.rawQuery(BagCardSQL,null);
        String[] colNames = c.getColumnNames();
        allatk = new Double[c.getCount()];
        allnumber = new String[c.getCount()];
        c.moveToFirst();
        if(c.getCount()<3){
            AlertDialog.Builder builder = new AlertDialog.Builder(attributes1.this);
            builder.setCancelable(false);
            builder.setTitle("背包卡片不夠");
            builder.setMessage("背包裡的卡片不足3隻，要去圖鑑裡加入嗎？");

            builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(attributes1.this,illustration.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("不，回到主頁", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(attributes1.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else {
            for (int i = 0; i < c.getCount(); i++) {
                aAtk = c.getInt(c.getColumnIndex(colNames[8]));
                aSkillAtk = (getskillatk(c.getString(c.getColumnIndex(colNames[11]))))                //技能攻擊力
                        * (AttR[getskillnumber(c.getString(c.getColumnIndex(colNames[11])))][bAtr])    //屬性倍率
                        * (getAMag(getskillnumber(c.getString(c.getColumnIndex(colNames[11]))), bAtr)); //本體倍率
                total = aAtk + aSkillAtk;
                allatk[i] = total;                                          //將所有傷害放入陣列
                allnumber[i] = c.getString(c.getColumnIndex(colNames[0]));  //將所有number放入陣列
                Log.d("spdjgposjpo", allnumber[i]);
                Log.d("spdjgposjpo", String.valueOf(AttR[bAtr][9]));
                Log.d("spdjgposjpo", String.valueOf(total));
                Log.d("spdjgposjpo1", "1");
                c.moveToNext();
            }
            for(int i=0;i<allatk.length-1;i++){
                for(int j=0;j<allatk.length-1;j++){
                    if(allatk[j+1]<allatk[j]){
                        Double tump=allatk[j];
                        allatk[j]=allatk[j+1];
                        allatk[j+1]=tump;
                        String temp = allnumber[j];
                        allnumber[j]=allnumber[j+1];
                        allnumber[j+1]=temp;
                    }
                }
            }
            for(int i=0;i<3;i++){
                Log.d("spdj", allnumber[i]);
            }
            for(int i=0;i<3;i++) {
                ATTSORT[i] = allnumber[i];
            }
            c.close();
            db.close();
        }


    }
    public String getSkillAtt(String Att){
        String SkillAtt = "";
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String getAtt = "SELECT * from Skill WHERE 中文名 = '" + Att + "'";
        Cursor c = db.rawQuery(getAtt, null);
        String[] colNames = c.getColumnNames();
        c.moveToFirst();
        if(c.getCount()>0){
            SkillAtt = c.getString(c.getColumnIndex(colNames[4]));
        }
        return SkillAtt;
    }
    //依照PokemonNumber搜尋所有Pokemon值並印上
    public void getview(){
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        //搜尋所有背包卡片

        for(int i=0;i<3;i++) {
            String BagCardSQL = "SELECT * from Pokemon WHERE PokemonNumber = '" + ATTSORT[i] + "'";
            Cursor c = db.rawQuery(BagCardSQL, null);
            String[] colNames = c.getColumnNames();
            c.moveToFirst();
            if (c.getCount() > 0) {
                Log.d("cccc","1");
                PokemonName[i] = c.getString(c.getColumnIndex(colNames[2]));  //名稱
                PokemonStar[i] = c.getString(c.getColumnIndex(colNames[3]));    //星數
                PokemonImg[i] = illustration.getResId("a_" + ATTSORT[i] + "_1", R.drawable.class);//圖片
                PokemonSkill[i] = c.getString(c.getColumnIndex(colNames[11]));//技能
                PokemonSkillAtt[i] = getSkillAtt(c.getString(c.getColumnIndex(colNames[11])));//技能屬性
                PokemonHp[i] = c.getInt(c.getColumnIndex(colNames[7]));//血量
                PokemonAtk[i] = c.getInt(c.getColumnIndex(colNames[8]));//攻擊力
                PokemonAtt[i] = c.getString(c.getColumnIndex(colNames[5]));   //卡片屬性
            }
        }
        CustomGrid adapter = new CustomGrid(attributes1.this, PokemonName,PokemonStar,PokemonImg);

        battlegrid2.setAdapter(adapter);
        battlegrid2.setFocusable(false);
        fskl1.setText(PokemonSkill[0]);
        fskl2.setText(PokemonSkill[1]);
        fskl3.setText(PokemonSkill[2]);
        sa[0].setText(PokemonSkillAtt[0]);
        sa[1].setText(PokemonSkillAtt[1]);
        sa[2].setText(PokemonSkillAtt[2]);
        fa[0].setText(PokemonAtt[0]);
        fa[1].setText(PokemonAtt[1]);
        fa[2].setText(PokemonAtt[2]);
        getcolor(PokemonSkillAtt);
        getcolor2(PokemonAtt);



    }



    //加入背包
    public void inBAG(String Number){
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "UPDATE Pokemon SET bag = 1 WHERE PokemonNumber = '"+Number+"'";
        db.execSQL(TED);
        db.close();
    }
    public void delBAG(String Number){
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "UPDATE Pokemon SET bag = 0 WHERE PokemonNumber = '"+Number+"'";
        db.execSQL(TED);
        db.close();
    }
    public int BAGstate(String Number){
        int state;
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        String TED = "SELECT * from Pokemon WHERE PokemonNumber = '"+Number+"'";
        Cursor c = db.rawQuery(TED,null);
        c.moveToFirst();
        String[] colNames = c.getColumnNames();
        state= Integer.parseInt(c.getString(c.getColumnIndex(colNames[14])));
        return state;
    }
    public void getItemtext(String Item){
        DbManager manager = new DbManager(attributes1.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT * FROM Pokemon WHERE PokemonNumber= "+"'"+Item+"'",null);
        String[] colNames = c.getColumnNames();
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

    public void getcolor(String[] attcolor) {
        for(int k=0;k<3;k++) {
            switch (attcolor[k]) {
                case "火":
                    sa[k].setBackgroundColor(Color.parseColor("#FF5511"));
                    break;
                case "格鬥":
                    sa[k].setBackgroundColor(Color.parseColor("#CC0000"));
                    break;
                case "毒":
                    sa[k].setBackgroundColor(Color.parseColor("#CC00FF"));
                    break;
                case "飛行":
                    sa[k].setBackgroundColor(Color.parseColor("#CCDDFF"));
                    break;
                case "蟲":
                    sa[k].setBackgroundColor(Color.parseColor("#BBFF00"));
                    break;
                case "岩石":
                    sa[k].setBackgroundColor(Color.parseColor("#880000"));
                    break;
                case "鋼":
                    sa[k].setBackgroundColor(Color.parseColor("#99FFFF"));
                    break;
                case "水":
                    sa[k].setBackgroundColor(Color.parseColor("#00BBFF"));
                    break;
                case "草":
                    sa[k].setBackgroundColor(Color.parseColor("#008800"));
                    break;
                case "冰":
                    sa[k].setBackgroundColor(Color.parseColor("#33FFDD"));
                    break;
                case "超能力":
                    sa[k].setBackgroundColor(Color.parseColor("#FFB3FF"));
                    break;
                case "惡":
                    sa[k].setBackgroundColor(Color.parseColor("#444444"));
                    break;
                case "妖精":
                    sa[k].setBackgroundColor(Color.parseColor("#CC00CC"));
                    break;
                case "地面":
                    sa[k].setBackgroundColor(Color.parseColor("#AA7700"));
                    break;
                case "幽靈":
                    sa[k].setBackgroundColor(Color.parseColor("#000088"));
                    break;
                case "電":
                    sa[k].setBackgroundColor(Color.parseColor("#FFFF33"));
                    break;
                case "龍":
                    sa[k].setBackgroundColor(Color.parseColor("#888800"));
                    break;
                case "一般":
                    sa[k].setBackgroundColor(Color.parseColor("#DDDDDD"));
                    break;
            }
        }
    }
    public void getcolor2(String[] attcolor) {
        for(int k=0;k<3;k++) {
            switch (attcolor[k]) {
                case "火":
                    fa[k].setBackgroundColor(Color.parseColor("#FF5511"));
                    break;
                case "格鬥":
                    fa[k].setBackgroundColor(Color.parseColor("#CC0000"));
                    break;
                case "毒":
                    fa[k].setBackgroundColor(Color.parseColor("#CC00FF"));
                    break;
                case "飛行":
                    fa[k].setBackgroundColor(Color.parseColor("#CCDDFF"));
                    break;
                case "蟲":
                    fa[k].setBackgroundColor(Color.parseColor("#BBFF00"));
                    break;
                case "岩石":
                    fa[k].setBackgroundColor(Color.parseColor("#880000"));
                    break;
                case "鋼":
                    fa[k].setBackgroundColor(Color.parseColor("#99FFFF"));
                    break;
                case "水":
                    fa[k].setBackgroundColor(Color.parseColor("#00BBFF"));
                    break;
                case "草":
                    fa[k].setBackgroundColor(Color.parseColor("#008800"));
                    break;
                case "冰":
                    fa[k].setBackgroundColor(Color.parseColor("#33FFDD"));
                    break;
                case "超能力":
                    fa[k].setBackgroundColor(Color.parseColor("#FFB3FF"));
                    break;
                case "惡":
                    fa[k].setBackgroundColor(Color.parseColor("#444444"));
                    break;
                case "妖精":
                    fa[k].setBackgroundColor(Color.parseColor("#CC00CC"));
                    break;
                case "地面":
                    fa[k].setBackgroundColor(Color.parseColor("#AA7700"));
                    break;
                case "幽靈":
                    fa[k].setBackgroundColor(Color.parseColor("#000088"));
                    break;
                case "電":
                    fa[k].setBackgroundColor(Color.parseColor("#FFFF33"));
                    break;
                case "龍":
                    fa[k].setBackgroundColor(Color.parseColor("#888800"));
                    break;
                case "一般":
                    fa[k].setBackgroundColor(Color.parseColor("#DDDDDD"));
                    break;
            }
        }
    }

}