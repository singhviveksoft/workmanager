package com.example.workmanager.utils

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChainingOne(context: Context,params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        try {

            for (i in 0 ..1000) {
                Log.i("TAG", "ChainingOne $i")
            }
            return Result.success()

        }

        catch (e:Exception){
            return Result.failure()
        }
    }
}