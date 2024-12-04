package com.sns.features.books.domain.usecases

import com.sns.features.books.data.entity.Books
import com.sns.features.books.data.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(): List<Books> = booksRepository.getBooksApiCall()
}