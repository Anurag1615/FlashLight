package com.example.flashlight;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.Provider;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {


ImageView Light;
Camera cam;
    boolean isTourchOn=true;
    Camera.Parameters parameters;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
Light=findViewById(R.id.light);
        permission();


         cam = Camera.open();
             parameters=cam.getParameters();




    }


    private void permission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
    }


    }

    public void Onoff(View view) {

        if(parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_OFF)){

            isTourchOn=true;


        }
        else {
            isTourchOn=false;
        }



        if (isTourchOn==true){
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(parameters);
            cam.startPreview();
Light.setVisibility(View.VISIBLE);
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        }
        else if(isTourchOn==false){

            cam.stopPreview();

            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
Light.setVisibility(View.INVISIBLE);
        }
        else {
            Toast.makeText(getApplicationContext(),"errr",Toast.LENGTH_LONG).show();
        }
    }
}