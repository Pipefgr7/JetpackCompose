package com.unilibre.newsapp.data.repository

import com.unilibre.newsapp.data.model.Article
import com.unilibre.newsapp.data.remote.NewsProvider

// Interfaz
interface NewsRepository {
    suspend fun getTopHeadlines(): List<Article>
}

// Implementación
class NewsRepositoryImpl(
    private val newsProvider: NewsProvider
) : NewsRepository {
    override suspend fun getTopHeadlines(): List<Article> {
        return newsProvider.getTopHeadlines().articles
    }
}
