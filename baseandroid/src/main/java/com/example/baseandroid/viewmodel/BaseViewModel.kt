package com.example.baseandroid.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.livedata.SingleLiveEvent
import kotlinx.coroutines.*

abstract class BaseViewModel<State : Parcelable, Transition>(private val dispatchers: CoroutineDispatchers) :
    ViewModel() {
    protected val viewState: MutableLiveData<State> = MutableLiveData()
    protected val viewTransition: SingleLiveEvent<Transition> = SingleLiveEvent()

    fun getViewState(): LiveData<State> = viewState
    fun getViewTransition(): LiveData<Transition> = viewTransition

    fun setViewTransition(transition: Transition) {
        viewTransition.value = transition
    }

    fun setViewState(state: State) {
        viewState.value = state
    }

    fun executeBackground(delay: Long = 0, param: suspend () -> Unit): Job {
        return GlobalScope.launch(dispatchers.defaultDispatcher) {
            if (delay > 0) delay(delay)
            param()
        }
    }

    suspend fun <T> executeBackgroundAsync(delay: Long = 0, param: suspend () -> T): Deferred<T> {
        return GlobalScope.async(dispatchers.defaultDispatcher) {
            if (delay > 0) delay(delay)
            param()
        }
    }

    fun executeUI(delay: Long = 0, param: () -> Unit) {
        GlobalScope.launch(dispatchers.uiDispatcher) {
            if (delay > 0) delay(delay)
            param()
        }
    }
}