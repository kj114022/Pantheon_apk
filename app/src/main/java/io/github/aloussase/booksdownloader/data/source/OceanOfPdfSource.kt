package io.github.aloussase.booksdownloader.data.source

import android.net.Uri
import io.github.aloussase.booksdownloader.data.Book
import io.github.aloussase.booksdownloader.domain.source.BookSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.URLEncoder
import javax.inject.Inject

class OceanOfPdfSource @Inject constructor() : BookSource {

    private val baseUrl = "https://oceanofpdf.com"

    override suspend fun search(query: String): List<Book> = withContext(Dispatchers.IO) {
        val searchUrl = "$baseUrl/?s=${query.replace(" ", "+")}"
        val books = mutableListOf<Book>()

        try {
            val doc = Jsoup.connect(searchUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .timeout(10000)
                .get()

            val articles = doc.select("article")

            for (article in articles) {
                val titleElement = article.selectFirst("h2.title > a") ?: continue
                val title = titleElement.text()
                val detailsUrl = titleElement.attr("href")
                
                val imgElement = article.selectFirst("img")
                val imageUrl = imgElement?.attr("src") ?: ""
                
                // OceanOfPdf usually has PDF and EPUB. We can't know for sure without visiting details,
                // but we can assume both or list it generically.
                // For the list, let's assume PDF as primary or check tags.
                
                // We'll create two entries if we want to be explicit, or one generic one.
                // Let's create one generic entry and let the details page handle the choice.
                // But our Book model requires an extension.
                
                books.add(Book(
                    id = detailsUrl.hashCode().toLong(),
                    authors = listOf("Unknown"), // Author is often in the title or details
                    title = title,
                    extension = "pdf/epub", // Placeholder
                    downloadUrl = Uri.parse(detailsUrl),
                    imageUrl = imageUrl,
                    size = "?",
                    source = "OceanOfPdf",
                    detailsUrl = detailsUrl
                ))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        books
    }
}
