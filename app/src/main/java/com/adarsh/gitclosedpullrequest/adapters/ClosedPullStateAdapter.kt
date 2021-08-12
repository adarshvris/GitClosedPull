package com.adarsh.gitclosedpullrequest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.adarsh.gitclosedpullrequest.viewholders.ClosePullLoadStateVH
import com.example.gitclosedpullrequest.databinding.LoadStateViewBinding

class ClosedPullStateAdapter(private val retry: () -> Unit) :
        LoadStateAdapter<ClosePullLoadStateVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ClosePullLoadStateVH =
            ClosePullLoadStateVH(
                    LoadStateViewBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                    ), retry
            )

    override fun onBindViewHolder(holder: ClosePullLoadStateVH, loadState: LoadState) {
        holder.bind(loadState)
    }
}