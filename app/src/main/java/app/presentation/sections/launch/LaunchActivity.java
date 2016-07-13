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

package app.presentation.sections.launch;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.frontado.youtubesample.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import app.data.foundation.analytics.GoogleAnalyticsSender;
import app.presentation.foundation.views.BaseActivity;
import app.presentation.foundation.views.LayoutResActivity;
import butterknife.Bind;
import rx.Observable;

@LayoutResActivity(R.layout.activity_launch)
public class LaunchActivity extends BaseActivity {

    @Override protected void injectDagger() {
        getApplicationComponent().inject(this);
    }

    @Override protected void initViews() {
        super.initViews();
        // Show Channels after 2 seconds
        final Handler handler = new Handler();
        handler.postDelayed(() -> wireframe.login(), 2000);
    }

}
