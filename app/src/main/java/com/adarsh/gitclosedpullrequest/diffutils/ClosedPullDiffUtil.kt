package com.adarsh.gitclosedpullrequest.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.adarsh.gitclosedpullrequest.models.ClosedPullData

class ClosedPullDiffUtil : DiffUtil.ItemCallback<ClosedPullData>() {

    override fun areItemsTheSame(oldItem: ClosedPullData, newItem: ClosedPullData): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ClosedPullData, newItem: ClosedPullData): Boolean =
            oldItem == newItem
}