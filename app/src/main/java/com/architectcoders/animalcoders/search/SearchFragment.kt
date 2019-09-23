package com.architectcoders.animalcoders.search

import com.architectcoders.animalcoders.R
import com.example.baseandroid.fragment.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment :
    BaseFragment<SearchFragmentViewState, SearchFragmentViewTransition, SearchFragmentViewModel>(
        true
    ) {

    override val viewModel: SearchFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_search

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageState(state: SearchFragmentViewState) {

    }

    override fun manageTransition(transition: SearchFragmentViewTransition) {

    }

    override fun initListeners() {

    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
