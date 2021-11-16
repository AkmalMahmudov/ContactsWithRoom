package uz.gita.contactswithroom.contract

import uz.gita.contactswithroom.room.ContactData


interface ContactContract {
    interface Model {
        fun getContactByUserId(userId: Long?): List<ContactData>
        fun insertContact(contactData: ContactData, userId: Long?)
        fun updateContact(contactData: ContactData)
        fun deleteContact(contactData: ContactData)
    }

    interface View{
        fun loadData(data:List<ContactData>)
        fun openEditDialog(contactData: ContactData)
        fun openInsertDialog()
        fun deleteItem(contactData: ContactData)
        fun updateItem(contactData: ContactData)
        fun insertItem(contactData: ContactData)
    }

    interface Presenter{
        fun deleteContact(contactData: ContactData)
        fun confirmEditContact(contactData: ContactData)
        fun editContact(contactData: ContactData)
        fun createContact(contactData: ContactData)
        fun openAddContact()

    }
}