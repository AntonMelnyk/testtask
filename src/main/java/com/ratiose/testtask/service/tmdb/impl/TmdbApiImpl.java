package com.ratiose.testtask.service.tmdb.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class TmdbApiImpl implements TmdbApi {
    private static final String POPULAR_MOVIE_URL = "/movie/popular";
    private static final String FIND_ACTOR_BY_ID_URL = "/person/";
    private static final String FIND_MOVIE_BY_ID_URL = "/movie/";
    private static final String FIND_BY_YEAR_URL = "/search/movie?query=&primary_release_year=";
    @Value("${tmdb.apikey}")
    private String tmdbApiKey;
    @Value("${tmdb.language}")
    private String tmdbLanguage;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    public String popularMovies() {
        return execute(POPULAR_MOVIE_URL);
    }

    public String findActorById(Long id) {
       return execute(FIND_ACTOR_BY_ID_URL + id);
    }   
    
    public String findMovieById(Long id) {
       return execute(FIND_MOVIE_BY_ID_URL + id);
    }

    public String findByYear(int year) {
        return execute(FIND_BY_YEAR_URL+year);
    }
    
    private String execute(String path) {
        try {
            String url = getTmdbUrl(path);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();

            if (jsonResponse.getStatus() != HttpStatus.SC_OK) {
                return null;
            }

            String responseJSONString = jsonResponse.getBody().toString();

            return responseJSONString;
        } catch (UnirestException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTmdbUrl(String tmdbItem) throws URISyntaxException {
        StringBuilder builder = new StringBuilder(tmdbApiBaseUrl);
        builder.append(tmdbItem);
        URIBuilder uriBuilder = new URIBuilder(builder.toString());
        uriBuilder.addParameter("language", tmdbLanguage);
        uriBuilder.addParameter("api_key", tmdbApiKey);
        return uriBuilder.build().toString();
    }
}
