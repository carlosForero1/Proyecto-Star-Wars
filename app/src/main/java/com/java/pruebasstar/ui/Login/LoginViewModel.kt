package com.java.pruebasstar.ui.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var name by mutableStateOf("")
        private set

    var side by mutableStateOf("")
        private set

    fun onNameChanged(newValue: String) {
        name = newValue
    }

    fun onSideSelected(selected: String) {
        side = selected
    }

    fun canLogin(): Boolean {
        return name.isNotBlank() && side.isNotBlank()
    }
}
