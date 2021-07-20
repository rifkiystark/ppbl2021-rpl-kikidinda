package com.kikidinda.hitrash.repository.retrofit

import com.kikidinda.hitrash.model.Notification
import com.kikidinda.hitrash.model.NotificationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @Headers("Authorization: key=AAAA0oxCtWM:APA91bFRbHv_xpPM97ITW80hFNynOqq-D-hBPTBSLeGltldvmeXwehZKs0OzKK5Xh-QQV1sexA7MnsbcNSKzwCbr86u2Degp-Ba5Oih_28NwGSIG27Ig86Dejr4vmJd340WQJ2IFxaXi")
    @POST("fcm/send")
    fun send(@Body notification: Notification) : Call<NotificationResponse>
}
