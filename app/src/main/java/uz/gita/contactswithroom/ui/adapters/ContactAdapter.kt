package uz.gita.contactswithroom.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactswithroom.R
import uz.gita.contactswithroom.databinding.ItemTodoBinding
import uz.gita.contactswithroom.util.SingleBlock
import uz.gita.contactswithroom.util.bindItem
import uz.gita.contactswithroom.room.ContactData

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private lateinit var binding:ItemTodoBinding
    private val ls = ArrayList<ContactData>()
    private var listenerItem: SingleBlock<ContactData>? = null
    private var listenerEdit: SingleBlock<ContactData>? = null
    private var listenerDelete: SingleBlock<ContactData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        binding= ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }
    override fun getItemCount(): Int = ls.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun submitList(data: List<ContactData>) {
        ls.clear()
        ls.addAll(data)
        notifyItemRangeRemoved(0, data.size)
    }

    fun removeItem(data: ContactData){
        val index = ls.indexOfFirst { it.id == data.id }
        ls.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(data: ContactData){
        val index = ls.indexOfFirst { it.id == data.id }
        ls[index] = data
        notifyItemChanged(index)
    }

    fun insertItem(data: ContactData){
        ls.add(data)
        notifyItemInserted(ls.size - 1)
    }

    fun setOnItemEditListener(block: SingleBlock<ContactData>) {
        listenerEdit = block
    }

    fun setOnItemDeleteListener(block: SingleBlock<ContactData>) {
        listenerDelete = block
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        init{
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(ls[adapterPosition]) }
               binding.buttonMore.setOnClickListener { it ->
                   val menu = PopupMenu(context,it)
                    menu.inflate(R.menu.menu_more)
                    menu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.menuDelete -> listenerDelete?.invoke(ls[adapterPosition])
                            R.id.menuEdit -> listenerEdit?.invoke(ls[adapterPosition])
                        }
                        true
                    }
                    menu.show()
                }
            }
        }

        fun bind() = bindItem {
            val d = ls[adapterPosition]
            binding.Name.text = d.name
           binding.Number.text=d.number
        }
    }
}