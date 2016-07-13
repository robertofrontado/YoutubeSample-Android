package app.presentation.sections.videos;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.frontado.youtubesample.R;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import app.domain.sections.Video;
import app.presentation.foundation.views.BaseFragment;
import app.presentation.foundation.views.LayoutResFragment;
import butterknife.Bind;
import library.recycler_view.OkRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
@LayoutResFragment(R.layout.fragment_channels)
public class VideosFragment extends BaseFragment<VideosPresenter> {

    @Bind(R.id.rv_videos) protected SuperRecyclerView rv_videos;
    private OkRecyclerViewAdapter<Video, VideoViewGroup> adapter;
    final String TAG = "YOUTUBE_SAMPLE";

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
        setUpRecyclerView();

        presenter.getVideos()
                .compose(safelyReportLoading())
                .subscribe(youtubeVideosResponse -> {
                    Log.d(TAG, youtubeVideosResponse.toString());
                    populateViews(youtubeVideosResponse.getVideos());
                });
    }

    private void setUpRecyclerView() {
        adapter = new ListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_videos.setLayoutManager(layoutManager);
        rv_videos.setAdapter(adapter);
    }

    private void populateViews(List<Video> videos) {
        adapter.setAll(videos);
    }

    class ListAdapter extends OkRecyclerViewAdapter {

        @Override
        protected View onCreateItemView(ViewGroup parent, int viewType) {
            return new VideoViewGroup(parent.getContext());
        }
    }
}
