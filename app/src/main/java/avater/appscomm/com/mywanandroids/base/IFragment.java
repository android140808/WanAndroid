package avater.appscomm.com.mywanandroids.base;

import android.os.Bundle;

public interface IFragment {
    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);
}
