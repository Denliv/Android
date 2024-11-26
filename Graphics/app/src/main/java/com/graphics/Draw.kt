package com.graphics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class Draw(context: Context?) : View(context) {
    private val paint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {
        //1080x2176
        val r = 100F
        super.onDraw(canvas)
        paint.apply {
            style = Paint.Style.FILL
            color = Color.rgb(3, 169, 244)
        }
        canvas.drawPaint(paint)
        canvas.save()
        canvas.rotate(-90F, x + 540F, y + 1088F)
        var yCoordRight = 900F
        val yCoordLeft: Float = (yCoordRight + 2.25F * r - yCoordRight) / 2 + yCoordRight
        val width = 20F
        paint.apply {
            color = Color.rgb(230, 230, 230)
        }
        canvas.drawRect(1080F / 2 - 3 * r / 2 - width,
            yCoordRight - r - width,
            1080F / 2 + 3 * r / 2 + width,
            yCoordRight + 2 * (2.25F * r) + r + width,
            paint)
        paint.apply {
            isAntiAlias = true
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = width
        }
        canvas.drawCircle(1080F / 2 + r / 2, yCoordRight, r, paint)
        paint.color = Color.BLACK
        canvas.drawCircle(1080F / 2 + r / 2, yCoordRight + 2.25F * r, r, paint)
        paint.color = Color.RED
        yCoordRight += 2.25F * r
        canvas.drawCircle(1080F / 2 + r / 2, yCoordRight + 2.25F * r, r, paint)
        paint.color = Color.YELLOW
        canvas.drawCircle(1080F / 2 - r / 2, yCoordLeft, r, paint)
        paint.color = Color.GREEN
        yCoordRight += 2.25F * r
        canvas.drawCircle(1080F / 2 - r / 2, yCoordLeft + 2.25F * r, r, paint)
        canvas.restore()
        paint.color = Color.BLACK
        canvas.drawLine(
            360F - r - 2 * width,
            1088F - 2F * r,
            360F - r - 2 * width,
            2176F,
            paint)
    }
}