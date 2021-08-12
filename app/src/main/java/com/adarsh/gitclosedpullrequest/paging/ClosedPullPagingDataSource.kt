package com.adarsh.gitclosedpullrequest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adarsh.gitclosedpullrequest.api.ClosedPullApi
import com.adarsh.gitclosedpullrequest.base.network.ApiException
import com.adarsh.gitclosedpullrequest.base.network.deserialize
import com.adarsh.gitclosedpullrequest.models.ClosedPullData

class ClosedPullPagingDataSource(private val closedPullApi: ClosedPullApi) :
        PagingSource<Int, ClosedPullData>() {

    companion object {
        const val STARTING_INDEX = 1
        const val NETWORK_PAGE_SIZE = 10
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClosedPullData> {
        val nextPage = params.key ?: STARTING_INDEX
        return try {
            val response = closedPullApi.getClosedPullList(page = nextPage)
            if (response.isSuccessful) {
                val result = response.body()?.toList() as List<ClosedPullData>
                LoadResult.Page(
                        data = result,
                        prevKey = if (nextPage == STARTING_INDEX) null else nextPage,
                        nextKey = if (result.isEmpty()) {
                            null
                        } else {
                            nextPage + (params.loadSize / NETWORK_PAGE_SIZE)
                        }
                )
            } else {
                LoadResult.Error(
                        ApiException(
                                responseCode = response.code(),
                                errorResponse = response.errorBody()?.deserialize(),
                                failedUrl = response.raw().request().url().toString()
                        )
                )
            }
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ClosedPullData>): Int? =
            state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
}