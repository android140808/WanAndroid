package avater.appscomm.com.mywanandroids.base;

import android.os.Bundle;

public interface IActivity {
    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);
}
