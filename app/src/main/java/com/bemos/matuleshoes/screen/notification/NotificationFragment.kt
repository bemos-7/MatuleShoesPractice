package com.bemos.matuleshoes.screen.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.databinding.NotificationFragmentBinding
import com.bemos.matuleshoes.screen.notification.adapter.Notification
import com.bemos.matuleshoes.screen.notification.adapter.NotificationAdapter

class NotificationFragment: Fragment(R.layout.notification_fragment) {

    private val binding: NotificationFragmentBinding by viewBinding()

    val adapter = NotificationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            rcvItems.adapter = adapter.adapter

            adapter.updateList(listOf(
                Notification(
                    "hi",
                    "hi",
                    "hi"
                ),
                Notification(
                    "hi",
                    "hi",
                    "hi"
                )
            ))

        }
    }

}