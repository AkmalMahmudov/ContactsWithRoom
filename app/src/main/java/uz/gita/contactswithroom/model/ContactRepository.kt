package uz.gita.contactswithroom.model

import uz.gita.contactswithroom.app.App
import uz.gita.contactswithroom.contract.ContactContract
import uz.gita.contactswithroom.room.AppDatabase
import uz.gita.contactswithroom.room.ContactData

class ContactRepository() : ContactContract.Model {
    private val database= AppDatabase.getDatabase(App.instance)
    private var contactDao = database.contactDao()
    override fun getContactByUserId(userId: Long?): List<ContactData> {
        return contactDao.getContactsByUserId(userId)
    }

    override fun insertContact(contactData: ContactData, userId: Long?) {
        contactData.userId= userId!!
        val id=contactDao.insert(contactData)
        contactData.id=id
    }

    override fun updateContact(contactData: ContactData) {
       contactDao.update(contactData)
    }

    override fun deleteContact(contactData: ContactData) {
      contactDao.delete(contactData)
    }

}