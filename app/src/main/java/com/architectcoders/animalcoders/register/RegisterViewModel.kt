package com.architectcoders.animalcoders.register

import androidx.lifecycle.viewModelScope
import com.architectcoders.domain.interactors.RegisterInteractor
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *      animalsCoders.
 *
 *  @author -   AMarinaG
 *  @since  -   2019-09-20
 */
class RegisterViewModel(
    private val interactor: RegisterInteractor,
    dispatchers: CoroutineDispatchers
) : BaseViewModel<RegisterViewState, RegisterViewTransition>(dispatchers = dispatchers) {

    private var serviceCall: Job? = null

    fun validate(username: String, password: String) = viewModelScope.launch {
        viewState.value = RegisterViewState.Loading
        serviceCall = executeBackground {
            interactor.invoke(username, password).fold({
                executeUI {
                    viewState.value = RegisterViewState.Error
                }
            }, {
                executeUI {
                    viewTransition.value = RegisterViewTransition.NavigateToHome
                }
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        serviceCall?.cancel()
    }

}
