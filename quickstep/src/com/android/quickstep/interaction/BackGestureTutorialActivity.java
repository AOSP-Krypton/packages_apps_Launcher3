/*
 * Copyright (C) 2020 The Android Open Source Project
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
package com.android.quickstep.interaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;

import com.android.launcher3.R;
import com.android.quickstep.interaction.BackGestureTutorialFragment.TutorialStep;
import com.android.quickstep.interaction.BackGestureTutorialFragment.TutorialType;

import java.util.Optional;

/** Shows the Back gesture interactive tutorial in full screen mode. */
public class BackGestureTutorialActivity extends FragmentActivity {

    Optional<BackGestureTutorialFragment> mFragment = Optional.empty();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.back_gesture_tutorial_activity);

        mFragment = Optional.of(BackGestureTutorialFragment.newInstance(TutorialStep.ENGAGED,
                TutorialType.RIGHT_EDGE_BACK_NAVIGATION));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.back_gesture_tutorial_fragment_container, mFragment.get())
                .commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    public void onBackPressed() {
        if (mFragment.isPresent()) {
            mFragment.get().onBackPressed();
        }
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
    }
}