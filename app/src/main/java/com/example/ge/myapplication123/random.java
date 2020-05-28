package com.example.ge.myapplication123;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.Random;

public class random extends AppCompatActivity {

    private String pokemonhp[];
    private String pokemonnumber[];
    private String[] enemynumber = new String[3];
    private String PokemonName[];
    private int battle1[] = new int[3];
    private Button bt;
    private Button bt2;
    private GridView grid2;
    private String SearchStr = "";
    private int IT= 0;
    private String[] Itentext;
    private boolean battlest[] = new boolean[3];
    private String[] battle1text = new String[3];
    private String[] battle1text1 = new String[3];
    private int[] battle1imgid = new int[3];
    private String enemyhp[] = new String [3];
    private int mybp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random);
        bt = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        Bundle bundle = getIntent().getExtras();
        mybp = bundle.getInt("mybp");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getview();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getview();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(random.this, battle2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("mybp", mybp);//傳遞Double
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                ingamebag();
            }
        });
    }


    public void getview() {
        Random ran = new Random();
        DbManager manager = new DbManager(random.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonName like " + "'%" + SearchStr + "%' "+"AND Part ="+mybp+" AND (Star = '★' OR Star = '★★' OR Star = '★★★') " , null);
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
        //角色
        CustomGrid adapter1 = new CustomGrid(random.this, battle1text,battle1text1,battle1imgid);
        grid2 = (GridView)findViewById(R.id.battlegrid2) ;
        grid2.setAdapter(adapter1);
    }
    public void ingamebag(){
        DbManager manager = new DbManager(random.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        for(int i=0;i<=2;i++) {
            String TED = "UPDATE Pokemon SET gamebag = 1 WHERE PokemonNumber = '" + enemynumber[i] + "'";
            db.execSQL(TED);
        }
        db.close();
    }

}
