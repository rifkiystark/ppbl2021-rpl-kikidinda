package com.kikidinda.hitrash.ui.deposittrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.User
import kotlinx.android.synthetic.main.fragment_home_admin.*

class DepositTrashFragment : Fragment() {

    val viewModel: DepositTrashViewModel by viewModels()

    var users: List<User> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userObserver().observe(viewLifecycleOwner, Observer {
            setDataUser(it)
        })

        btnAdd.setOnClickListener {
            var user: User? = null
            try {
                user = users.first {
                    it.email == spnUser.text.toString()
                }
            } catch (e: Exception) {

            }
            if (user != null) {
                viewModel.addPoin(user, etPoin.text.toString().toInt())
                Toast.makeText(requireContext(), "Poin ${user.name} bertambah ${etPoin.text}", Toast.LENGTH_SHORT).show()
                etPoin.setText("0")
            } else {
                Toast.makeText(requireContext(), "Email belum terdaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDataUser(it: List<User>) {
        users = it
        val provinsiArray = Array(it.size) { i ->
            it[i].email
        }
        spnUser.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                provinsiArray
            )
        )
    }


}