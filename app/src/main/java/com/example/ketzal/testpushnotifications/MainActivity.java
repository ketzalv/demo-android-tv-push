/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.ketzal.testpushnotifications;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ketzal.testpushnotifications.utils.ViewTooltip;

import static com.example.ketzal.testpushnotifications.TVMessaginService.KEY_MESSAGE;
import static com.example.ketzal.testpushnotifications.TVMessaginService.SHOW_MESSAGE;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {
    View dotView;
    ViewTooltip.TooltipView tooltip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(SHOW_MESSAGE);
        registerReceiver(receiver, filter);
        dotView = findViewById(R.id.view_dot_black);
    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(KEY_MESSAGE);
            showAlert(message);
        }
    };

    private void showAlert(String message){
        FrameLayout layout = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        View view = LayoutInflater.from(layout.getContext()).inflate(R.layout.layout_push_notification, layout, true);
        TextView tvMessage = view.findViewById(R.id.text_push);
        tvMessage.setText(message);
        tooltip = ViewTooltip.on(dotView)
                .align(ViewTooltip.ALIGN.CENTER)
                .position(ViewTooltip.Position.LEFT)
                .withShadow(true)
                .shadowColor(ContextCompat.getColor(this, R.color.default_background))
                .color(ContextCompat.getColor(this, R.color.white))
                .clickToHide(true)
                .customView(layout)
                .show();
    }
}
