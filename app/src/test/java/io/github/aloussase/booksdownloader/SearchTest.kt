package io.github.aloussase.booksdownloader

import android.util.Log
import io.github.aloussase.booksdownloader.data.source.LibgenSource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchTest {
    
    @Test
    fun testLibgenSearch() = runBlocking {
        val libgen = LibgenSource()
        val results = libgen.search("Harry Potter")
        Log.d("SearchTest", "Got ${results.size} results")
        results.forEach {
            Log.d("SearchTest", "Book: ${it.title} from ${it.source}")
        }
        assert(results.isNotEmpty()) { "Expected some results but got none" }
    }
}
