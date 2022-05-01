package com.example.webservice;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.Interfaz.Jsonplaceholder;
import com.example.webservice.modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView Jsonview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Jsonview=findViewById(R.id.Jsontext);
        getPosts();
    }
    private void getPosts(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Jsonplaceholder jsonplaceholder = retrofit.create(Jsonplaceholder.class);

        Call<List<Posts>> call = jsonplaceholder.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    Jsonview.setText("codigo: "+response.code());
                    return;
                }
                List<Posts> postsList=response.body();
                for(Posts post: postsList){
                    String content = "";
                    content +="userId:"+post.getUserId() + "\n\n";
                    content +="id:"+post.getId() + "\n\n";
                    content +="title:"+post.getTitle() + "\n\n";
                    content +="userId:"+post.getBody() + "\n\n";
                    Jsonview.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Jsonview.setText(t.getMessage());
            }
        });


    }
}