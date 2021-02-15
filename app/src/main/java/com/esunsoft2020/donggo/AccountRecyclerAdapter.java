package com.esunsoft2020.donggo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountRecyclerAdapter.VH> {

    Context context;
    ArrayList<TwoStringItem> items;

    public AccountRecyclerAdapter(Context context, ArrayList<TwoStringItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new VH(inflater.inflate(R.layout.account_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TwoStringItem item = items.get(position);
        holder.tvInfo.setText(item.first);
        holder.tvIndividual.setText(item.second);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tvInfo;
        TextView tvIndividual;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvIndividual = itemView.findViewById(R.id.tv_individual);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                    Intent intent;
                    switch (position){
                        case 0:
                            intent = new Intent(context,AccountChangeNameActivity.class);
                            context.startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(context,AccountChangeEmailActivity.class);
                            context.startActivity(intent);
                            break;


                    }
                }
            });
        }
    }
}
