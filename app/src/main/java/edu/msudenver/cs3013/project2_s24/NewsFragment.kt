package edu.msudenver.cs3013.project2_s24

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = ArticleAdapter(mutableListOf(), object : ArticleAdapter.OnClickListener {
            override fun onItemClick(article: Article) {
                // Handle item click
            }
        })

        recyclerView.adapter = adapter
        fetchTopHeadlines()
        return view
    }

    private fun fetchTopHeadlines() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getTopHeadlines("71225f6ae98944518fcad21c9c93f042", "us")
                Log.d("GamesListFragment", "API Response: $response")
                adapter.setData(response.articles)
                if (response.articles.isNotEmpty()) {
                    adapter.setData(response.articles)
                    Log.d("GamesListFragment", "Data loaded successfully: ${response.articles.size} articles")
                } else {
                    Log.d("GamesListFragment", "No articles found")
                }
            } catch (e: Exception) {
                Log.e("GamesListFragment", "Error fetching data", e)
            }
            }
        }
    }

