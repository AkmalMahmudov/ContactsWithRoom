package uz.gita.contactswithroom.ui.screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactswithroom.R
import uz.gita.contactswithroom.contract.ContactContract
import uz.gita.contactswithroom.databinding.ScreenUserBinding
import uz.gita.contactswithroom.model.ContactRepository
import uz.gita.contactswithroom.room.ContactData
import uz.gita.contactswithroom.ui.adapters.ContactAdapter
import uz.gita.contactswithroom.ui.dialogs.ContactDialog
import uz.gita.contactswithroom.presenter.ContactPresenter

class UserFragment : Fragment(R.layout.screen_user), ContactContract.View {
    private val userId: Long by lazy {
        val arguments: Bundle? = arguments
        arguments!!.getLong("id")
    }
    private lateinit var presenter: ContactPresenter
    private val adapter = ContactAdapter()
    private val binding by viewBinding(ScreenUserBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = ContactPresenter(ContactRepository(), this, userId)
        binding.list.layoutManager = LinearLayoutManager(activity)
        binding.list.adapter = adapter
        adapter.setOnItemEditListener(presenter::editContact)
        adapter.setOnItemDeleteListener(presenter::deleteContact)
        binding.btnAdd.setOnClickListener { presenter.openAddContact() }
        requireActivity().title = "UserContact"

    }

    override fun loadData(data: List<ContactData>) {
        adapter.submitList(data)
    }

    override fun openEditDialog(contactData: ContactData) {
        val dialog = activity?.let { ContactDialog(it, "Edit") }
        dialog?.setContactData(contactData)
        dialog?.setOnClickListener(presenter::confirmEditContact)
        dialog?.show()
    }

    override fun openInsertDialog() {
        val dialog = activity?.let { ContactDialog(it, "Add") }
        dialog?.setOnClickListener(presenter::createContact)
        dialog?.show()
    }

    override fun deleteItem(contactData: ContactData) {
        adapter.removeItem(contactData)
    }

    override fun updateItem(contactData: ContactData) {
        adapter.updateItem(contactData)
    }

    override fun insertItem(contactData: ContactData) {
        adapter.insertItem(contactData)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> presenter.openAddContact()
            R.id.menuBack -> activity?.finish()
        }
        return true
    }
}