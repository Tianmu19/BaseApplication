/*
 * Copyright (C) 2018 Jenly Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tianmu19.ui.activity;

import android.hardware.Camera;
import android.view.View;
import android.widget.TextView;

import com.github.tianmu19.R;
import com.github.tianmu19.baselibrary.utils.klogutil.KLog;
import com.github.tianmu19.utils.SPUtils;
import com.king.zxing.CaptureActivity;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class CustomCaptureActivity extends CaptureActivity implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.custom_capture_activity;
    }

    @Override
    public void processVisiableThings() {
        super.processVisiableThings();
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("扫码");
        findViewById(R.id.ivFlash).setVisibility(View.VISIBLE);
        getBeepManager().setPlayBeep(true);
        getBeepManager().setVibrate(true);
        KLog.e("_用时："+ (double)(System.currentTimeMillis()- SPUtils.getLong("time",0L))/1000D+"  s");
        findViewById(R.id.ivLeft).setOnClickListener(this);
        findViewById(R.id.ivFlash).setOnClickListener(this);
    }

    private void offFlash(){
        Camera camera = getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }

    public void openFlash(){
        Camera camera = getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
    }


    private void clickFlash(View v){
        if(v.isSelected()){
            offFlash();
            v.setSelected(false);
        }else{
            openFlash();
            v.setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivLeft:
                onBackPressed();
                break;
            case R.id.ivFlash:
                clickFlash(v);
                break;
        }
    }
}
