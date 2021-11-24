package com.example.colorgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class ColorGame(context: Context?) : View(context) {
    private val N = 2
    private val tiles = Array(N) { BooleanArray(N) { Random.nextBoolean() } }
    private val tileSize = 200f;
    private val gap = 30; // gap between tiles
    private var won = false;

    override fun onDraw(canvas: Canvas?) {
        val p = Paint();

        p.color = Color.RED
        canvas?.apply {
            drawColor(resources.getColor(R.color.cardview_dark_background))

            var cnt = 0

            for (i in 0 until N) {
                for (j in 0 until N) {
                    p.color = if (tiles[i][j]) Color.WHITE else Color.BLACK;

                    cnt += if (tiles[i][j]) 1 else 0;

                    drawRoundRect(
                        j * tileSize + (j + 1f) * gap,
                        i * tileSize + (i + 1f) * gap,
                        (j + 1f) * (gap + tileSize),
                        (i + 1f) * (gap + tileSize),
                        20f, 20f, p
                    )
                }
            }

            won = cnt == 0 || cnt == N * N;

            if (won) {
                val text = "Great job!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("Touched point", "(${event?.rawX}; ${event?.rawY})")
        if (won) return false;

        event?.apply {
            if (action != MotionEvent.ACTION_DOWN) return false;
            var touchedRectX: Int? = null;
            var touchedRectY: Int? = null;

            for (i in 0 until N) {
                for (j in 0 until N) {
                    val left = j * tileSize + (j + 1f) * gap
                    val right = (j + 1f) * (gap + tileSize)
                    val top = i * tileSize + (i + 1f) * gap
                    val bottom = (i + 1f) * (gap + tileSize)

                    if (x >= left && x <= right &&
                        y >= top && y <= bottom
                    ) {
                        touchedRectY = i
                        touchedRectX = j
                        break
                    }
                }
            }

            if (touchedRectX != null && touchedRectY != null) {
                invertColors(touchedRectY, touchedRectX)
            }
            invalidate()
        }
        return true
    }

    private fun invertColors(y: Int, x: Int) {
        for (i in 0 until N) {
            tiles[y][i] = !tiles[y][i]
            tiles[i][x] = !tiles[i][x]
        }

        tiles[y][x] = !tiles[y][x]
    }
}