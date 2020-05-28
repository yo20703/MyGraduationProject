package com.example.ge.myapplication123;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleEnd extends AppCompatActivity {
    private int[] enemyresid = new int[3];
    private int[] BattleEnemyHp = new int[3];
    private String[] enemyname = new String[3];
    private String[] enemynumber = new String[3];
    private Button buttonnext ;
    private Button buttonleave;
    private Button buttonscreenshot;
    private ImageView catch1;
    private ImageView catch2;
    private ImageView catch3;
    private TextView  catchname1;
    private TextView  catchname3;
    private TextView  catch11;
    private TextView  catch22;
    private TextView  catch33;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_end);
        Bundle bundle = getIntent().getExtras();
        enemyresid = bundle.getIntArray("im");
        enemyname = bundle.getStringArray("name");
        BattleEnemyHp = bundle.getIntArray("catch");
        enemynumber = bundle.getStringArray("enemynum");
        buttonnext = (Button) findViewById(R.id.buttonnext);
        buttonleave = (Button) findViewById(R.id.buttonleave);

        catch1 = (ImageView) findViewById(R.id.catch1);
        catch2 = (ImageView) findViewById(R.id.catch2);
        catch3 = (ImageView) findViewById(R.id.catch3);
        catchname1 = (TextView) findViewById(R.id.catch1name);
        catchname3 = (TextView) findViewById(R.id.catch3name);
        catch11 = (TextView) findViewById(R.id.catch1text);
        catch22 = (TextView) findViewById(R.id.catch2text);
        catch33 = (TextView) findViewById(R.id.catch3text);
        catch1.setImageResource(enemyresid[0]);
        catch2.setImageResource(enemyresid[1]);
        catch3.setImageResource(enemyresid[2]);
        catchname1.setText(enemyname[0]);
        catchname3.setText(enemyname[2]);
        if (BattleEnemyHp[0] == 0) {
            catch11.setText("捕獲成功");
            getbag(enemynumber[0]);
        }
        if (BattleEnemyHp[1] == 0) {
            catch22.setText("捕獲成功");
            getbag(enemynumber[1]);
        }
        if (BattleEnemyHp[2] == 0){
            catch33.setText("捕獲成功");
            getbag(enemynumber[2]);
        }
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleEnd.this ,battle.class);
                startActivity(intent);
                finish();
            }
        });

        buttonleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleEnd.this ,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        return;
    }
    public void getbag(String number){
        String TED = "UPDATE Pokemon SET gamebag = 1 WHERE PokemonNumber = '"+number+"'";
        DbManager manager = new DbManager(BattleEnd.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        db.execSQL(TED);
        db.close();
    }
}
