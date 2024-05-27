package com.bemos.matuleshoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.data.email.MainActivityViewModel
import com.bemos.matuleshoes.data.id_manager.IdManager
import com.bemos.matuleshoes.databinding.ActivityMainBinding
import com.bemos.matuleshoes.screen.category.vm.CategoryViewModel
import com.bemos.matuleshoes.screen.favorite.FavoriteFragment
import com.bemos.matuleshoes.screen.home.HomeFragment
import com.bemos.matuleshoes.screen.notification.NotificationFragment
import com.bemos.matuleshoes.screen.profile_screen.profile.ProfileFragment
import com.bemos.matuleshoes.screen.splash.SplashFragment
import com.bemos.matuleshoes.screen.user.sign_up.SignUpFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var categoryViewModel: CategoryViewModel

    val mainActivityViewModel by lazy {
        val emailManager = EmailManager(this)
        val idManager = IdManager(this)

        MainActivityViewModel(emailManager, idManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.home_bn -> supportFragmentManager.beginTransaction().replace(R.id.frame_container, HomeFragment()).commit()
                R.id.favorite_bn -> supportFragmentManager.beginTransaction().replace(R.id.frame_container, FavoriteFragment()).commit()
                R.id.notification_bn -> supportFragmentManager.beginTransaction().replace(R.id.frame_container, NotificationFragment()).commit()
                R.id.profile_bn -> supportFragmentManager.beginTransaction().replace(R.id.frame_container, ProfileFragment()).commit()
            }
            true
        }

        binding.bottomNavCard.setOnClickListener {

        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, SignUpFragment())
            .commit()
    }
}