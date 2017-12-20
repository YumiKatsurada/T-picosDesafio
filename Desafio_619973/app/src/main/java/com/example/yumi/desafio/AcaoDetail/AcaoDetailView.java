package com.example.yumi.desafio.AcaoDetail;

import com.example.yumi.desafio.Entity.AcaoDetailEntity;

/**
 * Created by yumi on 12/20/17.
 */

public interface AcaoDetailView {
    void showLoading();

    void showDetails(AcaoDetailEntity acaoDetailEntity);

    void showMessage(String s);

    void hideLoading();
}
