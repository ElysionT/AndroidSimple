/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.stackwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class StackWidgetProvider extends AppWidgetProvider {
    public static final String TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("StackWidgetTest", "StackWidgetProvider - onReceive action:" + intent.getAction());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Touched view " + viewIndex, Toast.LENGTH_SHORT).show();
            return;
        } else if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
            Log.i("StackWidgetTest", "StackWidgetProvider - onReceive, myLooper:" + Looper.myLooper());
            Log.i("StackWidgetTest", "StackWidgetProvider - onReceive, getMainLooper:" + Looper.getMainLooper());

            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, StackWidgetProvider.class));

            for (int i = 0; i < appWidgetIds.length; ++i) {
                relayoutWidget2(context, appWidgetManager, appWidgetIds[i], null);
            }

            // Not effective
//            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);

//            Handler mainHandler = new Handler(Looper.getMainLooper());
//            mainHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < appWidgetIds.length; ++i) {
//                        relayoutWidget(context, appWidgetManager, appWidgetIds[i], null);
//                    }
////                    Log.i("StackWidgetTest", "StackWidgetProvider - onReceive, notifyAppWidgetViewDataChanged");
////                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
//                }
//            }, 5000);

            return;
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i("StackWidgetTest", "StackWidgetProvider - onUpdate");
        // update each of the widgets with the remote adapter
        for (int i = 0; i < appWidgetIds.length; ++i) {
            relayoutWidget2(context, appWidgetManager, appWidgetIds[i], null);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void relayoutWidget(Context context, AppWidgetManager appWidgetManager, int widgetId, Bundle options) {
        Log.i("StackWidgetTest", "StackWidgetProvider - relayoutWidget widgetId:" + widgetId);
        // Here we setup the intent which points to the StackViewService which will
        // provide the views for this collection.
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        // When intents are compared, the extras are ignored, so we need to embed the extras
        // into the data so that the extras will not be ignored.
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        rv.setRemoteAdapter(widgetId, R.id.stack_view, intent);

        // The empty view is displayed when the collection has no items. It should be a sibling
        // of the collection view.
        rv.setEmptyView(R.id.stack_view, R.id.empty_view);

        // Here we setup the a pending intent template. Individuals items of a collection
        // cannot setup their own pending intents, instead, the collection as a whole can
        // setup a pending intent template, and the individual items can set a fillInIntent
        // to create unique before on an item to item basis.
        Intent toastIntent = new Intent(context, StackWidgetProvider.class);
        toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
//        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

        appWidgetManager.updateAppWidget(widgetId, rv);
//        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.stack_view);
    }

    private void relayoutWidget2(Context context, AppWidgetManager appWidgetManager, int widgetId, Bundle options) {
        Log.i("StackWidgetTest", "StackWidgetProvider - relayoutWidget2 widgetId:" + widgetId);
        RemoteViews.RemoteCollectionItems.Builder builder = new RemoteViews.RemoteCollectionItems.Builder().setHasStableIds(true);
        for (int i = 0; i < 10; i++) {
            Log.i("StackWidgetTest", "relayoutWidget2 i:" + i);
            RemoteViews item = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            String title = context.getString(R.string.item_title_text);
            item.setTextViewText(R.id.widget_item, title + " " + i); // mWidgetItems.get(position).text);

            // Next, we set a fill-intent which will be used to fill-in the pending intent template
            // which is set on the collection view in StackWidgetProvider.
            Bundle extras = new Bundle();
            extras.putInt(StackWidgetProvider.EXTRA_ITEM, i);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            item.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

            // You can do heaving lifting in here, synchronously. For example, if you need to
            // process an image, fetch something from the network, etc., it is ok to do it here,
            // synchronously. A loading view will show up in lieu of the actual contents in the
            // interim.
            try {
                System.out.println("Loading view " + i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            builder.addItem(i, item);
        }

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        rv.setRemoteAdapter(R.id.stack_view, builder.build());

        // The empty view is displayed when the collection has no items. It should be a sibling
        // of the collection view.
        rv.setEmptyView(R.id.stack_view, R.id.empty_view);

        // Here we setup the a pending intent template. Individuals items of a collection
        // cannot setup their own pending intents, instead, the collection as a whole can
        // setup a pending intent template, and the individual items can set a fillInIntent
        // to create unique before on an item to item basis.
        Intent toastIntent = new Intent(context, StackWidgetProvider.class);
        toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

        appWidgetManager.updateAppWidget(widgetId, rv);
    }
}