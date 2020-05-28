package com.example.ge.myapplication123;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private Button basis1;
    private Button basis2;
    private Button basis3;
    private Button basis4;
    private Button basis5;
    private Button basis6;
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
            intent.setClass(Main2Activity.this , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_attributes1) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "屬性表", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , attributes1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_attributes) {
            // 按下「關於」要做的事
            Toast.makeText(this, "圖鑑", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , illustration.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_battle) {
            // 按下「關於」要做的事
            Toast.makeText(this, "快速對戰", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , battle.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_main2) {
            // 按下「關於」要做的事
            Toast.makeText(this, "基礎教學", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_map) {
            // 按下「關於」要做的事
            Toast.makeText(this, "進階玩法", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , map.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_guide3) {
            // 按下「關於」要做的事
            Toast.makeText(this, "遊戲攻略", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this , guide2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id == R.id.nav_guide4) {
            // 按下「關於」要做的事
            Toast.makeText(this, "活動訊息", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this ,guide3.class);
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
        setContentView(R.layout.activity_main2);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
       // getSupportActionBar().hide(); //隱藏標題
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        ;
        basis1 = (Button)findViewById(R.id.basis1);
        basis1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(Main2Activity.this , basis01.class);
                        startActivity(intent);
                    }


        });
                basis2 = (Button)findViewById(R.id.basis2);
                basis2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(Main2Activity.this , basis2.class);
                        startActivity(intent);
                    }


                });
                        basis3 = (Button)findViewById(R.id.basis3);
                        basis3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setClass(Main2Activity.this , basis3.class);
                                startActivity(intent);
                            }


                        });
                         basis4 = (Button)findViewById(R.id.basis4);
                         basis4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent();
                                        intent.setClass(Main2Activity.this , basis4.class);
                                        startActivity(intent);
                                    }


                         });
                              basis5 = (Button)findViewById(R.id.basis5);
                              basis5.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent intent = new Intent();
                                      intent.setClass(Main2Activity.this, basis6.class);
                                      startActivity(intent);
                                  }


                              });
                                      basis6 = (Button) findViewById(R.id.basis6);
                                      basis6.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent();
                                              intent.setClass(Main2Activity.this, basis5.class);
                                              startActivity(intent);

                                          }

                                      });
                                  }
                              }

