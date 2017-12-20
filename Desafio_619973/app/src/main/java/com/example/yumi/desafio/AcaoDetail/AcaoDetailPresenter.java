package com.example.yumi.desafio.AcaoDetail;

import com.example.yumi.desafio.Entity.AcaoDetailEntity;
import com.example.yumi.desafio.Network.AcaoApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yumi on 12/20/17.
 */

public class AcaoDetailPresenter {
    public AcaoDetailPresenter(AcaoDetailView acaoDetailView) {
        this.acaoDetailView = acaoDetailView;
    }

    AcaoDetailView acaoDetailView;
    private AcaoDetailEntity acaoDetailEntity;

    public void getacaoDetails(long acaoId) {
        final AcaoApi acaoApi = AcaoApi.getInstance();
        acaoDetailView.showLoading();
        acaoApi.getAcaoDetail(acaoId).enqueue(new Callback<AcaoDetailEntity>() {
            @Override
            public void onResponse(Call<AcaoDetailEntity> call, Response<AcaoDetailEntity> response) {
                acaoDetailEntity = response.body();
                if(acaoDetailEntity != null){
                    //faz o que a gente quer
                    acaoDetailView.showDetails(acaoDetailEntity);
                }else{
                    //exibe msg de erro
                    acaoDetailView.showMessage("Falha ao carregar informações");
                }
                acaoDetailView.hideLoading();
            }

            @Override
            public void onFailure(Call<AcaoDetailEntity> call, Throwable t) {
                acaoDetailView.hideLoading();
                acaoDetailView.showMessage("Falha no acesso ao servidor");

            }
        });

    }
}
