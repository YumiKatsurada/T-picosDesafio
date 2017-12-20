package com.example.yumi.desafio.Acao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yumi.desafio.Entity.AcaoEntity;
import com.example.yumi.desafio.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yumi on 12/19/17.
 */

public class AcaoAdapter extends RecyclerView.Adapter<AcaoAdapter.ViewHolder> {

        private List<AcaoEntity> acoesList;
        OnRecyclerViewSelected onRecyclerViewSelected;
        private Context context;

        //Construtor que recebe a lista
    public AcaoAdapter(List<AcaoEntity> acoesList, Context context) {
        this.acoesList = acoesList;
        this.context = context;
    }

    //infla o componente view
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_acao, parent, false);
            return new ViewHolder(v);
        }

        //seta os dados nas views
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            AcaoEntity acaoEntity = acoesList.get(position);
            holder.txName.setText(acaoEntity.getName());
            Picasso.with(context)
                .load(acaoEntity.getPhoto())
                .centerCrop()
                .fit()
                .into(holder.imgBackgroud);
        }

        //retorna o tamanho da lista
        @Override
        public int getItemCount() {
            return acoesList.size();
        }

        //mapeamento dos componentes da view
        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.text_view_name)
            TextView txName;

            @BindView(R.id.image_view_background)
            ImageView imgBackgroud;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            //seta o clique rápido
            @OnClick(R.id.container) void onItemClick(View view){
                if(onRecyclerViewSelected != null)
                    onRecyclerViewSelected.onClick(view, getAdapterPosition());
            }
        }



    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }

}


