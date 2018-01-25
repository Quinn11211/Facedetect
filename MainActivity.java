package com.example.qpm1.fuckthis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity; import android.os.Bundle;
import android.view.View; import android.widget.Button;
import android.widget.ImageView;

import org.opencv.core.Mat; import org.opencv.core.Core; import org.opencv.android.Utils;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements View. OnClickListener { ImageView imageView1,imageView2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super. onCreate(savedInstanceState); setContentView (R.layout.activity_main);
        System. loadLibrary ("opencv_java3");
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.pen); imageView1=(ImageView)findViewById(R.id.imageView); imageView2=(ImageView)findViewById(R.id.imageView2);
        btn =(Button)findViewById(R.id.button); imageView1.setImageBitmap(img);

        btn. setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.pen); Mat source = new Mat();
        Mat dest = new Mat();
        Utils. bitmapToMat( img, source);

        Imgproc. cvtColor(source, dest, Imgproc.COLOR_RGB2GRAY);
        Bitmap btmp = Bitmap.createBitmap(dest.width(),dest.height(),Bitmap.Config.ARGB_8888); Utils. matToBitmap(dest,btmp);
        imageView1. setImageBitmap (btmp);

    }
}