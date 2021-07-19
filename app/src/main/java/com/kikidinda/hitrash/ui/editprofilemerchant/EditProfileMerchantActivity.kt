package com.kikidinda.hitrash.ui.editprofilemerchant

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.utils.Alert
import kotlinx.android.synthetic.main.activity_edit_profile_merchant.*

class EditProfileMerchantActivity : AppCompatActivity() {
    val viewModel: EditProfileMerchantViewModel by viewModels()
    lateinit var alert: SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_merchant)
        alert = SweetAlertDialog(this)
        viewModel.getMerchant(this)
        btnEditImage.setOnClickListener {
            ImagePicker.with(this)
                .crop(
                    4f,
                    3f
                )                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }


        setObserver()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        btnUpdateProfile.setOnClickListener {
            val merchantName = tilMerchantName.editText?.text
            val merchantType = tilMerchantType.editText?.text
            val merchantGoods = tilGoods.editText?.text

            if (merchantName?.isNotEmpty() == true && merchantType?.isNotEmpty() == true && merchantGoods?.isNotEmpty() == true) {
                viewModel.updateMerchant(merchantName.toString(), merchantType.toString(),merchantGoods.toString(), this)
            } else {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setObserver() {
        viewModel.isLoading.observe(this, Observer {
            alert = if (it) {
                Alert.loading(alert)
            } else {
                Alert.hide(alert)
            }
        })

        viewModel.merchantObservable.observe(this, Observer {
            if (it != null) {
                tilMerchantName.editText?.setText(it.merchantName)
                tilMerchantType.editText?.setText(it.merchantType)
                tilGoods.editText?.setText(it.merchantGoods)
                ivMerchant.load(it.img)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val uri: Uri = data?.data!!

                viewModel.uploadMerchantImage(uri, this)
                // Use Uri object instead of File to avoid storage permissions
                ivMerchant.setImageURI(uri)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}