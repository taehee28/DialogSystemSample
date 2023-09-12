package com.thk.dialogsystemsample.util

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

fun getDeviceWidth(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        windowManager.currentWindowMetrics.bounds.width()
    } else {
        DisplayMetrics()
            .apply { windowManager.defaultDisplay.getMetrics(this) }
            .widthPixels
    }
}
