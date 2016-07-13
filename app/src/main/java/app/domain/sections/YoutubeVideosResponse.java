package app.domain.sections;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by robertofrontado on 7/13/16.
 */
@Data
public class YoutubeVideosResponse {
    private final String nextPageToken;
    @SerializedName("items")
    private final List<Video> videos;
}
