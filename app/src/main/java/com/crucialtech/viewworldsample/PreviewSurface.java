package com.crucialtech.viewworldsample;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class PreviewSurface extends SurfaceView implements SurfaceHolder.Callback {
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;

    public PreviewSurface(Context context, Camera camera) {
        super(context);

        mCamera = camera;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mSurfaceHolder.getSurface() == null){
            return;
        }

        mCamera.startPreview();
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
