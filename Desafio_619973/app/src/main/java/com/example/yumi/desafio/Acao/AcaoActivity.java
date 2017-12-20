package com.example.yumi.desafio.Acao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yumi.desafio.AcaoDetail.AcaoDetailActivity;
import com.example.yumi.desafio.Entity.AcaoEntity;
import com.example.yumi.desafio.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcaoActivity extends AppCompatActivity implements AcaoView{
    @BindView(R.id.rv_acao)
    RecyclerView rvAcao;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    AcaoPresenter acoesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao);

        ButterKnife.bind(this);
        acoesPresenter = new AcaoPresenter(this);
        acoesPresenter.updateList();

        String jsonAcao = getIntent().getStringExtra("jsonAcao");
        acoesPresenter.updateList(jsonAcao);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_download:
                //salvar as informações dos filmes nas SharedPreferences
                acoesPresenter.saveAcoes();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateList(final List<AcaoEntity> acoesList) {

        //seta o adapter
        AcaoAdapter acaoAdapter = new AcaoAdapter(acoesList, this);
        acaoAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (AcaoActivity.this,
                                AcaoDetailActivity.class);

                intent.putExtra("acao_id", acoesList.get(position).getId());
                startActivity(intent);

            }

        });

        rvAcao.setAdapter(acaoAdapter);


        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAcao.setLayoutManager(layoutManager);
        rvAcao.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void saveAcaoSharedPreferences(String jsonAcaoEntity) {
        SharedPreferences.Editor editor =
                getSharedPreferences("acao_json", MODE_PRIVATE).edit();

        editor.putString("acao_entity_json", jsonAcaoEntity);

        editor.apply();

        showMessage("Informações salvas com sucesso");

    }
}
