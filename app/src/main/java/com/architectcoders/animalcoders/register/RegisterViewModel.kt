package com.architectcoders.animalcoders.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architectcoders.domain.interactors.RegisterInteractor
import com.architectcoders.domain.model.User
import kotlinx.coroutines.launch

/**
 *      animalsCoders.
 *
 *  @author -   AMarinaG
 *  @since  -   2019-09-20
 */
class RegisterViewModel(private val registerInteractor: RegisterInteractor) : ViewModel() {
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    fun validate(username: String, password: String) = viewModelScope.launch {
        _model.value = UiModel.Loading
        registerInteractor.invoke(username, password).fold({
            _model.value = UiModel.Error.UNKNOW
        }, {
            _model.value = UiModel.Content(it)
        })
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val user: User) : UiModel()
        sealed class Error {
            object UNKNOW : UiModel()
        }

    }
}
