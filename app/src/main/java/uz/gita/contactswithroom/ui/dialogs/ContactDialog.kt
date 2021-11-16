package uz.gita.contactswithroom.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import uz.gita.contactswithroom.databinding.ItemDialogBinding
import uz.gita.contactswithroom.util.SingleBlock
import uz.gita.contactswithroom.room.ContactData

class ContactDialog(context: Context, actionName:String) : AlertDialog(context) {
    private val binding= ItemDialogBinding.inflate(LayoutInflater.from(context), null,false)
    private var listener: SingleBlock<ContactData>? = null
    private var contactData: ContactData? = null

    init {
        setView(binding.root)
        setButton(BUTTON_POSITIVE, actionName) { _, _ ->
            val data = contactData ?: ContactData(0,0,"","")
            data.name=binding.inputName.text.toString()
            data.number=binding.inputNumber.text.toString()
            listener?.invoke(data)
        }
        setButton(BUTTON_NEGATIVE, "Cancel") { _, _ -> }
    }

    fun setContactData(contactDatas: ContactData) = with(binding.root) {
        this@ContactDialog.contactData = contactDatas
       binding.inputName.setText(contactDatas.name)
      binding.inputNumber.setText(contactDatas.number)
    }


    fun setOnClickListener(block: SingleBlock<ContactData>) {
        listener = block
    }
}