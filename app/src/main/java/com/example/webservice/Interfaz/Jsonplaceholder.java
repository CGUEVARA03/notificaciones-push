package com.example.webservice.Interfaz;

import com.example.webservice.modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Jsonplaceholder {

    @GET("posts")
    Call<List<Posts>> getPosts();

}
