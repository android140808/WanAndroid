package avater.appscomm.com.mywanandroids.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import avater.appscomm.com.mywanandroids.R;
import avater.appscomm.com.mywanandroids.activity.ArticleDetailsActivity;
import avater.appscomm.com.mywanandroids.adapter.HomeAdapter;
import avater.appscomm.com.mywanandroids.base.BaseMvpFragment;
import avater.appscomm.com.mywanandroids.bean.BannerBean;
import avater.appscomm.com.mywanandroids.bean.MainListBean;
import avater.appscomm.com.mywanandroids.imageloader.GlideUtils;
import avater.appscomm.com.mywanandroids.mvp.presenter.HomeFragmentPresenter;
import avater.appscomm.com.mywanandroids.mvp.view.IFragmentHomeView;
import avater.appscomm.com.mywanandroids.utils.CommonUtil;
import avater.appscomm.com.mywanandroids.utils.DensityUtil;
import avater.appscomm.com.mywanandroids.utils.LogUtils;

public class HomeFragment extends BaseMvpFragment<IFragmentHomeView, HomeFragmentPresenter> implements IFragmentHomeView {

    private SmartRefreshLayout srfMainRefresh;
    private FrameLayout llHeadview;
    private HomeAdapter homeAdapter;
    private RecyclerView rvMain;
    private ImageView ivBannerBg;
    /**
     * 头布局
     */
    private View header = null;
    private Banner banner = null;
    private List<MainListBean.DataBean.DatasBean> homeList = new ArrayList<>();
    private List<BannerBean.DataBean> bannerlist = new ArrayList<>();
    private List<String> imagelist = new ArrayList<>();
    private LinearLayout llError;
    private AppCompatTextView tvLoad;
    /**
     * 当前页数
     */
    private int currPage = 0;
    /***
     * 总页数
     */
    private int mPageCount = 0;
    private LinearLayout llSearch;
    private int mBannerHeight;
    private LinearLayout llSearch1;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                CommonUtil.showToast("暂未开通");
                break;
            case R.id.ll_search1:

                break;
            case R.id.tv_load:
                mPresenter.getBanner();
                break;
        }
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
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        llSearch = rootView.findViewById(R.id.ll_search);
        srfMainRefresh = rootView.findViewById(R.id.srf_main_refresh);
        rvMain = rootView.findViewById(R.id.rv_main);
        llSearch1 = rootView.findViewById(R.id.ll_search1);
        llError = rootView.findViewById(R.id.ll_error);
        tvLoad = rootView.findViewById(R.id.tv_load);
        llSearch1.setOnClickListener(this);
        tvLoad.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ImmersionBar.with(this).titleBar(R.id.ll_search).init();
        mPresenter.getBanner();
        mBannerHeight = DensityUtil.dip2px(getActivity(), 180);
        srfMainRefresh.setEnableAutoLoadMore(true);
        srfMainRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 0;
                mPresenter.getBanner();
            }
        });
        srfMainRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //无数据了
                if (currPage >= mPageCount) {
                    srfMainRefresh.finishLoadMoreWithNoMoreData();
                    return;
                }
                mPresenter.getHomeList("上拉加载");
            }
        });
        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy < 0) {
                    totalDy = 0;
                }
                if (totalDy < mBannerHeight) {
                    float alpha = (float) totalDy / mBannerHeight;
                    llSearch.setAlpha(alpha);
                } else {
                    llSearch.setAlpha(1);
                }

            }
        });
    }

    @Override
    public void getMainListSuccess(MainListBean.DataBean dataBean, String type) {
        currPage = dataBean.getCurPage();
        mPageCount = dataBean.getPageCount();
        llSearch.setVisibility(View.VISIBLE);
        if (type.equals("下拉刷新") || type.equals("首次加载")) {
            homeList.clear();
            homeList.addAll(dataBean.getDatas());
            if (homeAdapter == null) {
                rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
                homeAdapter = new HomeAdapter(getActivity(), R.layout.item_home_list, homeList);
                homeAdapter.setHasStableIds(true);
                homeAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                homeAdapter.isFirstOnly(false);
                rvMain.setAdapter(homeAdapter);
                addHeadView();
                homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        HomeFragment.startArticleDetail(getActivity(), homeList.get(position).getTitle(), homeList.get(position).getLink() + "");
                    }
                });
            } else {
                homeAdapter.refresh(dataBean);
            }
            srfMainRefresh.finishRefresh();
        } else if (type.equals("上拉加载")) {
            if (homeAdapter == null) {
                srfMainRefresh.finishLoadMoreWithNoMoreData();
                return;
            }
            final List<MainListBean.DataBean.DatasBean> loadList = new ArrayList<>();
            loadList.clear();
            loadList.addAll(dataBean.getDatas());
            homeAdapter.Load(loadList);
        }
        srfMainRefresh.finishLoadMore(/*,false*/);//不传时间则立即停止刷新    传入false表示加载失败

    }

    @Override
    public void getMainListFail() {
        srfMainRefresh.finishRefresh();
        srfMainRefresh.finishLoadMore();
        llSearch.setVisibility(View.GONE);
    }

    @Override
    public int getPage() {
        return currPage;
    }

    @Override
    public void getBannerSuccess(List<BannerBean.DataBean> dataBean) {
        Gson gson = new Gson();
        imagelist.clear();
        bannerlist.clear();
        bannerlist.addAll(dataBean);
        for (int i = 0; i < bannerlist.size(); i++) {
            imagelist.add(bannerlist.get(i).getImagePath());
        }
        LogUtils.d("banner---  " + gson.toJson(imagelist));
        //huo获取首页列表
        mPresenter.getHomeList("首次加载");
        llError.setVisibility(View.GONE);
    }

    @Override
    public void getBannerFail() {
        imagelist.clear();
        bannerlist.clear();
        //huo获取首页列表
        mPresenter.getHomeList("首次加载");
        llError.setVisibility(View.VISIBLE);
    }

    private void addHeadView() {
        if (header == null) {
            //添加Header
            header = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_banner, rvMain, false);
            banner = header.findViewById(R.id.banner);
            ivBannerBg = header.findViewById(R.id.iv_banner_bg);
            llHeadview = header.findViewById(R.id.llHeadview);
            GlideUtils.loadBlurry(ivBannerBg, bannerlist.get(0).getImagePath());
        }
        homeAdapter.addHeaderView(llHeadview);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imagelist);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                HomeFragment.startArticleDetail(getActivity(), bannerlist.get(position).getTitle(), bannerlist.get(position).getUrl() + "");
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                LogUtils.d("切换----   " + i);
                if (!getActivity().isFinishing()) {
                    if (bannerlist != null && bannerlist.size() > 0) {
                        GlideUtils.loadBlurry(ivBannerBg, bannerlist.get(i).getImagePath());
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    public static void startArticleDetail(Context context, String title, String url) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtils.loadImage(context, (String) path, imageView);
        }
    }
}
