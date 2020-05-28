package com.example.ge.myapplication123;

import java.util.logging.Level;

public class level {
    private int num;
    private String LEVEL;
    private String den;
    public level(int num,String LEVEL,String den){
        this.num=num;
        this.LEVEL=LEVEL;
        this.den=den;
    }
    public int getNum(){
        return  num;
    }
    public void setNum(int num){
        this.num=num;
    }
    public String getLEVEL(){
        return LEVEL;
    }
    public  void  setLEVEL(String LEVEL){
        this.LEVEL=LEVEL;
    }
    public String getDen(){
        return den;
    }
    public void setDen(String den){
        this.den=den;
    }

}
