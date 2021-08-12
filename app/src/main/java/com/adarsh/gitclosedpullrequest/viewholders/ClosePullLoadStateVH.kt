package com.adarsh.gitclosedpullrequest.viewholders

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.adarsh.gitclosedpullrequest.handlers.ErrorHandler
import com.example.gitclosedpullrequest.databinding.LoadStateViewBinding

class ClosePullLoadStateVH(
        private val binding: LoadStateViewBinding,
        private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        with(binding) {
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            pbLoading.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                tvErrorMessage.text = ErrorHandler.handleError(
                        throwable = loadState.error,
                        resources = itemView.resources
                )
            }

            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }
}