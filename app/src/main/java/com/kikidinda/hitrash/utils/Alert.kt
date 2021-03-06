package com.kikidinda.hitrash.utils

import cn.pedant.SweetAlert.SweetAlertDialog

object Alert {
    fun loading(alert: SweetAlertDialog): SweetAlertDialog {
        alert.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        alert.setCancelable(false)
        alert.titleText = "Loading"
        alert.showContentText(false)
        alert.show()

        return alert
    }

    fun fail(alert: SweetAlertDialog, message: String = ""): SweetAlertDialog {
        alert.changeAlertType(SweetAlertDialog.ERROR_TYPE)
        alert.titleText = "Gagal"
        alert.contentText = message
        alert.showContentText(true)
        alert.confirmText = "Ok"
        alert.setConfirmClickListener {
            it.hide()
        }
        alert.show()

        return alert
    }

    fun success(alert: SweetAlertDialog, message: String = "", callback: (Boolean) -> Unit): SweetAlertDialog {
        alert.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
        alert.titleText = "Berhasil"
        alert.contentText = message
        alert.showContentText(true)
        alert.setConfirmClickListener {
            it.hide()
        }
        alert.show()

        return alert
    }

    fun popup(alert: SweetAlertDialog, callback:(Boolean) -> Unit) : SweetAlertDialog{
        alert.changeAlertType(SweetAlertDialog.WARNING_TYPE)
        alert.titleText = "Perhatian"
        alert.contentText = "Apakah ingin diubah menjadi warung?"
        alert.showContentText(true)
        alert.cancelText = "Tidak"
        alert.confirmText = "Ya"
        alert.showCancelButton(true)
        alert.setCancelable(false)
        alert.setConfirmClickListener { callback(true) }
        alert.setCancelClickListener { callback(false) }
        alert.show()

        return alert
    }

    fun hide(alert: SweetAlertDialog): SweetAlertDialog {

        alert.hide()

        return alert
    }
}