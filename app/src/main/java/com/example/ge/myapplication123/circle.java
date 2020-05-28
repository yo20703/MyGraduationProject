package com.example.ge.myapplication123;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class circle extends AppCompatActivity {
    boolean blnButtonRotation=true;
    long lngDegrees = 0;
    int intNumber = 6;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        this.sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        this.intNumber=this.sharedPreferences.getInt("INT_NUMBER",6);
        //setImageRoulette(this.intNumber);
    }

   // @Override
    public void onAnimationStart(Animation animation)
    {
        this.blnButtonRotation = false;
   //     b_start.setVisibility(View.VISIBLE);

    }

  //  @Override
    public void onAnimationEnd(Animation animation) {
     //   Toast toast = Toast.makeText(this, " " + String.valueOf((int)(((double)this.intNumber)
        //        - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.intNumber))))) + " ",0);
    //    toast.setGravity(49,0,0);
     //   toast.show();
        this.blnButtonRotation = true;
    //    b_start.setVisibility(View.VISIBLE);


    }

//    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}