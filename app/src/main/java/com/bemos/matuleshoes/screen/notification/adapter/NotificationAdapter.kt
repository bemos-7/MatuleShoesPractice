package com.bemos.matuleshoes.screen.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bemos.matuleshoes.databinding.ListitemBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

//class NotificationAdapter(
//    val list: List<String> = listOf()
//) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
//
//    var onItemClick = ((Notificaton) -> Unit)? = null
//
//    class ViewHolder(private val binding: ListitemBinding): RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: String) {
//            binding.title.text = item
//            binding.desc.text = item
//            binding.time.text = item
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        val binding = ListitemBinding.inflate(inflater)
//
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = list[position]
//
//        holder.bind(item)
//        holder.binding.title.setOnClickListener {
//          onItemClick?.invoke(item)
//        }
//    }
//
//}

class NotificationAdapter {

    private val notificationDelegate = adapterDelegateViewBinding<Notification, Notification, ListitemBinding>(
        viewBinding = { layoutInflater, parent ->  
            ListitemBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.title.text = item.title
            binding.desc.text = item.description
            binding.time.text = item.time
        }

    }

    val adapter = ListDelegationAdapter(notificationDelegate)

    fun updateList(items: List<Notification>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}

data class Notification(
    val title: String,
    val description: String,
    val time: String
)