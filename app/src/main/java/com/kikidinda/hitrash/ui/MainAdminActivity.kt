package com.kikidinda.hitrash.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.ui.homeadmin.HomeAdmin
import com.kikidinda.hitrash.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainAdminActivity : AppCompatActivity() {

    private val fragments = listOf(
        HomeAdmin(),
        ProfileFragment()
    )
    lateinit var activeFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)

        supportFragmentManager.beginTransaction()
            .add(R.id.wrapperContent, fragments[0])
            .add(R.id.wrapperContent, fragments[1]).hide(fragments[1])
            .commit()

        activeFragment = fragments[0]

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuHome -> {
                    showFragment(0)
                }
                R.id.menuProfile -> {
                    showFragment(1)
                }
                else -> {
                    false
                }
            }
        }
    }

    fun showFragment(index: Int) : Boolean{
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragments[index]).commit()
        activeFragment = fragments[index]
        return true
    }
}