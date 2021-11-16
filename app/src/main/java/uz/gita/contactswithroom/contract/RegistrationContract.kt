package uz.gita.contactswithroom.contract

import uz.gita.contactswithroom.room.UserData

interface RegistrationContract {

    interface Model{
        fun getUser():List<UserData>
        fun insertUser(userData: UserData)

    }

    interface View{
        fun showMessage(message:String)
        fun moveLogIn()
        fun getLogin():String
        fun getPassword():String
        fun getConfirmPassword():String
    }

    interface Presenter{
        fun clickReg()
    }
}