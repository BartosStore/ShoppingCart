package com.miba.shoppingcart

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 * Created by miba on 19.09.2017.
 */
class KApp : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        const val LOG_MIBA = "MIBA"
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        Log.d(LOG_MIBA, "KApp created!")
    }

    override fun onActivityStarted(p0: Activity?) {
        Log.d(LOG_MIBA, "Application -> onActivityStarted: " + p0)
    }

    override fun onActivityStopped(p0: Activity?) {
        Log.d(LOG_MIBA, "Application -> onActivityStopped: " + p0)
    }

    override fun onActivityResumed(p0: Activity?) {
        Log.d(LOG_MIBA, "Application -> onActivityResumed: " + p0)
    }

    override fun onActivityPaused(p0: Activity?) {
        Log.d(LOG_MIBA, "Application -> onActivityPaused: " + p0)
    }

    override fun onActivityDestroyed(p0: Activity?) {
        Log.d(LOG_MIBA, "Application -> onActivityDestroyed: " + p0)
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        Log.d(LOG_MIBA, "Application -> onActivityCreated: " + p0)
    }
}