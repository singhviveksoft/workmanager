package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.workmanager.databinding.ActivityMain2Binding

class Demo1Activity : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        oneTimeWorkreq()
    }

/*
    fun oneTimeWorkreq(){
        //setting up  WorkManager
     val workManager=   WorkManager.getInstance(applicationContext)
        //setting up Work request
        var oneTimeWorkreq =OneTimeWorkRequest.Builder(Demo1::class.java)
            .build()
        workManager.enqueue(oneTimeWorkreq)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkreq.id).observe(this){
        binding.textView.text=it.state.name
         //   Toast.makeText(this, "$it.state.name", Toast.LENGTH_SHORT).show()
        }

    }

*/
// sending data to worker class in this function using DAta.builder
/*
    fun oneTimeWorkreq(){
    //data
    var data:Data=Data.Builder()
        .putInt("count",1000)
        .build()



        //setting up  WorkManager
        val workManager=   WorkManager.getInstance(applicationContext)
        //setting up Work request
        var oneTimeWorkreq =OneTimeWorkRequest.Builder(Demo1::class.java)
            .setInputData(data)         // setting data of type in worker request
            .build()

     //workManager

        workManager.enqueue(oneTimeWorkreq)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkreq.id).observe(this){
            binding.textView.text=it.state.name
            //   Toast.makeText(this, "$it.state.name", Toast.LENGTH_SHORT).show()
        }

    }
*/


    //receving data from Demo1 class
    fun oneTimeWorkreq(){




        //setting up  WorkManager
        val workManager=   WorkManager.getInstance(applicationContext)
        //setting up Work request
        var oneTimeWorkreq =OneTimeWorkRequest.Builder(Demo1::class.java)

            .build()

        //workManager

        workManager.enqueue(oneTimeWorkreq)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkreq.id).observe(this){
            if (it.state.isFinished)
            binding.textView.text=it.outputData.getInt("count_value",0).toString() // data receving
            //   Toast.makeText(this, "$it.state.name", Toast.LENGTH_SHORT).show()
        }

    }

}