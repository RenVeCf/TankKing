package com.ipd.tankking.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipd.tankking.R;

/**
 * 作者：rmy on 2017/12/7 14:21
 * 邮箱：942685687@qq.com
 */
public class T {
    private static Toast toast;
    private static ImageView ivIcon;
    private static TextView stoast;

    public static void Short(String msg, int iconType) {
        showCustomToast(ApplicationUtil.getContext(), msg, Toast.LENGTH_SHORT, iconType);
    }

    public static void Short(int msgId, int iconType) {
        showCustomToast(ApplicationUtil.getContext(), msgId, Toast.LENGTH_SHORT, iconType);
    }

    public static void Long(String msg, int iconType) {
        showCustomToast(ApplicationUtil.getContext(), msg, Toast.LENGTH_LONG, iconType);
    }

    public static void Long(int msgId, int iconType) {
        showCustomToast(ApplicationUtil.getContext(), msgId, Toast.LENGTH_LONG, iconType);
    }

    public static void InUiThread(final Activity activity, final String msg, final int iconType) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    showCustomToast(activity, msg, iconType);
                }
            });
        }
    }

    public static void InUiThread(final Activity activity, final int stringId, final int iconType) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    showCustomToast(activity, stringId, iconType);
                }
            });
        }
    }

    private static void showCustomToast(Context context, int msgId, int iconType) {
        final String msg = context.getResources().getString(msgId);
        showCustomToast(context, msg, iconType);
    }

    private static void showCustomToast(Context context, String msg, int iconType) {
        showCustomToast(context, msg, Toast.LENGTH_SHORT, iconType);
    }

    private static void showCustomToast(Context context, int msgId, int duration, int iconType) {
        final String msg = context.getResources().getString(msgId);
        showCustomToast(context, msg, duration, iconType);
    }

    private static void showCustomToast(final Context context, final String msg, final int duration, final int iconType) {
        if (context == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showToast(context, msg, duration, iconType);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, msg, duration, iconType);
                }
            });
        }
    }

    private static void showToast(Context context, String msg, int duration, int iconType) {
        if (null != context) {
            if (toast == null) {
                toast = new Toast(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View layout = inflater.inflate(R.layout.toast_layout, null);
                ivIcon = (ImageView) layout.findViewById(R.id.iv_ic);
                stoast = (TextView) layout.findViewById(R.id.tv_message);
                switch (iconType) {
                    case 0:
                        ivIcon.setImageResource(R.mipmap.ic_point);
                        break;
                    case 1:
                        ivIcon.setImageResource(R.mipmap.ic_success);
                        break;
                    case 2:
                        ivIcon.setImageResource(R.mipmap.ic_fail);
                        break;
                }
                stoast.setText(msg);
                toast.setDuration(duration);
                toast.setView(layout);
            } else {
                switch (iconType) {
                    case 0:
                        ivIcon.setImageResource(R.mipmap.ic_point);
                        break;
                    case 1:
                        ivIcon.setImageResource(R.mipmap.ic_success);
                        break;
                    case 2:
                        ivIcon.setImageResource(R.mipmap.ic_fail);
                        break;
                }
                stoast.setText(msg);
            }
            toast.show();
        }
    }

}
