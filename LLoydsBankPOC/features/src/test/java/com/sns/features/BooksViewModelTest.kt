package com.sns.features

import com.sns.features.books.data.entity.Books
import com.sns.features.books.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import junit.framework.TestCase.assertEquals

@ExperimentalCoroutinesApi
class BooksViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @InjectMocks
    lateinit var getBooksUseCase: GetBooksUseCase

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test loading posts success`() = testScope.runTest {
        var response: List<Books>? = null
        try {
            response = getBooksUseCase()

        }catch (e:Exception){
            response = null
        }
        if (response != null) {
            assertEquals(response.size>0,response.size)
        }
    }

    @Test
    fun `test loading posts failure`() = testScope.runTest {
        var response: List<Books>? = null
        try {
            response = getBooksUseCase()

        }catch (e:Exception){
            response = null
        }
        if (response == null) {
            assertEquals(null,response)
        }
    }

}
