package com.github.hosseinzafari.touristo.base.system

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import timber.log.Timber

/*
* XComponentActivity is Base activity class
* we can put our custom code in here
* and use it entire created activities and make life easier
* */
abstract class XComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        Timber.i("Activity onCreated()")
    }

    override fun onStart() {
        super.onStart()

        Timber.i("Activity onStart()")
    }

    override fun onPause() {
        super.onPause()

        Timber.i("Activity onPause()")
    }

    override fun onRestart() {
        super.onRestart()

        Timber.i("Activity onRestart()")
    }

    override fun onResume() {
        super.onResume()


        Timber.i("Activity onResume()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.i("Activity onDestroy()")
    }


}