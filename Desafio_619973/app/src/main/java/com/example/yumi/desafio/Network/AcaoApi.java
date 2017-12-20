package com.example.yumi.desafio.Network;

import com.example.yumi.desafio.Entity.AcaoDetailEntity;
import com.example.yumi.desafio.Entity.AcaoListEntity;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yumi on 12/19/17.
 */

public class AcaoApi {
    private static AcaoApi instance;

    private AcaoService acaoService;
    private String sessionToken;

    public static AcaoApi getInstance() {
        if (instance == null) {
            instance = new AcaoApi();
        }

        return instance;
    }

    private AcaoApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/s/50vmlj7dhfaibpj/sociais.json")
                .addConverterFactory(defaultConverterFactory())
                .build();


        this.acaoService = retrofit.create(AcaoService.class);
    }

    private Converter.Factory defaultConverterFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }


    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public Call<AcaoListEntity> getAcao() {
        return acaoService.getAcoes(getSessionToken());
    }

    public Call<AcaoDetailEntity> getAcaoDetail(long id){
        return acaoService.getAcaoDetail(sessionToken, id);
    }
}


