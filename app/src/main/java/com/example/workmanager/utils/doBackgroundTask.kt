package com.example.workmanager.utils

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmanager.view.MainFragment
import java.text.SimpleDateFormat
import java.util.*

class doBackgroundTask(context: Context,params:WorkerParameters):Worker(context,params) {

    companion object{
        const val DATA_KEY_WORKER="DATA"
    }

    override fun doWork(): Result {
        try {
            val count=inputData.getInt(MainFragment.DATA_KEY,0)  //
            for (i in 0..count){
                Log.d("TAG",i.toString())
                Log.i("myThread","${Thread.currentThread().name}")
            }


            val date=SimpleDateFormat("dd/MM/yy hh:mm:ss")
            val currentData=date.format(Date())

          //creating data object to send o/p to main activity
            val data=Data.Builder()
                .putString(DATA_KEY_WORKER,currentData)
                .build()
            return Result.success(data)

        }catch (ex:Exception){
            return Result.failure()
        }

    }
}