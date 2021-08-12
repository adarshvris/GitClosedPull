package com.adarsh.gitclosedpullrequest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.adarsh.gitclosedpullrequest.GitClosedPullApplication
import com.adarsh.gitclosedpullrequest.adapters.ClosedPullPagingAdapter
import com.adarsh.gitclosedpullrequest.adapters.ClosedPullStateAdapter
import com.adarsh.gitclosedpullrequest.base.factory.ViewModelFactory
import com.adarsh.gitclosedpullrequest.extensions.setVisibility
import com.adarsh.gitclosedpullrequest.handlers.ErrorHandler
import com.adarsh.gitclosedpullrequest.ui.viewmodels.ClosedPullListViewModel
import com.example.gitclosedpullrequest.R
import com.example.gitclosedpullrequest.databinding.FragmentClosedPullListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClosedPullListFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentClosedPullListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClosedPullListViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory {
            ClosedPullListViewModel((activity?.applicationContext as GitClosedPullApplication).closedPullListRepo)
        }).get(ClosedPullListViewModel::class.java)
    }

    private var closedPullPagingAdapter: ClosedPullPagingAdapter? = null

    /*------------Life cycle call back methods-------------------*/
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClosedPullListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchClosedPullList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        closedPullPagingAdapter = null
        _binding = null
    }
    /*------------Life cycle call back methods-------------------*/

    /*------------Local member functions-------------------*/
    private fun fetchClosedPullList() {
        lifecycleScope.launch {
            viewModel.getClosedPullList().collectLatest { closedPullData ->
                closedPullPagingAdapter?.submitData(closedPullData)
            }
        }

        closedPullPagingAdapter?.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    binding.pbLoading.setVisibility(true)
                    binding.ivError.setVisibility(false)
                    binding.tvErrorMessage.setVisibility(false)
                    binding.btnRetry.setVisibility(false)
                }
                is LoadState.NotLoading -> {
                    binding.pbLoading.setVisibility(false)
                    binding.ivError.setVisibility(false)
                    binding.tvErrorMessage.setVisibility(false)
                    binding.btnRetry.setVisibility(false)
                }
                else -> {
                    binding.pbLoading.setVisibility(false)
                    binding.ivError.setVisibility(true)
                    binding.tvErrorMessage.setVisibility(true)
                    binding.btnRetry.setVisibility(true)

                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    binding.tvErrorMessage.text =
                            error?.let {
                                ErrorHandler.handleError(
                                        throwable = it.error,
                                        resources = resources
                                )
                            } ?: getString(R.string.no_network_connection)
                }
            }
        }
    }

    private fun initViews() {
        closedPullPagingAdapter = ClosedPullPagingAdapter()
        binding.rvClosedPull.apply {
            closedPullPagingAdapter?.let {
                adapter =
                        it.withLoadStateFooter(footer = ClosedPullStateAdapter {
                            it.retry()
                        })
            }
        }
        binding.btnRetry.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnRetry -> {
                fetchClosedPullList()
            }
        }
    }
    /*------------Local member functions-------------------*/
}