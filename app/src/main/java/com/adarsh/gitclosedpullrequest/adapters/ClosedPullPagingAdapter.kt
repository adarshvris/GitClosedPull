package com.adarsh.gitclosedpullrequest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.adarsh.gitclosedpullrequest.diffutils.ClosedPullDiffUtil
import com.adarsh.gitclosedpullrequest.models.ClosedPullData
import com.adarsh.gitclosedpullrequest.viewholders.ClosedPullVH
import com.example.gitclosedpullrequest.databinding.ClosedPullItemBinding

class ClosedPullPagingAdapter : PagingDataAdapter<ClosedPullData, ClosedPullVH>(ClosedPullDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedPullVH =
            ClosedPullVH(
                    binding = ClosedPullItemBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: ClosedPullVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}