package com.secureguard.mdm

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PersistableBundle
import android.util.Log

/**
 * רכיב מערכת שמקבל אירועים הקשורים למנהל המכשיר.
 * הוא מופעל כאשר הרשאות הניהול ניתנות או נלקחות.
 */
class SecureGuardDeviceAdminReceiver : DeviceAdminReceiver() {
    private val TAG = "DeviceAdminReceiver"

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.d(TAG, "התקבלה הרשאת ניהול מכשיר!")
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Log.d(TAG, "Device admin disabled")
    }

    /**
     * פונקציה זו נקראת לאחר שהעברת הבעלות (transferOwnership) הושלמה בהצלחה.
     * כאן ניתן לבצע הגדרות ראשוניות של ה-Device Owner החדש.
     */
    override fun onTransferOwnershipComplete(context: Context, bundle: PersistableBundle?) {
        super.onTransferOwnershipComplete(context, bundle)
        Log.d(TAG, "העברת הבעלות הושלמה בהצלחה! האפליקציה היא כעת ה-Owner.")
        
        // כאן תוכל להוסיף לוגיקה שתופעל ברגע שהאפליקציה מקבלת בעלות, 
        // למשל להגדיר מחדש את מדיניות ה-Kiosk אם צריך.
    }

    companion object {
        /**
         * מספק דרך נוחה ומרכזית לקבל את ה-ComponentName של ה-Receiver.
         * @param context הקשר של האפליקציה.
         * @return ה-ComponentName של ה-Receiver.
         */
        fun getComponentName(context: Context): ComponentName {
            return ComponentName(context.applicationContext, SecureGuardDeviceAdminReceiver::class.java)
        }
    }
}
