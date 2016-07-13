package app.data.sections;

import com.frontado.youtubesample.R;

import javax.inject.Inject;

import app.data.foundation.Repository;
import app.data.foundation.UIUtils;
import app.data.foundation.cache.RxProviders;
import app.data.foundation.net.RestApi;
import app.domain.sections.YoutubeVideosResponse;
import io.rx_cache.EvictProvider;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by robertofrontado on 7/10/16.
 */
public class YoutubeRepository extends Repository {

    @Inject
    public YoutubeRepository(RestApi restApi, RxProviders rxProviders, UIUtils uiUtils) {
        super(restApi, rxProviders, uiUtils);
    }

    public Observable<YoutubeVideosResponse> getVideos() {
        return rxProviders.getUser(Observable.just(null), new EvictProvider(false))
                .flatMap(user -> restApi.getVideosOAuth("Bearer " + user.getToken(), "snippet", "mostPopular", uiUtils.getString(R.string.api_key)))
                .flatMap(response -> {
                    handleError(response);
                    return Observable.just(response.body());
                });
    }

}
