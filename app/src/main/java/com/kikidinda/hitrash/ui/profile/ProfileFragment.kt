package com.kikidinda.hitrash.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.ui.editprofilemerchant.EditProfileMerchantActivity
import com.kikidinda.hitrash.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {


    val viewModel : ProfileViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile(requireContext())

        viewModel.profileBroadcaster().observe(viewLifecycleOwner, Observer {
            tvName.text = it.name
            tvEmail.text = it.email

            if(it.warung){
                wrapperBtnEditMerchant.visibility = View.VISIBLE
            } else {
                wrapperBtnEditMerchant.visibility = View.GONE

            }
        })


        btnLogout.setOnClickListener {
            LocalStorage.signOut(requireActivity())
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finishAffinity()
        }

        btnUpdateProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileMerchantActivity::class.java))
        }
    }

}