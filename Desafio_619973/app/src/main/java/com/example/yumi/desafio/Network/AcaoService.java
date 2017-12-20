package com.example.yumi.desafio.Network;

import com.example.yumi.desafio.Entity.AcaoDetailEntity;
import com.example.yumi.desafio.Entity.AcaoListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


/**
 * Created by yumi on 12/19/17.
 */

public interface AcaoService {
    @GET("acao/list")
    Call<AcaoListEntity> getAcoes(@Header("Authorization") String sessionToken);

    @GET("acao")
    Call<AcaoDetailEntity> getAcaoDetail
            (@Header("Authorization") String sessionToken,
             @Query("id") long acaoId);
}
