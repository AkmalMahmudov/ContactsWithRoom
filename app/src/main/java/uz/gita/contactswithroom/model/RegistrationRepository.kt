package uz.gita.contactswithroom.model


import uz.gita.contactswithroom.app.App
import uz.gita.contactswithroom.contract.RegistrationContract
import uz.gita.contactswithroom.room.AppDatabase
import uz.gita.contactswithroom.room.UserData

class RegistrationRepository : RegistrationContract.Model{
    private var userDao = AppDatabase.getDatabase(App.instance).userDao()
    override fun getUser(): List<UserData> {
        return userDao.getAll()
    }



    override fun insertUser(userData: UserData) {
        userDao.insert(userData)
    }
}