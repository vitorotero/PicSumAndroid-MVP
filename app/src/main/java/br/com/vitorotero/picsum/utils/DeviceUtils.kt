package br.com.vitorotero.picsum.utils

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class DeviceUtils {

    companion object {

        fun openBrowserUrl(context: Context, url: String) {
            try {
                val i = Intent("android.intent.action.MAIN")
                i.component = ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main")
                i.addCategory("android.intent.category.LAUNCHER")
                i.data = Uri.parse(url)
                context.startActivity(i)
            } catch (e: ActivityNotFoundException) {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(i)
            }
        }

        fun showShortToas(context: Context, message: String) {
            val toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.setText(message)
            toast.show()
        }

    }

}