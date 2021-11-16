package uz.gita.contactswithroom.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ContactDao :BaseDao<ContactData>{
    @Query("SELECT * FROM ContactData")
    fun getAll(): List<ContactData>
    @Query("DElete From ContactData where userId=:id")
    fun deleteAllByUserId(id:Long)
    @Query("SELECT * From ContactData where userId=:userId")
    fun getContactsByUserId(userId: Long?):List<ContactData>


}