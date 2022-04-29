package com.example.workmanager.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanager.databinding.FragmentMainBinding
import com.example.workmanager.utils.ChainingOne
import com.example.workmanager.utils.ChainingTwo
import com.example.workmanager.utils.doBackgroundTask
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {


    companion object{
        const val DATA_KEY="data"
    }

    private lateinit var mainBinding: FragmentMainBinding
private lateinit var workManager: WorkManager





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainBinding=FragmentMainBinding.inflate(inflater, container, false)
        workManager= WorkManager.getInstance(requireContext())
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainBinding.button.setOnClickListener {
            oneTimeWorkRequestTask()
            Log.i("myMainThread","${Thread.currentThread().name}")

        }


    }

    fun oneTimeWorkRequestTask(){
        val constraint=Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

//creating data object to send i/p to worker class

        val data:Data=Data.Builder()
            .putInt(DATA_KEY,100000000)
            .build()

        // 1) work request
        val oneTimeWorkRequest= OneTimeWorkRequest.Builder(doBackgroundTask::class.java)
          //  .setInitialDelay(1,TimeUnit.MINUTES)
           // .addTag("task1")
          //  .setConstraints(constraint)
            .setInputData(data)
            .build()

        // 2) work request

        val chain1=OneTimeWorkRequest.Builder(ChainingOne::class.java)
            .build()

        // 3) work request
         val chain2=OneTimeWorkRequest.Builder(ChainingTwo::class.java)
             .build()

      //work manger

        workManager.enqueue(oneTimeWorkRequest)   // enqueing a single  request in background.

        // performing chaining of work request i.e sequence and parallel

        //1) in sequence chaining work request

   //     workManager.beginWith(chain1).then(chain2).then(oneTimeWorkRequest).enqueue()   //enqueing in sequence wise.

        // 2) in parallel chaining of work request we have to create a mutable list and than add worker request to it.
      /*  val parallel= mutableListOf<OneTimeWorkRequest>()
      //  parallel.add(oneTimeWorkRequest)
        parallel.add(chain1)
        parallel.add(chain2)

        workManager.beginWith(parallel).enqueue()*/


     // getting the current state using live data
/*
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(requireActivity(), Observer {
                mainBinding.textView.text=it.state.name   // its give as the the current result state.

                //we cannot get the o/p of data object until task is complete
                if (it.state.isFinished){    //getting data once the task is sompleted
                    val result=it.outputData.getString(doBackgroundTask.DATA_KEY_WORKER)
                    Toast.makeText(requireContext(),result,Toast.LENGTH_LONG).show()
                }
            })
*/


    }
}