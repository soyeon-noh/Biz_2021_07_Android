package com.noso.chatt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noso.chatt.R;
import com.noso.chatt.model.Chatt;

import java.util.List;

/*
 * RecyclerView.Adapter 구현한 클래스
 * RecyclerView에 데이터를 표현하고자 할 때 사용하는 Helper 클래스
 *      (Helper 클래스: 도와주는 도구 클래스)
 */
public class ChattAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>{ //책

    private List<Chatt> chattList;

    /*
     * RecyclerView가 화면에 그릴 데이터들을 전달받을 통로
     * @param chattList
     */
    public ChattAdapter(List<Chatt> chattList) {
        this.chattList = chattList;
    }

    /*
     * chat_item.xml파일을 읽어서 한개의 아이템을 화면에 그릴 준비
     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
         * LayoutInflater.from().inflate(layout 파일)
         *
         * chatt.item.xml 파일은 한개의 파일이 화면 전체를 구성하지 않고
         * 여기에서는 RecyclerView 내부에 각각의 데이터 아이템을 그릴 도구로 사용된다.
         * 이러한 layout은 setContentView()를 사용하지 않고
         * layoutInflater.inflate() 함수를 사용하여 만든다다         */
        View item_layout
            = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.chatt_item,parent, false);

        ChattViewHolder viewHolder
                = new ChattViewHolder(item_layout);

        return viewHolder; // 데이터를 보여주기위한 기본구성을 만들어주는 것
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    /*
     * RecyclerView가 데이터들을 화면에 표시할때 참조하는 함수
     * @Return
     */
    @Override
    public int getItemCount() {
        return chattList == null ? 0 : chattList.size();
    }

    // class 내부에 in class 선언
    public static class ChattViewHolder extends RecyclerView.ViewHolder{ // 책 속 아이템

        public TextView item_name;
        public TextView item_msg;

        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_msg = itemView.findViewById(R.id.msg_Linear);

        }
    }
}
