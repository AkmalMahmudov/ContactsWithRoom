package uz.gita.contactswithroom.model

import uz.gita.contactswithroom.app.App
import uz.gita.contactswithroom.contract.LogInContract
import uz.gita.contactswithroom.room.AppDatabase
import uz.gita.contactswithroom.room.UserData

class LogInRepository : LogInContract.Model {
    private var userDao = AppDatabase.getDatabase(App.instance).userDao()
    override fun getUsers(): List<UserData> {
        return userDao.getAll()
    }

}