package com.example.baseandroid.activity

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.baseandroid.viewmodel.BaseViewModel

abstract class BaseActivity<ViewState : Parcelable, ViewTransition, ViewModel : BaseViewModel<ViewState, ViewTransition>> :
    AppCompatActivity() {

    protected lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getActivityViewModel()

        setContentView(getLayout())

        initView()

        initObservers()
        initListeners()
    }

    abstract fun getActivityViewModel(): ViewModel

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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getParcelable<ViewState>(VIEW_STATE)?.let { viewState ->
            viewModel.setViewState(viewState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        clearObservers()
    }

    private fun clearObservers() {
        viewModel.getViewState().removeObservers(this)
        viewModel.getViewTransition().removeObservers(this)
    }

    companion object {
        private const val VIEW_STATE = "VIEW_STATE"
    }
}