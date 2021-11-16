package uz.gita.contactswithroom.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao : BaseDao<UserData> {
    @Query("SELECT * FROM UserData")
    fun getAll(): List<UserData>
}