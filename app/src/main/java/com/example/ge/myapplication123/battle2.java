package com.example.ge.myapplication123;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Random;

import static java.sql.Types.NULL;
import java.lang.reflect.Field;


public class battle2 extends AppCompatActivity {
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
    private String[] BagPokemonName = new String[3];
    private String[] pokemonnum = new String[3];
    private Button finshbutton;
    private String pokemonhp[];
    private String pokemonnumber[];
    private String[] enemynumber = new String[3];
    private String PokemonName[];
    private int mybp;
    private int battle1[] = new int[3];
    private Button select;
    private Button cleanbutton;
    private GridView grid;
    private GridView grid1;
    private GridView grid2;
    private String SearchStr = "";
    //private String SearchAttributes;
    private int IT= 0;
    private String[] Itentext;
    private boolean battlest[] = new boolean[3];
    private String[] battle1text = new String[3];
    private String[] battle1text1 = new String[3];
    private int[] battle1imgid = new int[3];
    private String enemyhp[] = new String [3];

    private String battle2text[] = new String[3];
    private String battle2text1[] = new String[3];
    private String battle2hp[] = new String[3];
    private int battle2imgid[] = new int[3];
    //private int[] Itenimage;
    //private String[] SearchPartItem = new String[13];
    //private String  SearchPart="";
    //private String Pstring=" ORDER BY PokemonNumber ";
    //private String SBBB = " ASC";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        mybp = bundle.getInt("mybp");
        select = (Button)findViewById(R.id.selectbutton);
        cleanbutton = (Button)findViewById(R.id.cleanbutton);
        finshbutton = (Button)findViewById(R.id.finishbutton);
        grid = (GridView) findViewById(R.id.textgrid);
        grid2 = (GridView) findViewById(R.id.battlegrid2);
        Log.d("mybp1", String.valueOf(mybp));
        getall();

        Log.d("mybp2", String.valueOf(mybp));
        final Context context = this;
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfriend(battle1);
                finshbutton.setVisibility(View.VISIBLE);
                grid2.setVisibility(View.VISIBLE);
            }
        });
        cleanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<=2;i++){
                    battle1[i] = NULL;
                }
                finshbutton.setVisibility(View.GONE);
                clean();
            }
        });
        finshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(battle2.this , BattleAnimation.class);
                //new一個Bundle物件，並將要傳遞的資料傳入
                Bundle bundle1 = new Bundle();
                Bundle bundle2 = new Bundle();
                Bundle bundle3 = new Bundle();
                Bundle bundle4 = new Bundle();
                Bundle bundle5 = new Bundle();
                Bundle bundle6 = new Bundle();
                Bundle bundle7 = new Bundle();
                Bundle bundle8 = new Bundle();
                bundle1.putStringArray("enemy1",battle1text );//傳遞敵方名稱
                bundle2.putStringArray("enemy2",enemyhp );//傳遞敵方血量
                bundle3.putIntArray("enemy3",battle1imgid );//傳遞敵方圖片ID

                bundle4.putStringArray("enemy4",battle2text );//傳遞敵方名稱
                bundle5.putStringArray("enemy5",battle2hp );//傳遞敵方血量
                bundle6.putIntArray("enemy6",battle2imgid );//傳遞敵方圖片ID
                bundle8.putStringArray("enemy8",enemynumber);//傳遞敵方編號
                bundle7.putStringArray("enemy7",pokemonnum);//
                intent.putExtras(bundle1);
                intent.putExtras(bundle2);
                intent.putExtras(bundle3);
                intent.putExtras(bundle4);
                intent.putExtras(bundle5);
                intent.putExtras(bundle6);
                intent.putExtras(bundle7);
                intent.putExtras(bundle8);
                startActivity(intent);
                finish();
            }
        });
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context, String.valueOf(BagPokemonName[position]),Toast.LENGTH_SHORT).show();//點擊元件顯示第幾個(Position)
                for(int i=0;i<=2;i++){
                    if(battle1[i]==NULL){
                        makeTextAndShow(battle2.this,"你選擇了"+BagPokemonName[position],Toast.LENGTH_SHORT);
                        battle1[i] = position;
                        Log.d("battle1", String.valueOf(battle1[i]));
                        break;
                    }
                }
            }
        });

    }

    public void getview() {
        Random ran = new Random();
        DbManager manager = new DbManager(battle2.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonName like " + "'%" + SearchStr + "%' "+"AND Part ="+mybp , null);
        String[] colNames = c.getColumnNames();
        String[] text = new String[c.getCount()];
        String[] text1 = new String[c.getCount()];
        int[] imageid = new int[c.getCount()];
        String[] image = new String[c.getCount()];
        IT = c.getCount();
        String[] imageitem1=new String[c.getCount()];
        //Itenimage=new int[IT];
        Itentext = new String[IT];
        PokemonName = new String[IT];
        pokemonhp = new String[IT];
        pokemonnumber = new String[IT];
        c.moveToFirst();  // 第1筆

        for (int i = 0; i < c.getCount(); i++) {
            pokemonnumber[i] = c.getString(c.getColumnIndex(colNames[0]));
            pokemonhp[i] = c.getString(c.getColumnIndex(colNames[7]));
            text[i] = c.getString(c.getColumnIndex(colNames[2]));
            text1[i] = c.getString(c.getColumnIndex(colNames[3]));
            Itentext[i]=c.getString(c.getColumnIndex(colNames[0]));
            PokemonName[i] = c.getString(c.getColumnIndex(colNames[2]));
            imageitem1[i]=c.getString(c.getColumnIndex(colNames[0]));
            image[i]=c.getString(c.getColumnIndex(colNames[0]));
            c.moveToNext();
        }
        for(int i = 0; i < c.getCount(); i++) {
            imageid[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            //Itenimage[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            Log.d("imagei",image[i]);
            Log.d("image", String.valueOf(imageid[i]));
        }
        for(int i=0;i<=2;i++){
            int rani = ran.nextInt(c.getCount());
            battle1text[i] = text[rani];
            battle1text1[i] = text1[rani];
            battle1imgid[i] = imageid[rani];
            enemyhp[i] = pokemonhp[rani];
            enemynumber[i] = pokemonnumber[rani];
        }
        manager.closeDataBase();
        //敵方角色
        CustomGrid adapter1 = new CustomGrid(battle2.this, battle1text,battle1text1,battle1imgid);
        grid1 = (GridView)findViewById(R.id.battlegrid1) ;
        grid1.setAdapter(adapter1);
    }
    public void bagfriend(){
        DbManager manager = new DbManager(battle2.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonName like " + "'%" + SearchStr + "%' "+"AND Part ="+mybp+" AND gamebag = 1" , null);
        String[] colNames = c.getColumnNames();
        String[] text = new String[c.getCount()];
        String[] text1 = new String[c.getCount()];
        int[] imageid = new int[c.getCount()];
        String[] image = new String[c.getCount()];
        IT = c.getCount();
        BagPokemonName = new String [IT];
        String[] imageitem1=new String[c.getCount()];
        //Itenimage=new int[IT];
        c.moveToFirst();  // 第1筆
        for (int i = 0; i < c.getCount(); i++) {
            BagPokemonName[i] = c.getString(c.getColumnIndex(colNames[2]));
            text[i] = c.getString(c.getColumnIndex(colNames[2]));
            text1[i] = c.getString(c.getColumnIndex(colNames[3]));
            Itentext[i]=c.getString(c.getColumnIndex(colNames[0]));
            imageitem1[i]=c.getString(c.getColumnIndex(colNames[0]));
            image[i]=c.getString(c.getColumnIndex(colNames[0]));
            c.moveToNext();
        }
        for(int i = 0; i < c.getCount(); i++) {
            imageid[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            //Itenimage[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            Log.d("imagei",image[i]);
            Log.d("image", String.valueOf(imageid[i]));
        }
        CustomGrid adapter1 = new CustomGrid(battle2.this, text,text1,imageid);
        grid.setAdapter(adapter1);
        grid.setFocusable(false);

    }
    //選擇我方角色
    public void myfriend(int battle2[]){
        DbManager manager = new DbManager(battle2.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonName like " + "'%" + SearchStr + "%' "+"AND Part ="+mybp+" AND gamebag = 1"  , null);
        String[] colNames = c.getColumnNames();
        String[] text = new String[c.getCount()];
        String[] text1 = new String[c.getCount()];
        int[] imageid = new int[c.getCount()];
        String[] image = new String[c.getCount()];
        IT = c.getCount();
        String[] imageitem1=new String[c.getCount()];
        //Itenimage=new int[IT];
        c.moveToFirst();  // 第1筆
        for (int i = 0; i < c.getCount(); i++) {
            text[i] = c.getString(c.getColumnIndex(colNames[2]));
            text1[i] = c.getString(c.getColumnIndex(colNames[3]));
            Itentext[i]=c.getString(c.getColumnIndex(colNames[0]));
            imageitem1[i]=c.getString(c.getColumnIndex(colNames[0]));
            image[i]=c.getString(c.getColumnIndex(colNames[0]));
            c.moveToNext();
        }
        for(int i = 0; i < c.getCount(); i++) {
            imageid[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            //Itenimage[i] = illustration.getResId("a_"+image[i]+"_1", R.drawable.class);
            Log.d("imagei",image[i]);
            Log.d("image", String.valueOf(imageid[i]));
        }
        for(int i=0;i<=2;i++){
            battle2text[i] = text[battle2[i]];
            battle2text1[i] = text1[battle2[i]];
            battle2hp[i] = pokemonhp[battle2[i]];
            battle2imgid[i] = imageid[battle2[i]];
            pokemonnum[i] = image[battle2[i]];
        }
        CustomGrid adapter = new CustomGrid(battle2.this, battle2text,battle2text1,battle2imgid);
        grid2 = (GridView) findViewById(R.id.battlegrid2);
        grid2.setAdapter(adapter);
        grid2.setFocusable(false);
        //該彈所有角色


    }
    public void clean(){
        String battle2text[] = new String[3];
        String battle2text1[] = new String[3];
        String battle2hp[] = new String[3];
        int battle2imgid[] = new int[3];

        //CustomGrid adapter = new CustomGrid(battle2.this, battle2text,battle2text1,battle2imgid);
        grid2 = (GridView) findViewById(R.id.battlegrid2);
        //grid2.setAdapter(adapter);
        grid2.setVisibility(View.INVISIBLE);
        grid2.setFocusable(false);
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
    /*public void getpart() {
        for (int i = 0; i < SearchPartItem.length; i++) {
            Log.d("bbbb",SearchPartItem[i]);
            if (i == 0)
                SearchPart = " AND (Part = '" + SearchPartItem[i]+ "' ";
            else
                SearchPart += " OR Part = '" + SearchPartItem[i]+ "' " ;
            //.............如果還要顯示其它欄位，就要再複製上面一行
        }
        Log.d("aaaa",SearchPart);

    }*/
    public void getall(){
        //getpart();

        getview();
        bagfriend();
    }

}
