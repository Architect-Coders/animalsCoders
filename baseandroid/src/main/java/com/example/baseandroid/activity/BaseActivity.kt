package com.example.baseandroid.activity

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.baseandroid.fragment.BaseFragment
import com.example.baseandroid.viewmodel.BaseViewModel

abstract class BaseActivity<ViewState : Parcelable, ViewTransition, ViewModel : BaseViewModel<ViewState, ViewTransition>> :
    AppCompatActivity() {

    protected abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())

        if (savedInstanceState != null) {
            savedInstanceState.getParcelable<ViewState>(VIEW_STATE)?.let { viewState ->
                viewModel.setViewState(viewState)
            }
        } else {
            viewModel.init()
        }

        initView()
        viewModel.initServices()
        initListeners()
        initObservers()
    }

    abstract fun getLayout(): Int

    abstract fun initView()

    private fun initObservers() {
        viewModel.getViewState().observe(this, Observer {
            if (it != null) {
                manageState(state = it)
            }
        })

        viewModel.getViewTransition().observe(this, Observer {
            if (it != null) {
                manageTransition(transition = it)
            }
        })
    }

    abstract fun manageState(state: ViewState)

    abstract fun manageTransition(transition: ViewTransition)

    abstract fun initListeners()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(VIEW_STATE, viewModel.getViewState().value)
    }

    override fun onDestroy() {
        super.onDestroy()
        clearObservers()
    }

    private fun clearObservers() {
        viewModel.getViewState().removeObservers(this)
        viewModel.getViewTransition().removeObservers(this)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.isNotEmpty()) {
            supportFragmentManager.fragments.last()?.let { fragment ->
                if (fragment is BaseFragment<*, *, *> && fragment.allowChildNavigation && fragment.childFragmentManager.backStackEntryCount > 0) {
                    fragment.childFragmentManager.popBackStack()
                } else {
                    super.onBackPressed()
                }
            } ?: run {
                super.onBackPressed()
            }

        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val VIEW_STATE = "VIEW_STATE"
    }
}