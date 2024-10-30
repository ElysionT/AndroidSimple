package com.example.android.stackwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    private static final String TAG = "JLAPIS-15783";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AppCompatActivity
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // AppCompatActivity
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        findViewById(R.id.refresh).setOnClickListener(view -> {
            AppWidgetManager mgr = AppWidgetManager.getInstance(getApplicationContext());
            int[] appWidgetIds = mgr.getAppWidgetIds(new ComponentName(getApplicationContext(), StackWidgetProvider.class));
            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
        });

        // List default selector style
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.list_widget_item, // Built-in layout for list items
                items // The items to display
        ));
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        Log.i(TAG, "onActionModeStarted - mode:" + mode.toString());
    }

    @Override
    public boolean onMenuOpened(int featureId, @NonNull Menu menu) {
        Log.i(TAG, "onMenuOpened - featureId:" + featureId + ", menu:" + menu.toString());
        return super.onMenuOpened(featureId, menu);
    }
}