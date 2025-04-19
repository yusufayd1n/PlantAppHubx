package com.example.plantapphubx.core.util

import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView

fun TextView.applyGradientText(startColor: Int, endColor: Int) {
    this.post {
        val shader = LinearGradient(
            0f, 0f, 0f, this.height.toFloat(),
            startColor, endColor,
            Shader.TileMode.CLAMP
        )
        this.paint.shader = shader
        this.invalidate()
    }
}