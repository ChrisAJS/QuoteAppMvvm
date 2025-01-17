package com.example.quoteappmvvm.data.local

import com.example.quoteappmvvm.data.QuoteDataSource
import com.example.quoteappmvvm.data.Result
import com.example.quoteappmvvm.data.Result.Success
import com.example.quoteappmvvm.data.model.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException


class QuoteLocalDataSource constructor( // for fetching locally stored quotes, might save favorite quootes or save the json quotes
    private val quotesDao: QuotesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : QuoteDataSource {

    override suspend fun getQuotes(): Result<List<Quote>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(quotesDao.getQuotes())
        } catch (e: Exception) {
            Result.Error(IOException("Unable to fetch quotes!"))
        }
    }

    suspend fun insertQuote(quote: Quote) = withContext(ioDispatcher) {
        try {
            quotesDao.insertFavoriteQuote(quote)
        } catch (e: Exception) {
            Result.Error(IOException("Unable to insert quote!"))
        }
    }

}

/*  try {
            Success(tasksDao.getTasks())
        } catch (e: Exception) {
            Error(e)
        }

 */