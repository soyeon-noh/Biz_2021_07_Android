package com.callor.cacao.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.cacao.Model.Chatt;
import com.callor.cacao.R;

import java.util.List;

public class ChattAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Chatt> chattList;

    public void addChattlist(Chatt chatt){

        chattList.add(chatt);
        notifyItemInserted(chattList.size()-1);
    }

    public ChattAdapter(List<Chatt> chattList) {
        this.chattList = chattList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item_layout
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatt_item,parent,false);

        ChattViewHolder viewHolder
                = new ChattViewHolder(item_layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Chatt chat = chattList.get(position);
        ChattViewHolder chattViewHolder = (ChattViewHolder) holder;

        chattViewHolder.item_name.setText(chat.getName());
        chattViewHolder.item_msg.setText(chat.getMsg());

    }

    @Override
    public int getItemCount() {
        return chattList == null ? 0 : chattList.size();
    }


    public static class ChattViewHolder extends RecyclerView.ViewHolder{

        public TextView item_name;
        public TextView item_msg;

        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.item_msg = itemView.findViewById(R.id.item_msg);

        }
    }
}
