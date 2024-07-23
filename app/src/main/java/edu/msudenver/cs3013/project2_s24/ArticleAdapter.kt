package edu.msudenver.cs3013.project2_s24

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArticleAdapter(
    private val articles: MutableList<Article>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    fun setData(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]


        val imageUrl = article.urlToImage
        Log.d("GlideDebug", "Loading image from: $imageUrl")
        holder.bind(articles[position])
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.item_title)
        private val descriptionView: TextView = itemView.findViewById(R.id.item_description)
        private val imageView: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(article: Article) {
            titleView.text = article.title
            descriptionView.text = article.description
            Glide.with(itemView.context).load(article.urlToImage).into(imageView)

            val imageUrl = article.urlToImage
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder) // Optional: placeholder while loading
                    .error(R.drawable.error) // Optional: error image if loading fails
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.placeholder) // Set a default placeholder image
            }

            itemView.setOnClickListener {
                onClickListener.onItemClick(article)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(article: Article)
    }
}