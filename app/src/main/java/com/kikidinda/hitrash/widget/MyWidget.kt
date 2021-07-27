package com.kikidinda.hitrash.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.google.firebase.firestore.FirebaseFirestore
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import com.kikidinda.hitrash.repository.local.LocalStorage
import com.kikidinda.hitrash.utils.CONST

/**
 * Implementation of App Widget functionality.
 */
class MyWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.my_widget)
    // views.setTextViewText(R.id.appwidget_text, widgetText)

    val user = LocalStorage.getUser(context)
    FirebaseFirestore.getInstance().collection(CONST.FIRESTORE.USER).document(user.id!!).addSnapshotListener { value, _ ->
        if (value != null) {
            if (value.exists()){
                val result = value.toObject(User::class.java)
                views.setTextViewText(R.id.tvPoin, "Total Poin : ${result?.poin}")
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }
    }
    appWidgetManager.updateAppWidget(appWidgetId, views)

    // Instruct the widget manager to update the widget
}