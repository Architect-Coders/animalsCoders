package com.architectcoders.animalcoders.search

import com.architectcoders.animalcoders.R
import com.architectcoders.domain.model.Animal
import com.example.baseandroid.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment :
    BaseFragment<SearchFragmentViewState, SearchFragmentViewTransition, SearchFragmentViewModel>(
        true
    ) {

    lateinit var navigateToDetail: (animal: Animal) -> Unit

    override val viewModel: SearchFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_search

    override fun initView() {
        //view configurations
    }

    override fun manageState(state: SearchFragmentViewState) {
        when (state) {
            is SearchFragmentViewState.DrawAnimals -> {
                animalsRecyclerView.adapter = AnimalsAdapter(state.animals) {
                    viewModel.navigateToDetail(it)
                }
            }
        }
    }

    override fun manageTransition(transition: SearchFragmentViewTransition) {
        when (transition) {
            is SearchFragmentViewTransition.NavigateToAnimalDetail -> {
                navigateToDetail(transition.animal)
            }
        }
    }

    override fun initListeners() {

    }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
