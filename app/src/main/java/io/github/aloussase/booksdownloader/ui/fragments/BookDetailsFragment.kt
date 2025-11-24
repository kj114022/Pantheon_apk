package io.github.aloussase.booksdownloader.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.github.aloussase.booksdownloader.R
import io.github.aloussase.booksdownloader.databinding.FragmentBookDetailsBinding
import io.github.aloussase.booksdownloader.GlideApp
import io.github.aloussase.booksdownloader.data.cover

@AndroidEntryPoint
class BookDetailsFragment : BaseApplicationFragment(R.layout.fragment_book_details) {

    private val args: BookDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentBookDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookDetailsBinding.bind(view)

        val book = args.book

        binding.apply {
            tvTitle.text = book.title
            tvAuthor.text = book.authors.joinToString(", ")
            chipSource.text = book.source
            
            GlideApp.with(this@BookDetailsFragment)
                .load(book.cover())
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into(ivBookCover)

            btnDownload.setOnClickListener {
                setBookForDownload(book)
                downloadBook()
            }
        }
    }
}
