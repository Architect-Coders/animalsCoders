package com.architectcoders.animalcoders.profile

import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.register.RegisterActivity
import com.architectcoders.animalcoders.search.favourites.FavouritesActivity
import com.architectcoders.domain.model.Animal
import com.example.baseandroid.click.setSafeOnClickListener
import com.example.baseandroid.extensions.goToActivity
import com.example.baseandroid.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileFragmentViewState, ProfileFragmentViewTransition, ProfileFragmentViewModel>(
        true
    ) {

    lateinit var navigateToLogin: () -> Unit
    lateinit var navigateToFavourites: () -> Unit

    override val viewModel: ProfileFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_profile

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageState(state: ProfileFragmentViewState) {
        when(state) {
           is ProfileFragmentViewState.UserLoaded -> {
               ProfileName?.text = state.userName
            }
        }
    }

    override fun manageTransition(transition: ProfileFragmentViewTransition) {
        when(transition){
            ProfileFragmentViewTransition.NavigateToFavourites -> {
                navigateToFavourites()
            }
            ProfileFragmentViewTransition.NavigateToLogin -> {
                navigateToLogin()
            }
        }

    }

    override fun initListeners() {
        tvMyFavourites.setSafeOnClickListener {
            viewModel.onFavouritesClicked()
        }
    }

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}
