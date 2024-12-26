package org.simple.animationsimple.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.widget.FrameLayout


class BorderLayout2 : FrameLayout {
    companion object {
        private const val TAG = "BorderLayout2"
        private val borderColor = Color.parseColor("#3678FF")
        private const val LINE_HEIGHT = 2F
    }

    private var mPaint: Paint? = null

    constructor(context: Context) : super(context) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initPaint()
    }

    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.setColor(borderColor)
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeWidth = LINE_HEIGHT
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OUT))
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.TRANSPARENT)
        super.onDraw(canvas)
        drawLine(canvas)
    }

    private fun drawLine(canvas: Canvas) {
        canvas.drawRect(4F, 4F, width - 4F, height - 4F, mPaint!!)
    }
}