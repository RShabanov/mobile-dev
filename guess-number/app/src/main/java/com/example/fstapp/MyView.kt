package com.example.fstapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class MyView(context: Context?) : View(context) {
    var color = Color.RED

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val r = Random(1000)
        color = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255))

        canvas?.drawColor(Color.YELLOW)
        Log.d("mytag", "Color ${color}")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        invalidate()
        return super.onTouchEvent(event)
    }
}