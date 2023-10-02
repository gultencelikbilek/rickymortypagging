package com.example.paggingcourse.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paggingcourse.api.ApiService
import com.example.paggingcourse.model.RickMorty
import javax.inject.Inject

class RickMortyPagingSource
@Inject constructor(private val apiService: ApiService):PagingSource<Int,RickMorty>(){
    override fun getRefreshKey(state: PagingState<Int, RickMorty>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickMorty> {
       return try {
           val currentPage= params.key ?: 1
           val response = apiService.getAllCharecters(currentPage)
           val data = response.body()?.results?: emptyList()
           val responseData = mutableListOf<RickMorty>()
           responseData.addAll(data)

           LoadResult.Page(
               data = responseData,
               prevKey = if (currentPage == 1) null else 1,
               nextKey = currentPage.plus(1)

           )




       }catch (e:Exception){
           LoadResult.Error(e)
       }
    }


}