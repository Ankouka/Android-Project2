package edu.msudenver.cs3013.project2_s24

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader (context: Context) : ImageLoader {
    private val glide by lazy { Glide.with(context) }

    override fun loadImage(background_image: String, imageView: ImageView)
    {
        glide.load(background_image)
            .centerCrop().into(imageView)
    }
}