package com.oscar.gamermvvmapp.presentation.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

class Vibrate {

    companion object {

        @RequiresApi(Build.VERSION_CODES.Q)
        fun vibrar(context: Context) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Para versiones de Android 26 y superiores
                val vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
                vibrator.vibrate(vibrationEffect)
            } else {
                // Para versiones anteriores a Android 26
                // Puedes utilizar un patrón corto de vibración
                vibrator.vibrate(50)
            }
        }

    }

}