package com.example.inventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.engine.Resource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

        private int page ;

            public static final String TAG =  "CHARACTERS";
    String urlJson = "https://rickandmortyapi.com/api/character/";
    ArrayList<Character> characters= new ArrayList<>();

   private Retrofit retrofit ;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private boolean aptoParaCargar;
    CharacterAdapter characterAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
       linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    int visibleItemCount =linearLayoutManager.getChildCount();
                    int totalItemCount =linearLayoutManager.getItemCount();
                    int pastVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if(aptoParaCargar){
                        if((visibleItemCount+pastVisibleItem)>=totalItemCount){
                            Log.i(TAG,"LLEGAMOS AL FINAL");
                            aptoParaCargar = false;
                            page +=1;
                            obtenerDatos(page);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        page = 1;

                obtenerDatos(page);

        FloatingActionButton add = findViewById(R.id.addbutton);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActvity.class);
                startActivity(intent);
            }
        });


    }

    private void obtenerDatos(int page) {
        CharacterapiService service = retrofit.create(CharacterapiService.class);
        Call<CharacterResponse> characterResponseCall = service.obetenerDatosCharacter(page);

        characterResponseCall.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, retrofit2.Response<CharacterResponse> response) {
               aptoParaCargar=true;
                if(response.isSuccessful()){
                    CharacterResponse characterResponse = response.body();
                  characters = characterResponse.getResults();


                  CharacterAdapter characterAdapter  = new CharacterAdapter(characters,MainActivity.this);

                  recyclerView.setAdapter(characterAdapter);
                  recyclerView.setLayoutManager(linearLayoutManager);



                  for(int i = 0;i<characters.size();i++){
                      Character c = characters.get(i);
                      Log.i(TAG,"Character : " + c.getName());

                  }
                }else{
                    Log.e(TAG,"onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG,"onFailure: "+ t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        //initContent();

    }
    @Override
    protected void onPause() {
        super.onPause();
        //this.characters.clear();
    }
/*
      private  void initContent(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlJson, new
                Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            parseContent(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    private void parseContent(JSONArray jsonArray) throws JSONException {
        for(int i=0; i < jsonArray.length(); i++) {
            JSONObject tmp = jsonArray.getJSONObject(i);
            Gson gson = new Gson();

            Character t = gson.fromJson(tmp.toString(),Character.class);
            characters.add(t);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new CharacterAdapter(characters,this ));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    */

}