package org.simple.animationsimple;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_app_bar);

//        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
//        View temp = findViewById(R.id.temp);
//        TextView titleView = findViewById(R.id.title);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            int height = -1;
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if(-1 == height){
//                    height = titleView.getHeight();
//                }
//
//
//                Log.i(TAG, "onOffsetChanged - verticalOffset:" + verticalOffset + ", collapsingToolbarLayout.getHeight():" + collapsingToolbarLayout.getHeight() + ", appBarLayout.getHeight():" + appBarLayout.getHeight() + ", temp.getHeight():" + temp.getHeight());
////                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleView.getLayoutParams();
////                int margin = Math.abs(verticalOffset);
////                if (margin != params.getMarginStart()) {
////                    params.setMarginStart(margin);
////                    titleView.setLayoutParams(params);
////                }
//
//                ViewGroup.LayoutParams layoutParams =   titleView.getLayoutParams();
//                layoutParams.height = height + verticalOffset;
//                titleView.setLayoutParams(layoutParams);
//
//            }
//        });

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}