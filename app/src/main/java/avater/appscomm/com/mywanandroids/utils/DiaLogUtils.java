package avater.appscomm.com.mywanandroids.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import avater.appscomm.com.mywanandroids.R;

public class DiaLogUtils {
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);
        TextView textView = v.findViewById(R.id.tipTextView);
        if (msg != null && !msg.equals("")) {
            textView.setText(msg);
        }
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(v);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = (int) (dm.widthPixels * 0.3);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        loadingDialog.getWindow().setLayout(width, height);
        return loadingDialog;
    }

}
