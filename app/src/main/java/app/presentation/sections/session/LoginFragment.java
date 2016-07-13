package app.presentation.sections.session;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.frontado.youtubesample.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.api.services.youtube.YouTubeScopes;

import app.domain.sections.User;
import app.presentation.foundation.views.BaseFragment;
import app.presentation.foundation.views.LayoutResFragment;
import butterknife.Bind;
import rx.Observable;

/**
 * Created by robertofrontado on 7/11/16.
 */
@LayoutResFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment<LoginPresenter> {

    @Bind(R.id.btn_sign_in) SignInButton btn_sign_in;
    final String TAG = "YOUTUBE_SAMPLE";
    final int REQUEST_CODE = 123;
    GoogleApiClient mGoogleApiClient;

    @Override
        protected void injectDagger() {
            getApplicationComponent().inject(this);
        }

        @Nullable
        @Override
        protected String getScreenNameForGoogleAnalytics() {
            return null;
        }

        @Override
        protected void initViews() {
            super.initViews();
            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.server_client_id))
                    .requestEmail()
                    .requestProfile()
                    .requestScopes(new Scope(YouTubeScopes.YOUTUBE), new Scope(YouTubeScopes.YOUTUBE_READONLY))
                    .build();

            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .enableAutoManage(getActivity(), connectionResult -> {
                        showToast(Observable.just(connectionResult.toString()));
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            btn_sign_in.setScopes(gso.getScopeArray());
            btn_sign_in.setOnClickListener(v -> {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, REQUEST_CODE);
            });
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            // [START get_id_token]
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult:GET_TOKEN:success:" + result.getStatus().isSuccess());

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                String idToken = acct.getIdToken();
                String email = acct.getEmail();

                User user = new User(acct.getDisplayName(), acct.getEmail(), acct.getIdToken(), "");
                // Show signed-in UI.
                Log.d(TAG, "idToken:" + idToken);
                Log.d(TAG, "email:" + email);

                // TODO(user): send token to server and validate server-side
                presenter.saveUser(user)
                        .compose(safelyReportLoading())
                        .subscribe(aBoolean -> {
                            if (aBoolean)
                                wireframe.channels();
                            else
                                showToast(Observable.just("Error saving token"));
                        });
            }
        }
    }
}

