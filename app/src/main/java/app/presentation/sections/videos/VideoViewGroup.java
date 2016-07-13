package app.presentation.sections.videos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.frontado.youtubesample.R;
import com.squareup.picasso.Picasso;

import app.domain.sections.Video;
import butterknife.Bind;
import butterknife.ButterKnife;
import library.recycler_view.OkRecyclerViewAdapter;

/**
 * Created by robertofrontado on 7/13/16.
 */
public class VideoViewGroup extends FrameLayout implements OkRecyclerViewAdapter.Binder<Video> {

    @Bind(R.id.iv_video) protected ImageView iv_video;
    @Bind(R.id.tv_name) protected TextView tv_name;

    public VideoViewGroup(Context context) {
        super(context);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.video_view_group, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(Video video, int position) {

        tv_name.setText(video.getSnippet().getTitle());

        String thumbnail = video.getSnippet().getThumbnails().get("default").getUrl();

        Picasso.with(getContext()).load(thumbnail)
                .centerCrop()
                .fit()
                .into(iv_video);
    }
}
