package com.example.android.stackwidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocaleChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("StackWidgetTest", "LocaleChangedReceiver - onReceive");
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
            int[] appWidgetIds = mgr.getAppWidgetIds(new ComponentName(context, StackWidgetProvider.class));
            // Not effective
            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
        }
    }
}