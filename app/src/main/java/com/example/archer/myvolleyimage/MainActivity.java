package com.example.archer.myvolleyimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void  init(){
        imageView= (ImageView) findViewById(R.id.imageView);
        LoadImageVolley();
    }

    public void LoadImageVolley(){

        String imageUrl="http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        //指定大小
        final LruCache<String,Bitmap>lruCache=new LruCache<>(20);
        ImageLoader.ImageCache imageCache=new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {

                lruCache.put(key,value);
            }
        };
        ImageLoader imageLoader=new ImageLoader(requestQueue,imageCache);

        ImageLoader.ImageListener listener= ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(imageUrl,listener);
    }
}
