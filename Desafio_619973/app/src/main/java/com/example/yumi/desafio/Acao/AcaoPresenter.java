package com.example.yumi.desafio.Acao;

import com.example.yumi.desafio.Entity.AcaoEntity;
import com.example.yumi.desafio.Entity.AcaoListEntity;
import com.example.yumi.desafio.Network.AcaoApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AcaoPresenter {

    private AcaoView acoesView;
    private List<AcaoEntity> acoesList = new ArrayList<>();
    AcaoListEntity acaoListEntity;

    AcaoPresenter(AcaoView acoesView){
        this.acoesView = acoesView;
    }

    void updateList(){
        final AcaoApi acaoApi = AcaoApi.getInstance();
        acoesView.showLoading();
        acaoApi.getAcao().enqueue(new Callback<AcaoListEntity>() {
            @Override
            public void onResponse(Call<AcaoListEntity> call, Response<AcaoListEntity> response) {
                acaoListEntity = response.body();
                if(acaoListEntity != null){
                    acoesView.updateList(acaoListEntity.getAcoes());
                } else{
                    acoesView.showMessage("Falha no login");
                }
                acoesView.hideLoading();
            }

            @Override
            public void onFailure(Call<AcaoListEntity> call, Throwable t) {
                acoesView.hideLoading();
                acoesView.showMessage("Falha no acesso ao servidor");
            }
        });
    }

    public void saveAcoes() {
        String jsonAcoesEntity = new Gson().toJson(acaoListEntity);
        acoesView.saveAcaoSharedPreferences(jsonAcoesEntity);
    }

    public void updateList(String jsonAcoes) {
        if(jsonAcoes != null) {
            //exibe dados já baixados
            acaoListEntity = new Gson().fromJson(jsonAcoes, AcaoListEntity.class);
            acoesList = acaoListEntity.getAcoes();
            acoesView.updateList(acoesList);

        } else{
            final AcaoApi acaoApi = AcaoApi.getInstance();
            acoesView.showLoading();
            acaoApi.getAcao().enqueue(new Callback<AcaoListEntity>() {
                @Override
                public void onResponse(Call<AcaoListEntity> call, Response<AcaoListEntity> response) {
                    acaoListEntity = response.body();
                    if(acaoListEntity != null){
                        acoesView.updateList(acaoListEntity.getAcoes());
                    } else{
                        acoesView.showMessage("Falha no login");
                    }
                    acoesView.hideLoading();
                }

                @Override
                public void onFailure(Call<AcaoListEntity> call, Throwable t) {
                    acoesView.hideLoading();
                    acoesView.showMessage("Falha no acesso ao servidor");
                }
            });
        }
    }

}
