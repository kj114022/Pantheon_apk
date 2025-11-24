package io.github.aloussase.booksdownloader.domain.source

import io.github.aloussase.booksdownloader.data.Book

interface BookSource {
    suspend fun search(query: String): List<Book>
}
