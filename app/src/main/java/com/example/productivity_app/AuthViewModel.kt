package com.example.productivity_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<Authstate>()
    val authState: LiveData<Authstate> = _authState

    init {
        checkAuthStatus()
    }
    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value =Authstate.Unauthenticated
        }else{
            _authState.value =Authstate.Authanticated
        }
    }

    fun login(email:String , password:String){

        if(email.isEmpty()||password.isEmpty()){
            _authState.value = Authstate.Error("Email or password can't be empty")
            return
        }

        _authState.value = Authstate.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    _authState.value = Authstate.Authanticated
                }else{
                    _authState.value = Authstate.Error(task.exception?.message?:"Something Went Wrong")
                }
            }
    }

    fun signup(email:String , password:String){

        if(email.isEmpty()||password.isEmpty()){
            _authState.value = Authstate.Error("Email or password can't be empty")
            return
        }

        _authState.value = Authstate.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    _authState.value = Authstate.Authanticated
                }else{
                    _authState.value = Authstate.Error(task.exception?.message?:"Something Went Wrong")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = Authstate.Unauthenticated
        }





}

sealed class  Authstate{
    object Authanticated : Authstate()
    object Unauthenticated : Authstate()
    object Loading : Authstate()

    data class Error(val message : String):Authstate()
}