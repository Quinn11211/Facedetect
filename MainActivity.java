package com.example.qpm1.facedetectapp;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.media.MediaScannerConnection;
        import android.net.Uri;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.provider.Settings;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.AppCompatActivity;
        import org.opencv.core.Point;
        import org.opencv.core.Rect;
        import org.opencv.core.Scalar;
        import org.opencv.core.Size;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Calendar;
        import org.opencv.core.Mat;
        import org.opencv.core.Core;
        import org.opencv.android.Utils;
        import org.opencv.core.MatOfRect;
        import org.opencv.core.Rect;
        import org.opencv.imgproc.Imgproc;
        import org.opencv.objdetect.CascadeClassifier;

        import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements View. OnClickListener {
    ImageView imageView1, imageView2;
    Button btn;

    private Button btn;
    private ImageView imageview;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("opencv_java3");

        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.pen);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        btn = (Button) findViewById(R.id.button);
        imageView1.setImageBitmap(img);

        btn = (Button) findViewById(R.id.btn);
        imageview = (ImageView) findViewById(R.id.iv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

    }
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private String startActivityForResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return "Please try again";
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    long startTime = System.nanoTime();
        //    CascadeClassifier faceDetector = new CascadeClassifier("D:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
                    CascadeClassifier faceDetector = new CascadeClassifier("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
//       CascadeClassifier faceDetector = new CascadeClassifier("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_default.xml");

                    Mat image = new Mat();
                    new Mat(); Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true); Utils.bitmapToMat(bmp32, mat);


                    float size = Math.round(image.height() * 0.1f);
                    Rect rectCrop = null;
                    MatOfRect faceDetections = new MatOfRect();
                    faceDetector.detectMultiScale(image,faceDetections);
                    faceDetector.detectMultiScale(image, faceDetections, 1.1, 3, 0, new Size(90, 90),new Size(400, 400));

                     if(faceDetections.toArray().length >=1);
                        return "Not a face"
