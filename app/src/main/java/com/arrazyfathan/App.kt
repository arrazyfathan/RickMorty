package com.arrazyfathan // ktlint-disable filename

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.arrazyfathan.rickmorty.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RickMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            setupCustomCrash()
            val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(1)
                .methodOffset(5)
                .tag("")
                .build()

            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, "-$$tag", message, t)
                }

                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "%s:%s",
                        element.methodName,
                        element.lineNumber,
                        super.createStackElementTag(element)
                    )
                }
            })

            Timber.d("App Created!")
        }
    }

    private fun setupCustomCrash() {
        CaocConfig.Builder.create().apply()
    }
}
