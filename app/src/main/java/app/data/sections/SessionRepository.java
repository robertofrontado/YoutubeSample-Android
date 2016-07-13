package app.data.sections;

import javax.inject.Inject;

import app.data.foundation.Repository;
import app.data.foundation.UIUtils;
import app.data.foundation.cache.RxProviders;
import app.data.foundation.net.RestApi;
import app.domain.sections.User;
import io.rx_cache.EvictProvider;
import rx.Observable;

/**
 * Created by robertofrontado on 7/13/16.
 */
public class SessionRepository extends Repository {

    @Inject
    public SessionRepository(RestApi restApi, RxProviders rxProviders, UIUtils uiUtils) {
        super(restApi, rxProviders, uiUtils);
    }

    public Observable<Boolean> saveUser(User user) {
        return rxProviders.getUser(Observable.just(user), new EvictProvider(true))
                .map(s -> true)
                .onErrorReturn(throwable -> false);
    }

    public Observable<User> getUser() {
        return rxProviders.getUser(Observable.just(null), new EvictProvider(false));
    }

}
