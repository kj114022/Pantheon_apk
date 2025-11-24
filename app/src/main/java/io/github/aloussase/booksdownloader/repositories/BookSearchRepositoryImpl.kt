package io.github.aloussase.booksdownloader.repositories

import io.github.aloussase.booksdownloader.data.Book
import io.github.aloussase.booksdownloader.data.source.AnnasArchiveSource
import io.github.aloussase.booksdownloader.data.source.LibgenSource
import io.github.aloussase.booksdownloader.domain.repository.BookSearchRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val libgenSource: LibgenSource,
    private val annasArchiveSource: AnnasArchiveSource
) : BookSearchRepository {

    override suspend fun search(query: String): List<Book> = coroutineScope {
        val libgenDeferred = async { libgenSource.search(query) }
        val annasDeferred = async { annasArchiveSource.search(query) }

        val results = awaitAll(libgenDeferred, annasDeferred)
        results.flatten()
    }
}
