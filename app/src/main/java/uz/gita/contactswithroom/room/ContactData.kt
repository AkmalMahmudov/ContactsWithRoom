package uz.gita.contactswithroom.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var userId:Long=0,
    var name: String = "",
    var number: String = ""
)
