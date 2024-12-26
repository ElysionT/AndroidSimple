package org.simple.animationsimple.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ParenthesisView extends View {

    private Paint paint;

    private float leftParenthesisX, leftParenthesisY;

    private float rightParenthesisX, rightParenthesisY;


    public ParenthesisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        paint = new Paint();
        paint.setColor(0xFF000000); // 黑色
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);


        // 初始位置，可以根据需要调整

        leftParenthesisX = getWidth() / 4;

        leftParenthesisY = getHeight() / 2 - 50; // 括号上半部分

        rightParenthesisX = getWidth() * 3 / 4;

        rightParenthesisY = getHeight() / 2 + 50; // 括号下半部分

    }


    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        // 绘制左括号

        Path leftParenthesisPath = new Path();

        leftParenthesisPath.moveTo(leftParenthesisX, leftParenthesisY);

        leftParenthesisPath.lineTo(leftParenthesisX, leftParenthesisY + 100);

        leftParenthesisPath.quadTo(leftParenthesisX - 50, leftParenthesisY + 50, leftParenthesisX, leftParenthesisY);

        canvas.drawPath(leftParenthesisPath, paint);


        // 绘制右括号

        Path rightParenthesisPath = new Path();

        rightParenthesisPath.moveTo(rightParenthesisX, rightParenthesisY);

        rightParenthesisPath.lineTo(rightParenthesisX, rightParenthesisY - 100);

        rightParenthesisPath.quadTo(rightParenthesisX + 50, rightParenthesisY - 50, rightParenthesisX, rightParenthesisY);

        canvas.drawPath(rightParenthesisPath, paint);

    }


    // Setter 方法用于更新括号位置

    public void setLeftParenthesisX(float leftParenthesisX) {

        this.leftParenthesisX = leftParenthesisX;

        invalidate(); // 请求重新绘制

    }


    public void setLeftParenthesisY(float leftParenthesisY) {

        this.leftParenthesisY = leftParenthesisY;

        invalidate(); // 请求重新绘制

    }


    public void setRightParenthesisX(float rightParenthesisX) {

        this.rightParenthesisX = rightParenthesisX;

        invalidate(); // 请求重新绘制

    }


    public void setRightParenthesisY(float rightParenthesisY) {

        this.rightParenthesisY = rightParenthesisY;

        invalidate(); // 请求重新绘制

    }

}
