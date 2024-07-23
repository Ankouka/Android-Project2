package edu.msudenver.cs3013.project2_s24

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(background_image: String, imageView: ImageView)
}