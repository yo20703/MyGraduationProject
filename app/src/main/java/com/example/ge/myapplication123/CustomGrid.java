package com.example.ge.myapplication123;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class CustomGrid extends BaseAdapter {
    private Context context;
    private final String[] text;
    private final String[] text1;
    private final int[] imageId;

    public CustomGrid(Context context, String[] text,String[] text1, int[] imageId) {
        this.context = context;
        this.text = text;
        this.text1 = text1;
        this.imageId = imageId;
    }

    //閹割版
    /*public CustomGrid(Context context, String[] text,String[] text1,int[] imageId) {
        this.context = context;
        this.text = text;
        this.text1 = text1;
        this.imageId=imageId;
    }*/

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bitmap bitmap = readBitMap(context, imageId[position]);
        View grid;
        // Context 動態放入mainActivity
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            // 將grid_single 動態載入(image+text)
            grid = layoutInflater.inflate(R.layout.griditem, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            TextView textView1 = (TextView) grid.findViewById(R.id.grid_text1);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(text[position]);
            textView1.setText(text1[position]);
            imageView.setImageBitmap(bitmap);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
