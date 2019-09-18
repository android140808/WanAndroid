package avater.appscomm.com.mywanandroids.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.activity.ArticleDetailsActivity;
import avater.appscomm.com.mywanandroids.adapter.ProjectAdapter;
import avater.appscomm.com.mywanandroids.base.BaseMvpFragment;
import avater.appscomm.com.mywanandroids.bean.ProjectListBean;
import avater.appscomm.com.mywanandroids.mvp.presenter.ProjectSecFragmentPresenter;
import avater.appscomm.com.mywanandroids.mvp.view.IFragmentSecView;

public class ProjectSecFragment extends BaseMvpFragment<IFragmentSecView, ProjectSecFragmentPresenter> implements IFragmentSecView {


    /***
     * 分类ID
     */
    private String mId = "";

    /**
     * 当前页数
     */
    private int currPage = 0;
    /***
     * 总页数
     */
    private int mPageCount = 0;
    private List<ProjectListBean.DataBean.DatasBean> projectList = new ArrayList<>();
    private ProjectAdapter projectAdapter;
    private SmartRefreshLayout srfProjectRefresh;
    private RecyclerView rvProject;
    private String mCollectId = "";
    private int mPosition = 0;


    @Override
    protected ProjectSecFragmentPresenter createPresenter() {
        return new ProjectSecFragmentPresenter();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void getProjectListSuccess(ProjectListBean.DataBean dataBean, String type) {
        currPage = dataBean.getCurPage();
        mPageCount = dataBean.getPageCount();
        if (type.equals("下拉刷新") || type.equals("首次加载")) {
            projectList.clear();
            projectList.addAll(dataBean.getDatas());
            if (projectAdapter == null) {
                rvProject.setLayoutManager(new LinearLayoutManager(getActivity()));
                projectAdapter = new ProjectAdapter(getActivity(), R.layout.item_project_list, projectList);
                projectAdapter.setHasStableIds(true);
                projectAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                projectAdapter.isFirstOnly(false);
                rvProject.setAdapter(projectAdapter);
                projectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ProjectSecFragment.startArticleDetail(getActivity(), projectList.get(position).getTitle(), projectList.get(position).getLink() + "");
                    }
                });
                projectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        mCollectId = projectList.get(position).getId() + "";
                        mPosition = position;
                        if (view.getId() == R.id.item_iv_collect) {
                            if (projectList.get(position).isCollect()) {
                                mPresenter.cancelCollect();
                            } else {
                                mPresenter.handleCollectIn();
                            }
                        }
                    }
                });
            } else {
                projectAdapter.refresh(dataBean);
            }
            srfProjectRefresh.finishRefresh();
        } else if (type.equals("上拉加载")) {
            if (projectAdapter == null) {
                srfProjectRefresh.finishLoadMoreWithNoMoreData();
                return;
            }
            final List<ProjectListBean.DataBean.DatasBean> loadList = new ArrayList<>();
            loadList.clear();
            loadList.addAll(dataBean.getDatas());
            projectAdapter.Load(loadList);
        }
        srfProjectRefresh.finishLoadMore(/*,false*/);//不传时间则立即停止刷新    传入false表示加载失败
    }

    @Override
    public void getProjectListFail(String msg) {

    }

    @Override
    public int getPage() {
        return currPage;
    }

    @Override
    public String getClassifyId() {
        return mId;
    }

    @Override
    public void collectSuccess() {
        projectList.get(mPosition).setCollect(true);
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void collectFail() {

    }

    @Override
    public String getCollectId() {
        return mCollectId;
    }

    @Override
    public void cancelCollectSuccess() {
        projectList.get(mPosition).setCollect(false);
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void cancelCollectFail() {

    }

    @Override
    public Dialog getLoadDialog() {
        return dialog;
    }

    @Override
    public void cancelLoadDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_project_sec;
    }

    private void initInfo() {
        mId = getArguments().getString("id");
    }

    @Override
    public void initView() {
        initInfo();
        srfProjectRefresh = rootView.findViewById(R.id.srf_project_refresh);
        rvProject = rootView.findViewById(R.id.rv_project);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getProjectList("首次加载");
        handleLoadData();
    }

    private void handleLoadData() {
        srfProjectRefresh.setEnableLoadMore(true);
        srfProjectRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 0;
                mPresenter.getProjectList("下拉刷新");
            }
        });
        srfProjectRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //无数据了
                if (currPage >= mPageCount) {
                    srfProjectRefresh.finishLoadMoreWithNoMoreData();
                    return;
                }
                currPage++;
                mPresenter.getProjectList("上拉加载");

            }
        });
    }

    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static ProjectSecFragment newInstance(String id) {
        ProjectSecFragment fragment = new ProjectSecFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    public static void startArticleDetail(Context context, String title, String url) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);

    }
}
