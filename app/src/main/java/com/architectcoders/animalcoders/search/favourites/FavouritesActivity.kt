package com.architectcoders.animalcoders.search.favourites

import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.search.AnimalsAdapter
import com.example.baseandroid.activity.BaseActivity
import com.example.baseandroid.extensions.loadUrl
import kotlinx.android.synthetic.main.animal_detail_activity.*
import kotlinx.android.synthetic.main.favourites_activity.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class FavouritesActivity :
    BaseActivity<FavouritesActivityViewState, FavouritesActivityViewTransition, FavouritesActivityViewModel>() {


    override val viewModel: FavouritesActivityViewModel by viewModel()

    override fun getLayout(): Int = R.layout.favourites_activity

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageState(state: FavouritesActivityViewState) {
        when (state) {
            is FavouritesActivityViewState.FavouritesLoaded -> {
                state.animals?.let {
                    favouritesRecyclerView.adapter = FavouritesAdapter(it)
                }
            }
        }
    }

    override fun manageTransition(transition: FavouritesActivityViewTransition) {

    }

    override fun initListeners() {

    }

}
