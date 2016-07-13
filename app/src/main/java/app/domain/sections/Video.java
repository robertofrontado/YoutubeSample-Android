package app.domain.sections;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

/**
 * Created by robertofrontado on 7/13/16.
 */
@Data
public class Video {
    private final String id;
    private final Snippet snippet;

    @Data
    public class Snippet {
        private final String publishedAt;
        private final String channelId;
        private final String title;
        private final String description;
        private final HashMap<String, Thumbnail> thumbnails;

        @Data
        public class Thumbnail {
            private final String url;
            private final int width;
            private final int height;
        }

    }
}
