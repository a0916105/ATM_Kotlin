package tw.idv.jew.atm

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), ServiceConnection {
    val TAG = ChatActivity::class.java.simpleName
    var chatService: ChatService ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val intent = Intent(this, ChatService::class.java)
        bindService(intent, this, Context.BIND_AUTO_CREATE)

        buttonSend.setOnClickListener { chatService?.sendMessage("Testing") }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(TAG, "onServiceConnected")
        val binder = service as ChatService.ChatBinder
        chatService = binder.getService()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        chatService = null
        Log.d(TAG, "onServiceDisconnected")
    }

    override fun onStop() {
        super.onStop()
        unbindService(this)
    }
}