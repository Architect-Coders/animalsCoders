package com.architectcoders.animalcoders.profile

import com.architectcoders.animalcoders.R
import com.example.baseandroid.fragment.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileFragmentViewState, ProfileFragmentViewTransition, ProfileFragmentViewModel>(
        true
    ) {

    override val viewModel: ProfileFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_profile

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageState(state: ProfileFragmentViewState) {

    }

    override fun manageTransition(transition: ProfileFragmentViewTransition) {

    }

    override fun initListeners() {

    }

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}
