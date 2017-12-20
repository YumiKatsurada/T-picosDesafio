package com.example.yumi.desafio.AcaoDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yumi.desafio.Entity.AcaoDetailEntity;
import com.example.yumi.desafio.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcaoDetailActivity extends AppCompatActivity implements AcaoDetailView{

    @BindView(R.id.image_view_header)
    ImageView imgHeader;

    @BindView(R.id.text_view_description)
    TextView tvDescription;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    AcaoDetailPresenter acaoDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_d);
        ButterKnife.bind(this);

        //insere opção Up Action na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        long acaoId = intent.getLongExtra("acao_id", -1);


        acaoDetailPresenter = new AcaoDetailPresenter(this);

        acaoDetailPresenter.getacaoDetails(acaoId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_download:
                Toast.makeText(this, "clicou", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void showDetails(AcaoDetailEntity acaoDetailEntity) {
        tvDescription.setText(acaoDetailEntity.getDescription());
        Picasso.with(this)
                .load(acaoDetailEntity.getPhoto())
                .centerCrop()
                .fit()
                .into(imgHeader);
        setTitle(acaoDetailEntity.getName());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }
}
