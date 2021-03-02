package tw.idv.jew.atm

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class MyWorker(appContext: Context, workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {
    companion object {
        val TAG = MyWorker::class.java.simpleName
    }

    override fun doWork(): Result {
        Log.d(TAG, "doWork: Do something")
        val sdf = SimpleDateFormat("HH:mm:ss")
        Log.d(TAG,"work: ${sdf.format(Date())}")
        return Result.success()
    }

}