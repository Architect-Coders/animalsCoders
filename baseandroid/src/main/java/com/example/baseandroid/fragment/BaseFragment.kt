package com.example.baseandroid.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.baseandroid.viewmodel.BaseViewModel

abstract class BaseFragment<
        ViewState : Parcelable,
        ViewTransition,
        ViewModel : BaseViewModel<ViewState, ViewTransition>>(
    val allowChildNavigation: Boolean
) : Fragment() {

    protected abstract val viewModel: ViewModel

    private var alertDialog: AlertDialog? = null

    private var viewCreated = false
    protected var isRecreated = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    protected abstract fun getLayout(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        if (savedInstanceState != null) {
            viewCreated = true
            isRecreated = true

            savedInstanceState.getParcelable<ViewState>(VIEW_STATE)?.let { viewState ->
                viewModel.setViewState(viewState)
            }
        } else {
            isRecreated = if (viewCreated) {
                true
            } else {
                viewCreated = true
                false
            }

            viewModel.init()
        }

        viewModel.initServices()
        initListeners()
        initObservers()
    }

    abstract fun initView()

    private fun initObservers() {
        viewModel.getViewState().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                manageState(state = it)
            }
        })

        viewModel.getViewTransition().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                manageTransition(transition = it)
            }
        })
    }

    protected abstract fun manageState(state: ViewState)

    protected abstract fun manageTransition(transition: ViewTransition)

    protected abstract fun initListeners()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(VIEW_STATE, viewModel.getViewState().value)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        clearObservers()
    }

    private fun clearObservers() {
        viewModel.getViewState().removeObservers(viewLifecycleOwner)
        viewModel.getViewTransition().removeObservers(viewLifecycleOwner)
    }

    companion object {
        private const val VIEW_STATE = "VIEW_STATE"
    }
}