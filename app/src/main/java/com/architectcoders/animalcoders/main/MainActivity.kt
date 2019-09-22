package com.architectcoders.animalcoders.main

import android.view.Menu
import android.view.MenuItem
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.login.LoginActivity
import com.example.baseandroid.activity.BaseActivity
import com.example.baseandroid.extensions.goToActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity :
    BaseActivity<MainActivityViewState, MainActivityViewTransition, MainActivityViewModel>() {

    override fun getActivityViewModel(): MainActivityViewModel =
        this.viewModel<MainActivityViewModel>().value

    override fun getLayout(): Int = R.layout.activity_main

    override fun initView() {
        viewModel.initView()
    }

    override fun manageState(state: MainActivityViewState) {
        when (state) {
            is MainActivityViewState.InitialState -> {
                setSupportActionBar(MainToolbar)
                MainBottomNav.selectedItemId = R.id.bottom_action_search
            }
        }

    }

    override fun manageTransition(transition: MainActivityViewTransition) {
        when (transition) {
            is MainActivityViewTransition.NavigateToLogin -> {
                finish()
                goToActivity<LoginActivity>()
            }
            is MainActivityViewTransition.NavigateToSearch -> {

            }
            is MainActivityViewTransition.NavigateToMap -> {

            }
            is MainActivityViewTransition.NavigateToProfile -> {

            }
        }
    }

    override fun initListeners() {
        MainBottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_action_search -> {
                    setSupportActionBarTitle(getString(R.string.tab_search))
                    true
                }

                R.id.bottom_action_map -> {
                    setSupportActionBarTitle(getString(R.string.tab_map))
                    true
                }

                R.id.bottom_action_profile -> {
                    setSupportActionBarTitle(getString(R.string.tab_profile))
                    true
                }
                else -> false
            }
        }
    }

    private fun setSupportActionBarTitle(title: String) {
        supportActionBar?.title = title
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_log_out_btn) {
            viewModel.logOut()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
