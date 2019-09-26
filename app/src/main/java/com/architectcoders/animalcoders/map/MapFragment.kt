package com.architectcoders.animalcoders.map

import com.architectcoders.animalcoders.R
import com.example.baseandroid.fragment.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment :
    BaseFragment<MapFragmentViewState, MapFragmentViewTransition, MapFragmentViewModel>(true) {

    override val viewModel: MapFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_map

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageState(state: MapFragmentViewState) {

    }

    override fun manageTransition(transition: MapFragmentViewTransition) {

    }

    override fun initListeners() {

    }

    companion object {
        val TAG: String = MapFragment::class.java.simpleName

        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}
