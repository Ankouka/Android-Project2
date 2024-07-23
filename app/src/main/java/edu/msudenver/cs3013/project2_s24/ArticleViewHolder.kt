package edu.msudenver.cs3013.project2_s24

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArticleViewHolder(private val view: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.item_title)
    private val description: TextView = view.findViewById(R.id.item_description)
    private val image: ImageView = view.findViewById(R.id.item_image)

    fun bind(article: Article) {
        title.text = article.title
        description.text = article.description

        // Load image using a library like Glide or Picasso
        article.urlToImage?.let { imageLoader.loadImage(it, image) }
    }
}