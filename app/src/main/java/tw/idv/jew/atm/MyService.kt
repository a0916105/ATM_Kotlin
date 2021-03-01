package tw.idv.jew.atm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {
    companion object {
        val TAG = MyService::class.java.simpleName
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?,
                                flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        Log.d(TAG, "下載檔案中...")
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            Log.d(TAG, "下載完成")
        }
        Log.d(TAG, "onStartCommand 即將結束")
        return START_NOT_STICKY
    }
}