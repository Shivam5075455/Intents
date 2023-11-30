package com.example.uploadimageusingintents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQ_CODE = 100;
    private static final int GALLERY_REQ_CODE = 101;
    private Button btnCamera;
    private Button btnGallery;
    private ImageView imgCamera;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgCamera =  findViewById(R.id.imgCamera);
        btnCamera =  findViewById(R.id.btnCamera);
        btnGallery =  findViewById(R.id.btnGallery);

//        This intent is to open camera
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                if (iCamera.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                    startActivityForResult(iCamera, CAMERA_REQ_CODE);
                }
            }
        });
//        this intent is to open gallery
        this.btnGallery.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent iGallery = new Intent("android.intent.action.PICK");
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
    }

// this onActivityResult define for above two intents(Camera and Gallery)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmapImage;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && data != null) {
            if (requestCode == 100) {
                Bundle extras = data.getExtras();
                if (extras != null && (bitmapImage = (Bitmap) extras.get("data")) != null) {
                    imgCamera.setImageBitmap(bitmapImage);
                    imgCamera.setVisibility(View.VISIBLE);
                }
            } else if (requestCode == 101) {
                imgCamera.setImageURI(data.getData());
            }
        }
    }
}
/*  This code is to open image chooser, that means it gives options to user to select image from camera or gallery

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_CODE = 2;
    private static final int REQUEST_CHOOSER = 3;

    tvEPChangeProfile.setOnClickListener(v -> {
            openImageChooser();
        });

    public void openImageChooser(){

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooseIntent = Intent.createChooser(pickIntent,"Select Image");
        chooseIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePhotoIntent});
        startActivityForResult(chooseIntent,REQUEST_CHOOSER);
    }

        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK && data!=null && data.getData()!=null){
//          Image from gallery
            imageUri = data.getData();
            imgEPProfileImage.setImageURI(imageUri);
            uploadProfileImage();
        }else{
            Bundle bundle = data.getExtras();
            Bitmap bitmapImage = (Bitmap) bundle.get("data");
            imgEPProfileImage.setImageBitmap(bitmapImage);
            uploadProfileImage();

        }
    }



 */