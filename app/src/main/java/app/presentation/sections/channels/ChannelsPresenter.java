package app.presentation.sections.channels;

import javax.inject.Inject;

import app.data.foundation.UIUtils;
import app.data.sections.WireframeRepository;
import app.presentation.foundation.PresenterFragment;

/**
 * Created by robertofrontado on 7/6/16.
 */
public class ChannelsPresenter extends PresenterFragment {

    @Inject
    protected ChannelsPresenter(WireframeRepository wireframeRepository, UIUtils uiUtils) {
        super(wireframeRepository, uiUtils);
    }
}
