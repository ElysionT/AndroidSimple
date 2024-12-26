package org.simple.animationsimple.ui.dashboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.simple.animationsimple.R;
import org.simple.animationsimple.databinding.FragmentDashboardBinding;
import org.simple.animationsimple.view.BorderLayout;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private View mCircleView;
    private TextView mTextView;
    private View mFloatView;
    private BorderLayout mBorderLayout;
    private BorderLayout mBorderLayout2;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mTextView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), mTextView::setText);

        mCircleView = binding.circleView;
        mCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开始动画
                startExpansionAnimation3(mCircleView);

            }
        });

        mBorderLayout = binding.borderLayoutInner;
        mBorderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开始动画
                startExpansionAnimation5(mBorderLayout);

            }
        });

        mFloatView = inflater.inflate(R.layout.layout_float_view2, null);
        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        mFloatView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.section_linear_enter);
////                animation.setInterpolator(new SectionLinearInterpolator());
//                mFloatView.findViewById(R.id.float_view).startAnimation(animation);
//            }
//        });


//        int[] location = new int[2];
//        mTextView.getLocationOnScreen(location);
//        mLayoutParams.x = location[0] + mTextView.getWidth() / 2;
//        mLayoutParams.y = location[1] - mTextView.getHeight() / 2;
//        mLayoutParams.width = mTextView.getWidth();
//        mLayoutParams.height = mTextView.getHeight();
//        mWindowManager.addView(mFloatView, mLayoutParams);


        mBorderLayout2 = binding.borderLayoutInner2;
        mBorderLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.section_linear_enter);
//                animation.setInterpolator(new SectionLinearInterpolator());
                mBorderLayout2.startAnimation(animation);

            }
        });
//        mBorderLayout2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i()
//                return false;
//            }
//        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        int[] location = new int[2];
        mTextView.getLocationOnScreen(location);
        mLayoutParams.x = location[0] + mTextView.getWidth() / 2;
        mLayoutParams.y = location[1] - mTextView.getHeight() / 2;
        mLayoutParams.width = mTextView.getWidth();
        mLayoutParams.height = mTextView.getHeight();
        mWindowManager.addView(mFloatView, mLayoutParams);

//        startExpansionAnimation7();

//        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.section_linear_enter);
////                animation.setInterpolator(new SectionLinearInterpolator());
//        mFloatView.findViewById(R.id.float_view).startAnimation(animation);


////        mFloatView.setPivotX();
//
////        mFloatView.setAlpha(0.0f);
//        mFloatView.setScaleX(0.0f);
//        mFloatView.setScaleY(0.0f);
//        mFloatView.setPivotX(300.0f);
////        mFloatView.setPivotY(0.0f);
//        mFloatView.animate().alpha(1.0f)
////                .setInterpolator(new AccelerateDecelerateInterpolator())
//                .scaleX(1f)
//                .scaleY(1f)
//                .setDuration(5000)
//                .start();
//
//       Log.i("DashboardFragment", "onResume - mFloatView.getPivotX():"+ mFloatView.getPivotX() + " mFloatView.getPivotY():"+ mFloatView.getPivotY());
//
//        startExpansionAnimation6();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFloatView.isAttachedToWindow()) {
            mWindowManager.removeView(mFloatView);
        }
    }

    private void startExpansionAnimation7() {

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setDuration(2000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        animationSet.addAnimation(alphaAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.addAnimation(scaleAnimation);

        mFloatView.findViewById(R.id.float_view).startAnimation(animationSet);
    }


    private void startExpansionAnimation6() {
        final int initialRadiusX = 0;
        final int initialRadiusY = 0;

        final int finalRadiusX = mTextView.getWidth();
        final int finalRadiusY = mTextView.getHeight();

        int centerX = mLayoutParams.x;
        int centerY = mLayoutParams.y;

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);

        ValueAnimator animatorX = ValueAnimator.ofInt(initialRadiusX, finalRadiusX);
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentRadius = (int) animation.getAnimatedValue();
                mLayoutParams.width = currentRadius;
                mLayoutParams.x = centerX - currentRadius / 2;
                mWindowManager.updateViewLayout(mFloatView, mLayoutParams);
//                view.setAlpha(1 - (float) currentRadius / finalRadius); // 渐变透明度
            }
        });

        ValueAnimator animatorY = ValueAnimator.ofInt(initialRadiusY, finalRadiusY);
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentRadius = (int) animation.getAnimatedValue();
                mLayoutParams.height = currentRadius;
                mLayoutParams.y = centerY - currentRadius / 2;
                mWindowManager.updateViewLayout(mFloatView, mLayoutParams);
//                view.setAlpha(1 - (float) currentRadius / finalRadius); // 渐变透明度
            }
        });

        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
            }
        });
        animatorSet.start();
    }

    private void startExpansionAnimation5(final View view) {
        final int initialRadiusX = 0;
        final int initialRadiusY = 0;

        final int finalRadiusX = mTextView.getWidth();
        final int finalRadiusY = mTextView.getHeight();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);


        ValueAnimator animatorX = ValueAnimator.ofInt(initialRadiusX, finalRadiusX);
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentRadius = (int) animation.getAnimatedValue();
                view.getLayoutParams().width = currentRadius; // 设置宽度
                view.requestLayout();
//                view.setAlpha(1 - (float) currentRadius / finalRadius); // 渐变透明度
            }
        });

        ValueAnimator animatorY = ValueAnimator.ofInt(initialRadiusY, finalRadiusY);
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentRadius = (int) animation.getAnimatedValue();
                view.getLayoutParams().height = currentRadius; // 设置高度
                view.requestLayout();
//                view.setAlpha(1 - (float) currentRadius / finalRadius); // 渐变透明度
            }
        });

        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();
    }

    private void startExpansionAnimation4(final View view) {
        final int initialRadius = 0; // 初始半径
        final int targetX = mTextView.getWidth();
        final int targetY = mTextView.getHeight();
        final int finalRadius = Math.max(targetX, targetY); // 扩展半径

        ValueAnimator animator = ValueAnimator.ofInt(initialRadius, finalRadius);
        animator.setDuration(2000); // 动画持续时间
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentRadius = (int) animation.getAnimatedValue();
                view.getLayoutParams().width = currentRadius * 2; // 设置宽度
                view.getLayoutParams().height = currentRadius * 2; // 设置高度
                view.requestLayout();
//                view.setAlpha(1 - (float) currentRadius / finalRadius); // 渐变透明度
            }
        });

        animator.start(); // 启动动画
    }

    private void startExpansionAnimation3(View view) {
        final int initialRadiusX = view.getWidth();
        final int initialRadiusY = view.getHeight();

        final int targetX = mTextView.getWidth();
        final int targetY = mTextView.getHeight();
        final int finalRadius = Math.max(targetX, targetY); // 扩展半径

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
//        AnimationSet animationSet = new AnimationSet(true);
//        animationSet.setInterpolator(new AccelerateInterpolator());
//        animationSet.setDuration(2000);

        ValueAnimator animatorX = ValueAnimator.ofFloat(initialRadiusX, targetX);
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                view.setScaleX(animatedValue / initialRadiusX);
//                mCircleView.setAlpha(1 - (animatedValue - initialRadius) / (finalRadius - initialRadius)); // 渐变透明度
            }
        });

        ValueAnimator animatorY = ValueAnimator.ofFloat(initialRadiusY, targetY);
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                view.setScaleY(animatedValue / initialRadiusY);
//                mCircleView.setAlpha(1 - (animatedValue - initialRadius) / (finalRadius - initialRadius)); // 渐变透明度
            }
        });

        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();
    }


    private void startExpansionAnimation2() {
        final int initialRadiusX = mCircleView.getWidth();
        final int initialRadiusY = mCircleView.getHeight();
        final int initialRadius = Math.min(initialRadiusX, initialRadiusY);

        final int targetX = mTextView.getWidth();
        final int targetY = mTextView.getHeight();
        final int finalRadius = Math.max(targetX, targetY); // 扩展半径

        ValueAnimator animator = ValueAnimator.ofFloat(initialRadius, finalRadius);
        animator.setDuration(2000); // 设置动画时长
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (animatedValue <= targetX) {
                    mCircleView.setScaleX(animatedValue / initialRadiusX);
                }
                if (animatedValue <= targetY) {
                    mCircleView.setScaleY(animatedValue / initialRadiusY);
                }
//                mCircleView.setAlpha(1 - (animatedValue - initialRadius) / (finalRadius - initialRadius)); // 渐变透明度
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束时，可选择重置视图或执行其他操作
                mCircleView.setScaleX(1); // 重置缩放
                mCircleView.setScaleY(1);
                mCircleView.setAlpha(1); // 重置透明度
            }
        });

        animator.start(); // 启动动画
    }

    private void startExpansionAnimation() {
        final int initialRadius = mCircleView.getWidth() / 2;
        final int finalRadius = Math.max(mCircleView.getWidth(), mCircleView.getHeight()) * 2; // 扩展半径

        ValueAnimator animator = ValueAnimator.ofFloat(initialRadius, finalRadius);
        animator.setDuration(1000); // 设置动画时长
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mCircleView.setScaleX(animatedValue / initialRadius);
                mCircleView.setScaleY(animatedValue / initialRadius);
                mCircleView.setAlpha(1 - (animatedValue - initialRadius) / (finalRadius - initialRadius)); // 渐变透明度
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束时，可选择重置视图或执行其他操作
                mCircleView.setScaleX(1); // 重置缩放
                mCircleView.setScaleY(1);
                mCircleView.setAlpha(1); // 重置透明度
            }
        });

        animator.start(); // 启动动画
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mBorderLayout = null;
        mTextView = null;
        mCircleView = null;
        binding = null;
    }
}