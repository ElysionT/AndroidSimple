package org.simple.animationsimple.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class BracketView extends View {
    private Paint paint;
    private Path path;
    private float leftBracketXStart, leftBracketXEnd;
    private float rightBracketXStart, rightBracketXEnd;
    private float bracketWidth = 50f;
    private float bracketHeight = 150f;
    private boolean isAnimating = false;

    public BracketView(Context context) {
        super(context);
        init();
    }

    public BracketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BracketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        path = new Path();

        // Initial positions for the brackets (centered horizontally)
        leftBracketXStart = -bracketWidth / 2;
        leftBracketXEnd = -getWidth() / 2 - bracketWidth;
        rightBracketXStart = getWidth() / 2 + bracketWidth / 2;
        rightBracketXEnd = getWidth() + bracketWidth * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw left bracket
        path.reset();
        path.moveTo(leftBracketXStart, getHeight() / 2 - bracketHeight / 2);
        path.lineTo(leftBracketXStart, getHeight() / 2 + bracketHeight / 2);
        path.lineTo(leftBracketXStart + bracketWidth / 2, getHeight() / 2);
        path.close();
        canvas.drawPath(path, paint);

        // Draw right bracket
        path.reset();
        path.moveTo(rightBracketXStart - bracketWidth / 2, getHeight() / 2 - bracketHeight / 2);
        path.lineTo(rightBracketXStart - bracketWidth / 2, getHeight() / 2 + bracketHeight / 2);
        path.lineTo(rightBracketXStart, getHeight() / 2);
        path.close();
        canvas.drawPath(path, paint);
    }

    public void startAnimation() {
        if (!isAnimating) {
            isAnimating = true;

            ValueAnimator animatorLeft = ValueAnimator.ofFloat(leftBracketXStart, leftBracketXEnd);
            ValueAnimator animatorRight = ValueAnimator.ofFloat(rightBracketXStart, rightBracketXEnd);

            animatorLeft.setInterpolator(new LinearInterpolator());
            animatorRight.setInterpolator(new LinearInterpolator());

            animatorLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    leftBracketXStart = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });

            animatorRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    rightBracketXStart = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });

            animatorLeft.setDuration(1000); // 1 second duration
            animatorRight.setDuration(1000); // 1 second duration
            animatorLeft.start();
            animatorRight.start();

            // Optionally, set a listener to stop animation after completion
            animatorLeft.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAnimating = false; // Reset animation flag
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

            animatorRight.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAnimating = false; // Reset animation flag
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
    }
}