package com.crucialtech.viewworldsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private PreviewSurface mPreviewSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mCamera = getCameraInstance();


        //initialiazeCameraParameters();

        if(mCamera == null){

        }else{
            mPreviewSurface = new PreviewSurface(this,mCamera);
            setContentView(mPreviewSurface);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void initialiazeCameraParameters() {
        Camera.Parameters parameters = mCamera.getParameters();

        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();

        Camera.Size size = getBestPreviewSize(640,480,parameters);

        int currentWidth = 0;
        int currentHeight = 0;

        boolean foundDesiredWidth = false;

        for(Camera.Size s : sizes){
            if(s.width == size.width){
                currentWidth = s.width;
                currentHeight = s.height;
                foundDesiredWidth = true;
                break;
            }
        }

        if(foundDesiredWidth){
            parameters.setPreviewSize(currentWidth,currentHeight);
        }

        mCamera.setParameters(parameters);
    }

    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;

        for(Camera.Size size :parameters.getSupportedPreviewSizes()){
            if(size.width <= width && size.height <= height){
                if(result == null){
                    result = size;
                }
                else{
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;

                    if(newArea > resultArea){
                        result = size;
                    }
                }
            }
        }

        return result;
    }

    private Camera getCameraInstance() {
        Camera c = null;

        try
        {
            c = Camera.open(0);

        }
        catch (Exception e){

        }

        return  c;
    }
}
