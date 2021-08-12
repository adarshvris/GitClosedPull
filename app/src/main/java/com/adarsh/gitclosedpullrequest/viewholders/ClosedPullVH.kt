package com.adarsh.gitclosedpullrequest.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.adarsh.gitclosedpullrequest.extensions.showWhenDataIsAvailable
import com.adarsh.gitclosedpullrequest.models.ClosedPullData
import com.bumptech.glide.Glide
import com.example.gitclosedpullrequest.R
import com.example.gitclosedpullrequest.databinding.ClosedPullItemBinding

class ClosedPullVH(private val binding: ClosedPullItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(closedPullData: ClosedPullData) {
        with(binding) {
            tvName.showWhenDataIsAvailable(closedPullData.userData?.login)
            tvTitle.showWhenDataIsAvailable(closedPullData.title)
            tvCreatedOn.text = String.format(
                    itemView.resources.getString(R.string.createdOn),
                    closedPullData.createdTimeStamp?.substring(0, 10)
            )
            tvClosedOn.text = String.format(
                    itemView.resources.getString(R.string.closeddOn),
                    closedPullData.closedTimeStamp?.substring(0, 10)
            )
            Glide.with(itemView)
                    .load(closedPullData.userData?.userAvatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .fallback(R.drawable.ic_placeholder)
                    .into(ivAvatar)
        }
    }
}