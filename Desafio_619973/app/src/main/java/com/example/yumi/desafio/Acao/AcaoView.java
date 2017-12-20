package com.example.yumi.desafio.Acao;

import com.example.yumi.desafio.Entity.AcaoEntity;

import java.util.List;

/**
 * Created by yumi on 12/19/17.
 */

public interface AcaoView {
    void updateList(List<AcaoEntity> acaoList);

    void showLoading();

    void showMessage(String s);

    void hideLoading();

    void saveAcaoSharedPreferences(String jsonAcaoEntity);

}
