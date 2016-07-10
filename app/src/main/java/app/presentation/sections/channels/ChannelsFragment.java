package app.presentation.sections.channels;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frontado.youtubesample.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import app.presentation.foundation.views.BaseFragment;
import app.presentation.foundation.views.LayoutResFragment;
import butterknife.Bind;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
@LayoutResFragment(R.layout.fragment_channels)
public class ChannelsFragment extends BaseFragment<ChannelsPresenter> {

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
//                .requestId()
//                .requestProfile()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
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
//        btn_sign_in.setSize(SignInButton.SIZE_STANDARD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG + "request", ""+requestCode);
        Log.i(TAG + "result", ""+resultCode);
        Log.i(TAG + "EXTRAS", ""+data.toString());

        if (requestCode == REQUEST_CODE) {
            // [START get_id_token]
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult:GET_TOKEN:success:" + result.getStatus().isSuccess());

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                String idToken = acct.getIdToken();

                // Show signed-in UI.
                Log.d(TAG, "idToken:" + idToken);

                // TODO(user): send token to server and validate server-side
            }
        }
    }
}
