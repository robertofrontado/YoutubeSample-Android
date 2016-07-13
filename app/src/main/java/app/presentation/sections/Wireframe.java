/*
 * Copyright 2016 FuckBoilerplate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.presentation.sections;

import android.content.Intent;
import android.os.Bundle;

import com.frontado.youtubesample.R;

import javax.inject.Inject;

import app.presentation.foundation.BaseApp;
import app.presentation.foundation.views.BaseActivity;
import app.presentation.foundation.views.SingleActivity;
import app.presentation.sections.videos.VideosFragment;
import app.presentation.sections.session.LoginFragment;

/**
 * Provides the routing for the application screens.
 */
public class Wireframe {
    private final BaseApp baseApp;

    @Inject public Wireframe(BaseApp baseApp) {
        this.baseApp = baseApp;
    }

    public void popCurrentScreen() {
        baseApp.getLiveActivity().finish();
    }

    public void login() {
        Bundle bundle = new Bundle();
        bundle.putString(BaseActivity.Behaviour.TITLE_KEY, baseApp.getString(R.string.login));
        bundle.putBoolean(BaseActivity.Behaviour.SHOW_BACK_KEY, false);
        bundle.putSerializable(BaseActivity.Behaviour.FRAGMENT_CLASS_KEY, LoginFragment.class);

        Intent intent = new Intent(baseApp, SingleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(bundle);
        baseApp.getLiveActivity().startActivity(intent);
    }

    public void channels() {
        Bundle bundle = new Bundle();
        bundle.putString(BaseActivity.Behaviour.TITLE_KEY, baseApp.getString(R.string.channels_list));
        bundle.putBoolean(BaseActivity.Behaviour.SHOW_BACK_KEY, false);
        bundle.putSerializable(BaseActivity.Behaviour.FRAGMENT_CLASS_KEY, VideosFragment.class);

        Intent intent = new Intent(baseApp, SingleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(bundle);
        baseApp.getLiveActivity().startActivity(intent);
    }
}
