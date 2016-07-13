package app.presentation.sections.videos;

import javax.inject.Inject;

import app.data.foundation.UIUtils;
import app.data.sections.WireframeRepository;
import app.data.sections.YoutubeRepository;
import app.domain.sections.YoutubeVideosResponse;
import app.presentation.foundation.PresenterFragment;
import rx.Observable;

/**
 * Created by robertofrontado on 7/6/16.
 */
public class VideosPresenter extends PresenterFragment {

    protected final YoutubeRepository youtubeRepository;

    @Inject
    protected VideosPresenter(WireframeRepository wireframeRepository, UIUtils uiUtils, YoutubeRepository youtubeRepository) {
        super(wireframeRepository, uiUtils);
        this.youtubeRepository = youtubeRepository;
    }

    public Observable<YoutubeVideosResponse> getVideos() {
        return youtubeRepository.getVideos();
    }

}
