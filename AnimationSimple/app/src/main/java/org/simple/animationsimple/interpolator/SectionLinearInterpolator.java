package org.simple.animationsimple.interpolator;

import android.util.Log;
import android.view.animation.BaseInterpolator;

public class SectionLinearInterpolator extends BaseInterpolator {
    private static final String TAG = "SectionLinearInterpolator";
    // 这些是我们定义的关键值
    private static final float[] inputValues = {0.0f, 0.25f, 0.5f, 0.75f, 1.0f}; // 假设的输入范围
    private static final float[] outputValues = {0.13f, 1.31f, 0.61f, 0.98f, 1.0f}; // 对应输出值

    @Override
    public float getInterpolation(float input) {
        Log.i(TAG, "getInterpolation - input:" + input);
        // 确保输入值在0到1之间
        if (input <= 0) {
            return outputValues[0];
        } else if (input >= 1) {
            return outputValues[outputValues.length - 1];
        }

        // 使用线性插值来查找对应的输出值
        for (int i = 0; i < inputValues.length - 1; i++) {
            if (input >= inputValues[i] && input <= inputValues[i + 1]) {
//                // 线性插值计算
                float range = inputValues[i + 1] - inputValues[i];
                float fraction = (input - inputValues[i]) / range;
                return outputValues[i] + (outputValues[i + 1] - outputValues[i]) * fraction;
            }
        }
        return 0; // 默认情况
    }
}
