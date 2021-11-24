package com.example.colorgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class ColorGame(context: Context?) : View(context) {
    val p = Paint();
    var centerX = 50f
    var centerY = 50f

    val tiles = Array(2) { BooleanArray(2) { Random.nextBoolean() } }
    val tileSize = 200;
    val margin = 30;
    var won = false;

    override fun onDraw(canvas: Canvas?) {

        p.color = Color.RED
        canvas?.apply {
            drawColor(resources.getColor(R.color.cardview_dark_background))

            var cnt = 0

            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    p.color = if (tiles[i][j]) Color.WHITE else Color.BLACK;

                    cnt += if (tiles[i][j]) 1 else 0;

                    drawRoundRect(
                        j * tileSize + (j + 1f) * margin,
                        i * tileSize + (i + 1f) * margin,
                        (j + 1f) * (margin + tileSize),
                        (i + 1f) * (margin + tileSize),
                        20f, 20f, p
                    )
                }
            }

            won = cnt == 0 || cnt == 4;
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("Touched point", "(${event?.rawX}; ${event?.rawY})")
        if (won) return false;

        event?.apply {
            if (action != MotionEvent.ACTION_DOWN) return false;
            var touchedRect: Int? = null;

            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    val left = j * tileSize + (j + 1f) * margin
                    val right = (j + 1f) * (margin + tileSize)
                    val top = i * tileSize + (i + 1f) * margin
                    val bottom = (i + 1f) * (margin + tileSize)

                    if (x >= left && x <= right &&
                        y >= top && y <= bottom
                    ) {
                        touchedRect = i * 2 + j
                        break
                    }
                }
            }

            if (touchedRect != null) {
                for (i in 0 until 2) {
                    for (j in 0 until 2) {
                        if (3 - touchedRect != (i * 2 + j)) {
                            tiles[i][j] = !tiles[i][j]
                        }
                    }
                }
            }
            invalidate()
        }
        return true
    }
}