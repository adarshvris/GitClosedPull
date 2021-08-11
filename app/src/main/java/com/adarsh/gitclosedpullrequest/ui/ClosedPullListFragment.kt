package com.adarsh.gitclosedpullrequest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adarsh.gitclosedpullrequest.base.factory.ViewModelFactory
import com.example.gitclosedpullrequest.databinding.FragmentClosedPullListBinding
import com.adarsh.gitclosedpullrequest.ui.viewmodels.ClosedPullListViewModel

class ClosedPullListFragment : Fragment() {

    private var _binding: FragmentClosedPullListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClosedPullListViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory {
            ClosedPullListViewModel()
        }).get(ClosedPullListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClosedPullListBinding.inflate(inflater, container, false)
        return binding.root
    }
}