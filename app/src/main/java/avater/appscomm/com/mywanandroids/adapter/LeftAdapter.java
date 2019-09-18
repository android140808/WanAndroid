package avater.appscomm.com.mywanandroids.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.bean.NavigateBean;


public class LeftAdapter extends SimpleRecyclerAdapter<NavigateBean.DataBean> {

    private int mSelectedPosition;

    @Override
    public SimpleViewHolder<NavigateBean.DataBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_search_sort_left, parent, false), this);
    }

    public void setSelectedPosition(int position) {
        mListData.get(mSelectedPosition).isSelected = false;
        notifyItemChanged(mSelectedPosition);
        mListData.get(position).isSelected = true;
        notifyItemChanged(position);
        mSelectedPosition = position;
    }
}
