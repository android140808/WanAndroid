package avater.appscomm.com.mywanandroids.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;


public class RightBigSortViewHolder extends SimpleViewHolder<NavigateBean.DataBean.ArticlesBean> {

    TextView tvTitle;

    public RightBigSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<NavigateBean.DataBean.ArticlesBean> adapter) {
        super(itemView, adapter);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }

    @Override
    protected void refreshView(NavigateBean.DataBean.ArticlesBean data) {
        tvTitle.setText(data.header);
    }

}
