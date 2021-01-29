package com.kryptkode.bookfinder.util.imageloader

import android.content.Context
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.kryptkode.bookfinder.R


class PlaceHolderDrawable(context: Context) : CircularProgressDrawable(context) {
    init {
        strokeWidth = 5f
        centerRadius = 30f
        colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(
                context,
                R.color.purple_200
            ), android.graphics.PorterDuff.Mode.SRC_IN
        )
        start()
    }
}
