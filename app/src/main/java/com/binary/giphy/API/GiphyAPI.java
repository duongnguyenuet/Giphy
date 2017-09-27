package com.binary.giphy.API;

import com.binary.giphy.models.randomresult.RandomResult;
import com.binary.giphy.models.searchdetail.Data;
import com.binary.giphy.models.searchdetail.SearchResult;
import com.binary.giphy.models.searchdetail.SearchSingleResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by duong on 9/20/2017.
 */

public interface GiphyAPI {
    @GET(ApiConstants.API_SEARCH_BY_KEYWORD)
    Call<ApiResponse<List<Data>>> getSearchByKeyResult(@Query("api_key") String apiKey,
                                                       @Query("q") String searchKey,
                                                       @Query("limit") int limit,
                                                       @Query("offset") int offset,
                                                       @Query("rating") String rating,
                                                       @Query("lang") String lang,
                                                       @Query("fmt") String fmt);

    @GET("/v1/gifs/trending")
    Call<SearchResult> getSearchTrendingResult(@Query("api_key") String apiKey,
                                               @Query("q") String searchKey,
                                               @Query("limit") int limit,
                                               @Query("offset") int offset,
                                               @Query("rating") String rating,
                                               @Query("fmt") String fmt);

    @GET("/v1/gifs/translate")
    Call<SearchSingleResult> getSearchTranslateResult(@Query("api_key") String apiKey,
                                                      @Query("s") String s);

    @GET("/v1/gifs/random")
    Call<RandomResult> getSearchRandomResult(@Query("api_key") String apiKey,
                                             @Query("tag") String tag,
                                             @Query("rating") String rating,
                                             @Query("fmt") String fmt);

    @GET("/v1/gifs/{gif_id}")
    Call<SearchSingleResult> getSearchByIdResult(@Query("api_key") String apiKey,
                                                 @Query("gif_id") String id);

    @GET("/v1/gifs")
    Call<SearchResult> getSearchByIdsResult(@Query("api_key") String apiKey,
                                            @Query("ids") String ids);

    @GET("/v1/stickers/search")
    Call<SearchResult> getSearchByKeyStickerResult(@Query("api_key") String apiKey,
                                                   @Query("q") String searchKey,
                                                   @Query("limit") int limit,
                                                   @Query("offset") int offset,
                                                   @Query("rating") String rating,
                                                   @Query("lang") String lang,
                                                   @Query("fmt") String fmt);

    @GET("/v1/stickers/trending")
    Call<SearchResult> getSearchTrendingStickerResult(@Query("api_key") String apiKey,
                                                      @Query("limit") int limit,
                                                      @Query("rating") String rating,
                                                      @Query("fmt") String fmt);

    @GET("/v1/stickers/translate")
    Call<SearchSingleResult> getSearchTranslateStickerResult(@Query("api_key") String apiKey,
                                                             @Query("s") String searchKey);

    @GET("/v1/stickers/random")
    Call<SearchResult> getSearchRandomStickerResult(@Query("api_key") String apiKey,
                                                    @Query("tag") String tag,
                                                    @Query("rating") String rating,
                                                    @Query("fmt") String fmt);


}
