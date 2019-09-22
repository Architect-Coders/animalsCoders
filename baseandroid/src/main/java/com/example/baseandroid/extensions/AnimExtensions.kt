package com.example.baseandroid.extensions

import androidx.fragment.app.FragmentTransaction
import com.example.baseandroid.R
import com.example.baseandroid.enum.AnimationsFragments

fun FragmentTransaction.getAnimation(animation: AnimationsFragments): FragmentTransaction =
    when (animation) {
        AnimationsFragments.PUSH_FRAGMENT -> {
            this.setCustomAnimations(
                R.anim.fragment_enter,
                R.anim.fragment_exit,
                R.anim.fragment_pop_enter,
                R.anim.fragment_pop_exit
            )
        }
        AnimationsFragments.PUSH_FRAGMENT_AND_FADE -> {
            this.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        }
        AnimationsFragments.PUSH_MODAL -> {
            this.setCustomAnimations(
                R.anim.fragment_enter_modal,
                R.anim.fragment_exit_modal,
                R.anim.fragment_pop_enter_modal,
                R.anim.fragment_pop_exit_modal
            )
        }
    }