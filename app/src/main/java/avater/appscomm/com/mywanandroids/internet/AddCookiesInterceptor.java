package avater.appscomm.com.mywanandroids.internet;


import java.io.IOException;

import avater.appscomm.com.mywanandroids.utils.LogUtils;
import avater.appscomm.com.mywanandroids.utils.SpUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

        //添加Cookie
//        if(!TextUtils.isEmpty(NetClient.COOKIE)){
        builder.addHeader("Cookie", SpUtils.GetConfigString("cookie"));
        LogUtils.d("我发送了cookie---------------   " + SpUtils.GetConfigString("cookie"));
        //   }
        return chain.proceed(builder.build());
    }

}
