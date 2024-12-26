package org.simple.animationsimple.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import org.simple.animationsimple.R


class BorderLayout : FrameLayout {

    companion object {
        private const val TAG = "BorderLayout"
        private val borderColor = Color.parseColor("#3678FF")
        private const val LINE_HEIGHT = 2F
        private const val CORNER_HEIGHT = 6F
        private const val CORNER_HEIGHT_OFFSET = CORNER_HEIGHT / 2
        private const val CORNER_LENGTH = 22F

    }

    private var mPaint: Paint? = null
    private var mCornerPaint: Paint? = null

    private var mStatus: Int = 1

    private var bgBitmapLt: Bitmap? = null
    private var bgBitmapLb: Bitmap? = null
    private var bgBitmapRt: Bitmap? = null
    private var bgBitmapRb: Bitmap? = null

    constructor(context: Context) : super(context) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
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

        mCornerPaint = Paint()
        mCornerPaint!!.setColor(borderColor)
        mCornerPaint!!.strokeWidth = CORNER_HEIGHT
        mCornerPaint!!.isAntiAlias = true
        mCornerPaint!!.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OVER))

        bgBitmapLt = decodeBitmapFromResource(context, R.drawable.line_lt)
        bgBitmapLb = decodeBitmapFromResource(context, R.drawable.line_lb)
        bgBitmapRt = decodeBitmapFromResource(context, R.drawable.line_rt)
        bgBitmapRb = decodeBitmapFromResource(context, R.drawable.line_rb)
    }

    override fun onDraw(canvas: Canvas) {
//        background = null
        canvas.drawColor(Color.TRANSPARENT)
        super.onDraw(canvas)
//        if (mStatus == STATUS_HIVE_BORDER) {
//            return
//        }
//        if (mStatus == MSG_TOUCH_DOWN) {
//            drawLine(canvas)
//        }
        drawCorner(canvas)
    }

    private fun drawLine(canvas: Canvas) {
        canvas.drawRect(4F, 4F, width - 4F, height - 4F, mPaint!!)
    }

    private fun drawCorner(canvas: Canvas) {

        canvas.drawBitmap(bgBitmapLt!!, 0F, 0F, mCornerPaint!!)
        canvas.drawBitmap(
            bgBitmapLb!!,
            0F,
            (height - bgBitmapLb!!.height).toFloat(),
            mCornerPaint!!
        )
        canvas.drawBitmap(bgBitmapRt!!, (width - bgBitmapRb!!.width).toFloat(), 0F, mCornerPaint!!)
        canvas.drawBitmap(
            bgBitmapRb!!,
            (width - bgBitmapRb!!.width).toFloat(),
            (height - bgBitmapLb!!.height).toFloat(),
            mCornerPaint!!
        )
    }

    fun setStatus(status: Int) {
        mStatus = status

        invalidate()
    }

    private fun decodeBitmapFromResource(context: Context, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}