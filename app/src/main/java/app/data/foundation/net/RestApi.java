/*
 * Copyright 2016 FuckBoilerplate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.data.foundation.net;

import app.domain.sections.YoutubeVideosResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Definition for Retrofit of every endpoint required by the Api.
 */
public interface RestApi {
    String URL_BASE = "https://www.googleapis.com/youtube/v3/";

    @GET("channels")
    Observable<Response<Object>> getChannels(@Header("Authorization") String bearerToken,
                                             @Query("part") String part,
                                             @Query("mine") boolean mine,
                                             @Query("access_token") String accessToken);

    @GET("videos")
    Observable<Response<Object>> getVideos(@Query("part") String part,
                                           @Query("chart") String chart,
                                           @Query("key") String apiKey);

    @GET("videos")
    Observable<Response<YoutubeVideosResponse>> getVideosOAuth(@Query("access_token") String bearerToken,
                                                               @Query("part") String part,
                                                               @Query("chart") String chart,
                                                               @Query("key") String apiKey);

    @GET("search")
    Observable<Response<Object>> search(@Query("part") String part,
                                        @Query("key") String apiKey);

    @GET("search")
    Observable<Response<Object>> getRelatedVideos(@Header("Authorization") String bearerToken,
                                                  @Query("part") String part,
                                                  @Query("relatedToVideoId") String relatedToVideoId,
                                                  @Query("type") String type,
                                                  @Query("key") String apiKey);

    @POST("subscriptions")
    Observable<Response<Object>> subscribe(@Query("access_token") String bearerToken);

}

