package com.adarsh.gitclosedpullrequest.api

import com.adarsh.gitclosedpullrequest.models.ClosedPullData
import com.adarsh.gitclosedpullrequest.paging.ClosedPullPagingDataSource.Companion.NETWORK_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ClosedPullApi {

    companion object {
        const val GET_CLOSED_PULL_LIST = "repos/vmg/redcarpet/pulls"
    }

    @GET(GET_CLOSED_PULL_LIST)
    suspend fun getClosedPullList(
            @Query("state") state: String = "closed",
            @Query("page") page: Int,
            @Query("per_page") perPage: Int = NETWORK_PAGE_SIZE
    ): Response<List<ClosedPullData>>
}