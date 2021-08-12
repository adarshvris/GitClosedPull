package com.adarsh.gitclosedpullrequest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.adarsh.gitclosedpullrequest.repo.ClosedPullListRepo

class ClosedPullListViewModel(private val repo: ClosedPullListRepo) : ViewModel() {

    fun getClosedPullList() = repo.getClosedPullList().cachedIn(viewModelScope)
}