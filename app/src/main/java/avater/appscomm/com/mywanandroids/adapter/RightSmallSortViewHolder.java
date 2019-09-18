package avater.appscomm.com.mywanandroids.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;


public class RightSmallSortViewHolder extends SimpleViewHolder<NavigateBean.DataBean.ArticlesBean> {

    private TextView textView;

    public RightSmallSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<NavigateBean.DataBean.ArticlesBean> adapter) {
        super(itemView, adapter);
        textView = itemView.findViewById(R.id.tv_small);
    }

    @Override
    protected void refreshView(NavigateBean.DataBean.ArticlesBean data) {
        textView.setText(data.getTitle());
    }
}
