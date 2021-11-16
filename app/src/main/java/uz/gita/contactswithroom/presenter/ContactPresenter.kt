package uz.gita.contactswithroom.presenter

import android.os.Handler
import android.os.Looper
import uz.gita.contactswithroom.contract.ContactContract
import uz.gita.contactswithroom.room.ContactData
import java.util.concurrent.Executors

class ContactPresenter(
    private val model: ContactContract.Model,
    private val view: ContactContract.View, private val userId: Long
) : ContactContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())
    init {
        runOnWorkerThread {
        val ls = model.getContactByUserId(userId)
           runOnUIThread {
        view.loadData(ls)
           }
        }
    }

    override fun deleteContact(contactData: ContactData) {
        runOnWorkerThread {
        model.deleteContact(contactData)
            runOnUIThread {
        view.deleteItem(contactData)
            }
        }
    }

    override fun confirmEditContact(contactData: ContactData) {
        runOnWorkerThread {
        model.updateContact(contactData)
            runOnUIThread {
        view.updateItem(contactData)
            }
        }
    }

    override fun editContact(contactData: ContactData) {
        view.openEditDialog(contactData)
    }

    override fun createContact(contactData: ContactData) {
        runOnWorkerThread {
        model.insertContact(contactData, userId)
            runOnUIThread {
        view.insertItem(contactData)
            }
        }
    }

    override fun openAddContact() {
        view.openInsertDialog()
    }
    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}