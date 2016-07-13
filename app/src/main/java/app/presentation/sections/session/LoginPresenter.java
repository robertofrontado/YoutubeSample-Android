package app.presentation.sections.session;

import javax.inject.Inject;

import app.data.foundation.UIUtils;
import app.data.sections.SessionRepository;
import app.data.sections.WireframeRepository;
import app.data.sections.YoutubeRepository;
import app.domain.sections.User;
import app.presentation.foundation.PresenterFragment;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by robertofrontado on 7/11/16.
 */
public class LoginPresenter extends PresenterFragment {

    protected final SessionRepository sessionRepository;

    @Inject
    protected LoginPresenter(WireframeRepository wireframeRepository, UIUtils uiUtils, SessionRepository sessionRepository) {
        super(wireframeRepository, uiUtils);
        this.sessionRepository = sessionRepository;
    }

    public Observable<Boolean> saveUser(User user) {
        return sessionRepository.saveUser(user);
    }
}
