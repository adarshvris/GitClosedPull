package com.adarsh.gitclosedpullrequest.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adarsh.gitclosedpullrequest.api.ClosedPullApi
import com.adarsh.gitclosedpullrequest.models.ClosedPullData
import com.adarsh.gitclosedpullrequest.paging.ClosedPullPagingDataSource
import com.adarsh.gitclosedpullrequest.paging.ClosedPullPagingDataSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow


class ClosedPullListRepo(private val closedPullApi: ClosedPullApi) {

    companion object {
        const val PREFETCH_DISTANCE = 4
    }

    fun getClosedPullList(): Flow<PagingData<ClosedPullData>> {
        return Pager(config = PagingConfig(
                initialLoadSize = 2 * NETWORK_PAGE_SIZE,
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = true
        ), pagingSourceFactory = { ClosedPullPagingDataSource(closedPullApi) }).flow
    }

}