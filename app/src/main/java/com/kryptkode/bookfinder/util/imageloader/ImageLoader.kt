package com.kryptkode.bookfinder.util.imageloader

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.kryptkode.bookfinder.R

class ImageLoader constructor(private val context: Context) {

    fun load(
        imageSource: String,
        target: ImageView,
        @DrawableRes errorResId: Int = R.drawable.ic_book
    ) {
        GlideApp.with(context)
            .load(imageSource)
            .placeholder(PlaceHolderDrawable(context))
            .error(errorResId)
            .into(target)
    }
}
