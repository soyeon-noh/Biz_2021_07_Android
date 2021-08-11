package com.noso.topnews.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noso.topnews.databinding.NewsItemViewBinding;
import com.noso.topnews.model.NaverNewsDTO;

import java.util.List;

/**
 * RecyclerView helper class
 * RecyclerView.Adapter  클래스를 상속받고
 * RecyclerView.Viewholder 클래스를 상속받은
 * inner class를 포함한다
 */
public class NewsViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 데이터를 외부로부터 주입당해서 생성하기 위한 adapter
    private List<NaverNewsDTO> newsList;
    public NewsViewAdapter(List<NaverNewsDTO> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemViewBinding viewBinding
                = NewsItemViewBinding.inflate(
                        layoutInflater,parent,false
        ); // 뷰를 만들어서
        return new NewsViewHolder(viewBinding); // 뷰홀더에 넣어 return 하는 코드
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        /**
         * 매개변수로 받은 holder는
         * RecyclerView.ViewHolder type으로 실제 사용해야하는
         * NewsViewHolder의 부모 클래스 type이다.
         * 때문에 실제 사용하기 위해서는 NewsViewHolder type으로
         * 형변환(Cast)해야 한다.
         */
        NewsViewHolder viewHolder = (NewsViewHolder) holder;

        NewsItemViewBinding newsBinding = viewHolder.viewBinding;

        NaverNewsDTO newsDTO = newsList.get(position);

//        viewHolder.viewBinding.newsItemTitle.setText(newsDTO.getTitle()); // 아래랑 같은것..?이었는데 아래가 수정되었음

        Spanned newsTitle = Html.fromHtml(newsDTO.getTitle(), Html.FROM_HTML_MODE_LEGACY);
        newsBinding.newsItemTitle.setText(newsTitle);

        Spanned newsDesc
                = Html.fromHtml(newsDTO.getDescription(), Html.FROM_HTML_MODE_LEGACY);
        newsBinding.newsItemDesc.setText(newsDesc);
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        /**
         * binding 속성을 사용하면
         * item_view.xml에 설정된 view component를
         * 일일이 찾아서 초기화하는 코드가 필요 없어진다.
         *
         * item_view.xml 파일에 의해 생성된 ItemViewBinding 클래스를 사용하여
         * 모든 view component를 한꺼번에 viewHolder로 생성할 수 있다.
         */
        public NewsItemViewBinding viewBinding;
        public NewsViewHolder(@NonNull NewsItemViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding; // 동시에 바인딩 되어 동시에 폴더가 만들어짐

        }
    }
}
