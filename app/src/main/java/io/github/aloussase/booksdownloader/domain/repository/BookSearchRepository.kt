package io.github.aloussase.booksdownloader.domain.repository

import io.github.aloussase.booksdownloader.data.Book

interface BookSearchRepository {
    suspend fun search(query: String): List<Book>
}
