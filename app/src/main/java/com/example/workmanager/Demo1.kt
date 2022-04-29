package com.example.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
// worker class and doWork run on background thread
class Demo1(val context: Context,workparam:WorkerParameters):Worker(context,workparam) {
/*
    override fun doWork(): Result {
        try {
            //receiving count from demo1activity
        var count=    inputData.getInt("count",0)
            for (i in 0 ..count) {
              //  Toast.makeText(context,"$i",Toast.LENGTH.)
                Log.i("doWork", "$i")
            }
            return Result.success()
        }catch (e: Exception) {
            return Result.failure()
        }
    }
*/

    override fun doWork(): Result {
        try {
            //sending data to demo1activity
           var data:Data=Data.Builder()
               .putInt("count_value",1550)
               .build()
            return Result.success(data)
        }catch (e: Exception) {
            return Result.failure()
        }
    }

}