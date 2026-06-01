package com.snapbizz.snapturbo.onboarding.userlogin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapbizz.snapturbo.onboarding.login.data.local.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val _loginSuccess =
        MutableStateFlow(false)

    val loginSuccess: StateFlow<Boolean> =
        _loginSuccess

    fun login(
        username: String,
        password: String
    ) {

        viewModelScope.launch {

            val user =
                userDao.login(
                    username,
                    password
                )

            _loginSuccess.value =
                user != null
        }
    }
}
