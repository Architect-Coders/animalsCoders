package com.architectcoders.animalcoders.search.detail

import com.architectcoders.animalcoders.R
import com.example.baseandroid.activity.BaseActivity
import com.example.baseandroid.extensions.loadUrl
import kotlinx.android.synthetic.main.animal_detail_activity.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class AnimalDetailActivity :
    BaseActivity<AnimalDetailActivityViewState, AnimalDetailActivityViewTransition, AnimalDetailActivityViewModel>() {


    override val viewModel: AnimalDetailActivityViewModel by viewModel{
        parametersOf(intent.getSerializableExtra(ANIMAL))
    }

    override fun getLayout(): Int = R.layout.animal_detail_activity

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageState(state: AnimalDetailActivityViewState) {
        when(state){
            is AnimalDetailActivityViewState.ViewLoaded -> {
                state.animal?.let {
                    tbAnimalDetailToolbar.title = it.name
                    ivAnimalDetailImage.loadUrl(it.pictureUrl)
                    animalDetailInfo.setAnimal(it)
                }
            }
        }
    }

    override fun manageTransition(transition: AnimalDetailActivityViewTransition) {

    }

    override fun initListeners() {

    }

    companion object {
        const val ANIMAL = "AnimalDetailActivity:animal"
    }
}
