/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.android.settings.accessibility;

import static android.app.Activity.RESULT_CANCELED;

import android.app.settings.SettingsEnums;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.android.settings.R;

import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupdesign.GlifPreferenceLayout;

public class ToggleScreenReaderPreferenceFragmentForSetupWizard
        extends ToggleAccessibilityServicePreferenceFragment {

    private boolean mToggleSwitchWasInitiallyChecked;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToggleSwitchWasInitiallyChecked = mToggleServiceSwitchPreference.isChecked();
        if (mTopIntroPreference != null) {
            mTopIntroPreference.setVisible(false);
        }
    }

    @Override
    public int getMetricsCategory() {
        return SettingsEnums.SUW_ACCESSIBILITY_TOGGLE_SCREEN_READER;
    }

    @Override
    public void onStop() {
        // Log the final choice in value if it's different from the previous value.
        if (mToggleServiceSwitchPreference.isChecked() != mToggleSwitchWasInitiallyChecked) {
            mMetricsFeatureProvider.action(getContext(),
                    SettingsEnums.SUW_ACCESSIBILITY_TOGGLE_SCREEN_READER,
                    mToggleServiceSwitchPreference.isChecked());
        }
        super.onStop();
    }
}
