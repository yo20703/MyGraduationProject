package com.example.ge.myapplication123;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Random;

public class BattleAnimation extends AppCompatActivity {
    private String[] enemyskl = new String[3];
    private float[] enemysklatk = new float[3];
    private String[] enemysklatt = new String[3];
    private float[] FriendBattleRe = new float[3];//我方攻擊敵方時屬性倍率
    private float[] EnemyBattleRe = new float[3];//敵方攻擊我方時屬性倍率
    private String [][] EnemyatkSkl = new String[3][2];//敵方技能[名稱][屬性]
    private String [][] Enemyskl = new String[3][3];//敵方技能
    private int [] EnemySkillAtk = new int[3];
    private int pkcount;//超過3跳到結束畫面
    private Button atkbutton;
    private Button catchbutton;
    private String[][] atkskl = new String[3][2];//我方技能名稱 屬性
    private String[] Enemyatt=new String[3];
    private String[] Friendatt=new String[3];
    private float[] SkillAtk = new float[3];
    private String[] friendid = new String[3];
    private String[] friend1skl = new String[3];
    private String[] friend2skl = new String[3];
    private String[] friend3skl = new String[3];
    private SoundPool soundPool;
    private int soundId;
    private int[] BattleEnemyHp= new int[3];
    private double[] BEH= new double[3];
    private double[] BEH1= new double[3];
    private double[] BFH= new double[3];
    private double[] BFH1= new double[3];
    private int BattleFriendHp1 = 0;
    private int BattleFriendHp2 = 0;
    private int BattleFriendHp3 = 0;
    private TextView EnemyName1;
    private TextView EnemyName2;
    private TextView EnemyName3;
    private TextView EnemyHp1;
    private TextView EnemyHp2;
    private TextView EnemyHp3;
    private ImageView enemy1;
    private ImageView enemy2;
    private ImageView enemy3;
    private TextView FriendName1;
    private TextView FriendName2;
    private TextView FriendName3;
    private TextView FriendHp1;
    private TextView FriendHp2;
    private TextView FriendHp3;
    private TextView effect1;
    private TextView effect2;
    private TextView effect3;
    private TextView effect4;
    private TextView effect5;
    private TextView effect6;
    private String[] enemyname = new String[3];
    private String[] enemyhp = new String[3];
    private int[] enemyresid = new int[3];
    private String[] friendyname = new String[3];
    private String[] friendhp = new String[3];
    private int[] friendresid = new int[3];
    private ImageView pkfriend1;
    private ImageView pkfriend2;
    private ImageView pkfriend3;
    private float[] FriendAtk = new float[3];
    private int[] FriendDef = new int[3];
    private Spinner BattleSkl1;
    private Spinner BattleSkl2;
    private Spinner BattleSkl3;
    private String[] enemynumber = new String[3];
    private int[] EnemyAtk = new int[3];
    private int[] EnemyDef = new int[3];
    long animationDuration = 1000;
    private float[][] AttR=new float[18][18];
    private int[] move ={413,413,413};
    private int[] move2 ={825,825,825};

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_animation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        atkbutton = (Button)findViewById(R.id.atkbutton);
        catchbutton = (Button)findViewById(R.id.catchbutton);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.hit, 1);
        Bundle bundle = getIntent().getExtras();
        enemyname = bundle.getStringArray("enemy1");
        enemyhp = bundle.getStringArray("enemy2");
        enemyresid = bundle.getIntArray("enemy3");
        friendyname = bundle.getStringArray("enemy4");
        friendhp = bundle.getStringArray("enemy5");
        friendresid = bundle.getIntArray("enemy6");
        friendid = bundle.getStringArray("enemy7");
        enemynumber = bundle.getStringArray("enemy8");
        getskill();
        getEnemyValue();
        Log.d("enemy",enemyhp[0]);

        catchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BattleAnimation.this , BattleEnd.class);
                Bundle bundle1 = new Bundle();
                Bundle bundle2 = new Bundle();
                Bundle bundle3 = new Bundle();
                Bundle bundle4 = new Bundle();
                bundle1.putIntArray("im",enemyresid );
                bundle2.putStringArray("name",enemyname );
                bundle3.putIntArray("catch",BattleEnemyHp );
                bundle4.putStringArray("enemynum",enemynumber);
                intent.putExtras(bundle1);
                intent.putExtras(bundle2);
                intent.putExtras(bundle3);
                intent.putExtras(bundle4);
                startActivity(intent);
                finish();
            }
        });

        //我方圖片
        pkfriend1 = (ImageView)findViewById(R.id.pkfriend1);
        pkfriend1.setImageResource(friendresid[0]);
        pkfriend2 = (ImageView)findViewById(R.id.pkfriend2);
        pkfriend2.setImageResource(friendresid[1]);
        pkfriend3 = (ImageView)findViewById(R.id.pkfriend3);
        pkfriend3.setImageResource(friendresid[2]);
        //我方血量
        FriendHp1 = (TextView)findViewById(R.id.FriendHp1);
        FriendHp1.setText("100%");
        BattleFriendHp1 = Integer.parseInt(friendhp[0]);
        FriendHp2 = (TextView)findViewById(R.id.FriendHp2);
        FriendHp2.setText("100%");
        BattleFriendHp2 = Integer.parseInt(friendhp[1]);
        FriendHp3 = (TextView)findViewById(R.id.FriendHp3);
        FriendHp3.setText("100%");
        BattleFriendHp3 = Integer.parseInt(friendhp[2]);
        //我方名稱
        FriendName1 = (TextView)findViewById(R.id.FriendName1);
        FriendName1.setText(friendyname[0]);
        FriendName2 = (TextView)findViewById(R.id.FriendName2);
        FriendName2.setText(friendyname[1]);
        FriendName3 = (TextView)findViewById(R.id.FriendName3);
        FriendName3.setText(friendyname[2]);
        //我方技能
        atkskl[0][0] = friend1skl[0]; //技能名稱
        atkskl[0][1] = getSkillAttributes(atkskl[0][0]);// 技能屬性
        SkillAtk[0] = getSkillAtk(atkskl[0][0]); //技能威力

        atkskl[1][0] = friend2skl[0]; //技能名稱
        atkskl[1][1] = getSkillAttributes(atkskl[1][0]);// 技能屬性
        SkillAtk[1] = getSkillAtk(atkskl[1][0]); //技能威力

        atkskl[2][0] = friend3skl[0]; //技能名稱
        atkskl[2][1] = getSkillAttributes(atkskl[2][0]);// 技能屬性
        SkillAtk[2] = getSkillAtk(atkskl[2][0]); //技能威力
        for(int i=0;i<=2;i++){
            enemysklatk[i] = getSkillAtk(enemyskl[i]);//敵方技能威力
            enemysklatt[i] = getSkillAttributes(enemyskl[i]);//敵方技能屬性
        }



        BattleSkl1 = (Spinner)findViewById(R.id.BattleSkl1);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,friend1skl);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BattleSkl1.setAdapter(adapter1);
        BattleSkl1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                Toast.makeText(BattleAnimation.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                atkskl[0][0] = adapterView.getSelectedItem().toString(); //技能名稱
                atkskl[0][1] = getSkillAttributes(atkskl[0][0]);// 技能屬性
                SkillAtk[0] = getSkillAtk(atkskl[0][0]); //技能威力
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(BattleAnimation.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });

        BattleSkl2 = (Spinner)findViewById(R.id.BattleSkl2);
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,friend2skl);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BattleSkl2.setAdapter(adapter2);
        BattleSkl2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                Toast.makeText(BattleAnimation.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                atkskl[1][0] = adapterView.getSelectedItem().toString(); //技能名稱
                SkillAtk[1] = getSkillAtk(atkskl[1][0]); //技能威力
                atkskl[1][1] = getSkillAttributes(atkskl[1][0]);// 技能屬性

            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(BattleAnimation.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();

            }
        });

        BattleSkl3 = (Spinner)findViewById(R.id.BattleSkl3);
        ArrayAdapter adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,friend3skl);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BattleSkl3.setAdapter(adapter3);
        BattleSkl3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                Toast.makeText(BattleAnimation.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                atkskl[2][0] = adapterView.getSelectedItem().toString(); //技能名稱
                SkillAtk[2] = getSkillAtk(atkskl[2][0]); //技能威力
                atkskl[2][1] = getSkillAttributes(atkskl[2][0]);// 技能屬性
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(BattleAnimation.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
        //敵方圖片
        enemy1 = (ImageView)findViewById(R.id.enemy1);
        enemy1.setImageResource(enemyresid[0]);
        enemy2 = (ImageView)findViewById(R.id.enemy2);
        enemy2.setImageResource(enemyresid[1]);
        enemy3 = (ImageView)findViewById(R.id.enemy3);
        enemy3.setImageResource(enemyresid[2]);
        //敵方名稱
        EnemyName1 = (TextView)findViewById(R.id.EnemyName1);
        EnemyName1.setText(enemyname[0]);
        EnemyName2 = (TextView)findViewById(R.id.EnemyName2);
        EnemyName2.setText(enemyname[1]);
        EnemyName3 = (TextView)findViewById(R.id.EnemyName3);
        EnemyName3.setText(enemyname[2]);
        //敵方血量
        EnemyHp1 = (TextView)findViewById(R.id.EnemyHp1);
        EnemyHp1.setText("100%");
        BattleEnemyHp[0] = Integer.parseInt(enemyhp[0]);
        EnemyHp2 = (TextView)findViewById(R.id.EnemyHp2);
        EnemyHp2.setText("100%");
        BattleEnemyHp[1] = Integer.parseInt(enemyhp[1]);
        EnemyHp3 = (TextView)findViewById(R.id.EnemyHp3);
        EnemyHp3.setText("100%");
        BattleEnemyHp[2] = Integer.parseInt(enemyhp[2]);

        BattleEnemyHp[0]=EnemyDef[0]*BattleEnemyHp[0]/200;//換標準血量
        BattleEnemyHp[1]=EnemyDef[1]*BattleEnemyHp[1]/200;
        BattleEnemyHp[2]=EnemyDef[2]*BattleEnemyHp[2]/200;
        BattleFriendHp1=FriendDef[0]*BattleFriendHp1/200;
        BattleFriendHp2=FriendDef[1]*BattleFriendHp2/200;
        BattleFriendHp3=FriendDef[2]*BattleFriendHp3/200;
        BEH[0]=BattleEnemyHp[0];
        BEH[1]=BattleEnemyHp[1];
        BEH[2]=BattleEnemyHp[2];
        BFH[0]=BattleFriendHp1;
        BFH[1]=BattleFriendHp2;
        BFH[2]=BattleFriendHp3;
        AR(1,2);
        Log.d("hpp1", String.valueOf(BattleEnemyHp[0]));
        Log.d("hpp2", String.valueOf(BattleEnemyHp[1]));
        Log.d("hpp3", String.valueOf(BattleEnemyHp[2]));
        effect1=(TextView)findViewById(R.id.effect1);
        effect2=(TextView)findViewById(R.id.effect2);
        effect3=(TextView)findViewById(R.id.effect3);
        effect4=(TextView)findViewById(R.id.effect4);
        effect5=(TextView)findViewById(R.id.effect5);
        effect6=(TextView)findViewById(R.id.effect6);
        for(int i=0;i<=2;i++){//獲得攻擊倍率
            FriendBattleRe[i] = AttR[AttrP(atkskl[i][1])][AttrP(Enemyatt[i])];//我方打敵方時屬性倍率
            EnemyBattleRe[i] =  AttR[AttrP(enemysklatt[i])][AttrP(Friendatt[i])];//敵方攻擊我方時屬性倍率
            Log.d("enemysklatt",enemysklatt[i]);
            Log.d("Friendatt",Friendatt[i]);
            Log.d("FriendBattleRe[i]", String.valueOf(FriendBattleRe[i]));
            Log.d("EnemyBattleRe[i]", String.valueOf(EnemyBattleRe[i]));
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void handleAnimation(View view){
        atkbutton.setVisibility(View.INVISIBLE);
        effect1.setVisibility(View.INVISIBLE);
        effect2.setVisibility(View.INVISIBLE);
        effect3.setVisibility(View.INVISIBLE);
        effect4.setVisibility(View.INVISIBLE);
        effect5.setVisibility(View.INVISIBLE);
        effect6.setVisibility(View.INVISIBLE);
        //我方攻擊
        ObjectAnimator pkfriend1Y = ObjectAnimator.ofFloat(pkfriend1, "y", move[0]);
        pkfriend1Y.setDuration(animationDuration);

        ObjectAnimator pkfriend2Y = ObjectAnimator.ofFloat(pkfriend2,"y",move[1]);
        pkfriend2Y.setDuration(animationDuration);

        ObjectAnimator pkfriend3Y = ObjectAnimator.ofFloat(pkfriend3,"y",move[2]);
        pkfriend3Y.setDuration(animationDuration);

        ObjectAnimator pkfriend4Y = ObjectAnimator.ofFloat(pkfriend1,"y",825);
        pkfriend1Y.setDuration(animationDuration);

        ObjectAnimator pkfriend5Y = ObjectAnimator.ofFloat(pkfriend2,"y",825);
        pkfriend2Y.setDuration(animationDuration);

        ObjectAnimator pkfriend6Y = ObjectAnimator.ofFloat(pkfriend3,"y",825);
        pkfriend3Y.setDuration(animationDuration);

        //敵方攻擊
        ObjectAnimator pkfriend7Y = ObjectAnimator.ofFloat(enemy1,"y",move2[0]);
        pkfriend1Y.setDuration(animationDuration);

        ObjectAnimator pkfriend8Y = ObjectAnimator.ofFloat(enemy2,"y",move2[1]);
        pkfriend2Y.setDuration(animationDuration);

        ObjectAnimator pkfriend9Y = ObjectAnimator.ofFloat(enemy3,"y",move2[2]);
        pkfriend3Y.setDuration(animationDuration);

        ObjectAnimator pkfriend10Y = ObjectAnimator.ofFloat(enemy1,"y",413);
        pkfriend1Y.setDuration(animationDuration);

        ObjectAnimator pkfriend11Y = ObjectAnimator.ofFloat(enemy2,"y",413);
        pkfriend2Y.setDuration(animationDuration);

        ObjectAnimator pkfriend12Y = ObjectAnimator.ofFloat(enemy3,"y",413);
        pkfriend3Y.setDuration(animationDuration);


        AnimatorSet animatorSet = new AnimatorSet();
        //animatorSet.playTogether(pkfriend1Y,pkfriend2Y,pkfriend3Y);
        animatorSet.playSequentially(pkfriend1Y,pkfriend4Y,pkfriend2Y,pkfriend5Y,pkfriend3Y,pkfriend6Y,pkfriend7Y,pkfriend10Y,pkfriend8Y,pkfriend11Y,pkfriend9Y,pkfriend12Y);
        animatorSet.start();
        //攻擊音效
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                atkbutton.setVisibility(View.VISIBLE);
            }
        },6000);
        if (BattleEnemyHp[0]!=0 && BattleFriendHp1!=0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }, 1000);
        }
        if (BattleEnemyHp[1]!=0 && BattleFriendHp2!=0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }, 2000);
        }
        if (BattleEnemyHp[2]!=0 && BattleFriendHp3!=0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }, 3000);
        }
        if (BattleEnemyHp[0]!=0&& BattleFriendHp1!=0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }, 3750);
        }
        if (BattleEnemyHp[1]!=0&& BattleFriendHp2!=0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }, 4500);
        }
        if (BattleEnemyHp[2]!=0&& BattleFriendHp3!=0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }, 5250);
        }
        final NumberFormat nf   =   NumberFormat.getPercentInstance();


        //(SkillAtk[n]+FriendAtk[n])/10*FriendBattleRe[n] = (技能威力+友方攻擊力)/10*剋數倍率
        BattleEnemyHp[0] -= (SkillAtk[0]+FriendAtk[0])/10*EnemyBattleRe[0];
        Log.d("KORSGDO", String.valueOf((SkillAtk[0]+FriendAtk[0])/10*EnemyBattleRe[0]));
        BEH1[0]=BattleEnemyHp[0]/BEH[0];
        if(BattleEnemyHp[0] <= 0) {
            BEH1[0] = 0;
            BattleEnemyHp[0] = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EnemyHp1.setText("HP "+String.valueOf(nf.format(BEH1[0])));
            }
        }, 1000);

        if (EnemyBattleRe[0]==0.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect1.setText("沒有效果");
                }
            },900);
        }
        if (EnemyBattleRe[0]==0.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect1.setText("效果不理想");
                }
            },900);
        }
        if (EnemyBattleRe[0]==1.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect1.setText("效果一般");
                }
            },900);
        }
        if (EnemyBattleRe[0]==1.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect1.setText("效果顯著");
                }
            },900);
        }


        BattleFriendHp1 -= (enemysklatk[0]+EnemyAtk[0])/10*FriendBattleRe[0];
        BFH1[0]=BattleFriendHp1/BFH[0];
        if(BattleFriendHp1 <= 0) {
            BFH1[0] = 0;
            BattleFriendHp1 = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FriendHp1.setText("HP "+String.valueOf(nf.format(BFH1[0])));
            }
        }, 4000);
        if (FriendBattleRe[0]==0.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect4.setText("沒有效果");
                }
            },3750);
        }
        if (FriendBattleRe[0]==0.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect4.setText("效果不理想");
                }
            },3750);
        }
        if (FriendBattleRe[0]==1.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect4.setText("效果一般");
                }
            },3750);
        }
        if (FriendBattleRe[0]==1.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect4.setText("效果顯著");
                }
            },3750);
        }

        BattleEnemyHp[1] -=(SkillAtk[1]+FriendAtk[1])/10*EnemyBattleRe[1];
        BEH1[1]=BattleEnemyHp[1]/BEH[1];
        if(BattleEnemyHp[1] <= 0) {
            BEH1[1] = 0;
            BattleEnemyHp[1] = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EnemyHp2.setText("HP "+String.valueOf(nf.format(BEH1[1])));
            }
        }, 2000);

        if (EnemyBattleRe[1]==0.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect2.setText("沒有效果");
                }
            },1800);
        }

        if (EnemyBattleRe[1]==0.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect2.setText("效果不理想");
                }
            },1800);
        }
        if (EnemyBattleRe[1]==1.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect2.setText("效果一般");
                }
            },1800);
        }
        if (EnemyBattleRe[1]==1.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect2.setText("效果顯著");
                }
            },1800);
        }
        BattleFriendHp2 -= (enemysklatk[1]+EnemyAtk[1])/10*FriendBattleRe[1];
        BFH1[1]=BattleFriendHp2/BFH[1];
        if(BattleFriendHp2 <= 0) {
            BFH1[1] = 0;
            BattleFriendHp2 = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FriendHp2.setText("HP "+String.valueOf(nf.format(BFH1[1])));
            }
        }, 5000);

        if (FriendBattleRe[1]==0.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect5.setText("沒有效果");
                }
            },4200);
        }
        if (FriendBattleRe[1]==0.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect5.setText("效果不理想");
                }
            },4200);
        }

        if (FriendBattleRe[1]==1.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect5.setText("效果一般");
                }
            },4200);
        }
        if (FriendBattleRe[1]==1.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect5.setText("效果顯著");
                }
            },4200);
        }


        BattleEnemyHp[2] -= (SkillAtk[2]+FriendAtk[2])/10*EnemyBattleRe[2];
        BEH1[2]=BattleEnemyHp[2]/BEH[2];
        if(BattleEnemyHp[2] <= 0) {
            BattleEnemyHp[2] = 0;
            BEH1[2] = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EnemyHp3.setText("HP "+String.valueOf(nf.format(BEH1[2])));
            }
        }, 3000);

        if (EnemyBattleRe[2]==0.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect3.setText("沒有效果");
                }
            },2700);
        }

        if (EnemyBattleRe[2]==0.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect3.setText("效果不理想");
                }
            },2700);
        }

        if (EnemyBattleRe[2]==1.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect3.setText("效果一般");
                }
            },2700);
        }

        if (EnemyBattleRe[2]==1.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect3.setText("效果顯著");
                }
            },2700);
        }

        BattleFriendHp3 -= (enemysklatk[2]+EnemyAtk[2])/10*FriendBattleRe[2];
        BFH1[2]=BattleFriendHp3/BFH[2];
        if(BattleFriendHp3 <= 0) {
            BFH1[2] = 0;
            BattleFriendHp3 = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FriendHp3.setText("HP "+String.valueOf(nf.format(BFH1[2])));
            }
        }, 6000);

        if (FriendBattleRe[2]==0.0)
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect6.setText("沒有效果");
                }
            },5250);
        }

        if (FriendBattleRe[2]==0.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect6.setText("效果不理想");
                }
            },5250);
        }
        if (FriendBattleRe[2]==1.0){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect6.setText("效果一般");
                }
            },5250);
        }

        if (FriendBattleRe[2]==1.5){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect6.setText("效果顯著");
                }
            },5250);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    effect1.setVisibility(View.VISIBLE);
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                effect2.setVisibility(View.VISIBLE);
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                effect3.setVisibility(View.VISIBLE);
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                effect4.setVisibility(View.VISIBLE);
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                effect5.setVisibility(View.VISIBLE);
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                effect6.setVisibility(View.VISIBLE);
            }
        },2000);

        pkcount=0;
        if(BattleEnemyHp[0]==0) {
            pkcount++;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemy1.setVisibility(View.INVISIBLE);
                    FriendHp1.setVisibility(View.INVISIBLE);
                    catchbutton.setVisibility(View.VISIBLE);
                }
            }, 6000);
        }
        if(BattleEnemyHp[1]==0) {
            pkcount++;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemy2.setVisibility(View.INVISIBLE);
                    FriendHp2.setVisibility(View.INVISIBLE);
                    catchbutton.setVisibility(View.VISIBLE);
                }
            }, 6000);
        }
        if(BattleEnemyHp[2]==0) {
            pkcount++;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemy3.setVisibility(View.INVISIBLE);
                    FriendHp3.setVisibility(View.INVISIBLE);
                    catchbutton.setVisibility(View.VISIBLE);
                }
            }, 6000);
        }

        if(BattleFriendHp1==0) {
            pkcount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pkfriend1.setVisibility(View.INVISIBLE);
                    EnemyHp1.setVisibility(View.INVISIBLE);
                }
            }, 6000);
        }
        if(BattleFriendHp2==0) {
            pkcount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pkfriend2.setVisibility(View.INVISIBLE);
                    EnemyHp2.setVisibility(View.INVISIBLE);
                }
            }, 6000);
        }
        if(BattleFriendHp3==0) {
            pkcount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pkfriend3.setVisibility(View.INVISIBLE);
                    EnemyHp3.setVisibility(View.INVISIBLE);
                }
            }, 6000);
        }
        if(BattleEnemyHp[0]!=0)
            move[0]=413;
        else
            move[0]=825;
        if(BattleEnemyHp[1]!=0)
            move[1]=413;
        else
            move[1]=825;
        if(BattleEnemyHp[2]!=0)
            move[2]=413;
        else
            move[2]=825;
        if(BattleFriendHp1!=0)
            move2[0]=825;
        else
            move2[0]=413;
        if(BattleFriendHp2!=0)
            move2[1]=825;
        else
            move2[1]=413;
        if(BattleFriendHp3!=0)
            move2[2]=825;
        else
            move2[2]=413;

        Log.d("hp1", String.valueOf(BattleEnemyHp[0]));
        Log.d("hp2", String.valueOf(BattleEnemyHp[1]));
        Log.d("hp3", String.valueOf(BattleEnemyHp[2]));
        Log.d("hppp1", String.valueOf(BEH1[0]));
        Log.d("hppp2", String.valueOf(BEH1[1]));
        Log.d("hppp3", String.valueOf(BEH1[2]));
        if(pkcount>=3){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(BattleAnimation.this , BattleEnd.class);
                    Bundle bundle1 = new Bundle();
                    Bundle bundle2 = new Bundle();
                    Bundle bundle3 = new Bundle();
                    Bundle bundle4 = new Bundle();
                    bundle1.putIntArray("im",enemyresid );
                    bundle2.putStringArray("name",enemyname );
                    bundle3.putIntArray("catch",BattleEnemyHp );
                    bundle4.putStringArray("enemynum",enemynumber);
                    intent.putExtras(bundle1);
                    intent.putExtras(bundle2);
                    intent.putExtras(bundle3);
                    intent.putExtras(bundle4);
                    startActivity(intent);
                    finish();
                }
            }, 6500);

        }
    }
    //獲取技能屬性
    public String getSkillAttributes(String SkillAttributes){
        DbManager manager = new DbManager(BattleAnimation.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor a = db.rawQuery("SELECT  * FROM Skill WHERE 中文名 = " + "'" + SkillAttributes + "' " , null);
        String[] colNames = a.getColumnNames();
        a.moveToFirst();
        SkillAttributes = a.getString(a.getColumnIndex(colNames[4]));
        return SkillAttributes;
    }
    //獲取技能威力
    public int getSkillAtk(String Skill){
        Random ran = new Random();
        DbManager manager = new DbManager(BattleAnimation.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor a = db.rawQuery("SELECT  * FROM Skill WHERE 中文名 = " + "'" + Skill + "' " , null);
        String[] colNames = a.getColumnNames();
        a.moveToFirst();
        Skill = a.getString(a.getColumnIndex(colNames[6]));
        int SkillAtk = Integer.parseInt(Skill);
        if(SkillAtk == 0){
            SkillAtk = ran.nextInt(100)+1;
        }
        return SkillAtk;
    }

    public void getskill(){
        DbManager manager = new DbManager(BattleAnimation.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor a = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonNumber = " + "'" + friendid[0] + "' " , null);
        String[] colNames = a.getColumnNames();
        a.moveToFirst();  // 第1筆
        friend1skl[0] = a.getString(a.getColumnIndex(colNames[11]));
        friend1skl[1] = a.getString(a.getColumnIndex(colNames[12]));
        friend1skl[2] = a.getString(a.getColumnIndex(colNames[13]));
        Cursor b = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonNumber = " + "'" + friendid[1] + "' " , null);
        b.moveToFirst();  // 第1筆
        friend2skl[0] = b.getString(b.getColumnIndex(colNames[11]));
        friend2skl[1] = b.getString(b.getColumnIndex(colNames[12]));
        friend2skl[2] = b.getString(b.getColumnIndex(colNames[13]));
        Cursor c = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonNumber = " + "'" + friendid[2] + "' " , null);
        c.moveToFirst();  // 第1筆
        friend3skl[0] = c.getString(c.getColumnIndex(colNames[11]));
        friend3skl[1] = c.getString(c.getColumnIndex(colNames[12]));
        friend3skl[2] = c.getString(c.getColumnIndex(colNames[13]));
        Cursor d = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonNumber = " + "'" + enemynumber[0] + "' " , null);
        d.moveToFirst();  // 第1筆
        enemyskl[0] = d.getString(d.getColumnIndex(colNames[11]));
        d = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonNumber = " + "'" + enemynumber[1] + "' " , null);
        d.moveToFirst();
        enemyskl[1] = d.getString(d.getColumnIndex(colNames[11]));
        d = db.rawQuery("SELECT  * FROM Pokemon WHERE PokemonNumber = " + "'" + enemynumber[2] + "' " , null);
        d.moveToFirst();
        enemyskl[2] = d.getString(d.getColumnIndex(colNames[11]));
    }
    public void getEnemyValue(){
        DbManager manager = new DbManager(BattleAnimation.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        for(int i=0 ; i<=2 ; i++) {
            Cursor a = db.rawQuery("SELECT  * FROM Pokemon WHERE  PokemonNumber = " + "'" + enemynumber[i] + "' ", null);
            String[] colNames = a.getColumnNames();
            a.moveToFirst();
            EnemyAtk[i] = a.getInt(a.getColumnIndex(colNames[8]));
            EnemyDef[i] = a.getInt(a.getColumnIndex(colNames[9]));
            Enemyatt[i] = a.getString(a.getColumnIndex(colNames[5]));
            Cursor b = db.rawQuery("SELECT  * FROM Pokemon WHERE  PokemonNumber = " + "'" +friendid[i] + "' ", null);
            String[] colNames1 = b.getColumnNames();
            b.moveToFirst();
            FriendAtk[i] = b.getInt(b.getColumnIndex(colNames[8]));
            FriendDef[i] = b.getInt(b.getColumnIndex(colNames[9]));
            Friendatt[i] = b.getString(b.getColumnIndex(colNames[5]));
        }
    }
    public int AttrP(String AP){
        String TED = "SELECT AttributesNumber FROM Attributes WHERE AttributesName ='"+AP+"'";
        DbManager manager = new DbManager(BattleAnimation.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery(TED, null);
        String[] colNames = c.getColumnNames();
        c.moveToFirst();  // 第1筆
        int AttributesNumber = c.getInt(c.getColumnIndex(colNames[0]));
        return AttributesNumber;
    }

    public void AR(int a,int b){   //回傳剋屬 a屬攻b屬
        DbManager manager = new DbManager(BattleAnimation.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c = db.rawQuery("SELECT  一般,格鬥,毒,地面,飛行,蟲,岩石,幽靈,鋼,火,水,電,草,冰,超能力,龍,惡,妖精 FROM AttributesRelationship ORDER BY Number ASC", null);
        String[] colNames = c.getColumnNames();
        c.moveToFirst();  // 第1筆
        String ted90327 = "";
        for(int i=0;i<=17;i++){
            for(int j=0;j<=17;j++){
                AttR[i][j] = c.getFloat(c.getColumnIndex(colNames[j]));
                ted90327 +=AttR[i][j]+",\n";
            }
            c.moveToNext();
        }
        Log.v("ted90327", ted90327);
        /*for(int i=0;i<=17;i++){
            for(int j=0;j<=17;j++){
                AttR[i][j]= c.getFloat(c.getColumnIndex(colNames[j]));
                Log.d("ted90325", String.valueOf(AttR[i][j]));
            }
            c.moveToNext();
        }*/
    }

}
