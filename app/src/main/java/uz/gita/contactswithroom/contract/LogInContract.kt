package uz.gita.contactswithroom.contract

import uz.gita.contactswithroom.room.UserData


interface LogInContract{
    interface Model{
        fun getUsers():List<UserData>
    }


    interface View{
        fun showMessage(message:String)
        fun moveRegistrationScreen()
        fun moveUserScreen(id:Long)
        fun getLogin():String
        fun getPassword():String

    }


    interface Presenter{
        fun clickRegistr()
        fun clickLogIn()
    }

}