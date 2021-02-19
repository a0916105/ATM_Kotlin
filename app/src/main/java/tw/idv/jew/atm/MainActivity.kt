package tw.idv.jew.atm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    var login = false

    companion object {
        val RC_LOGIN = 30
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!login){
            Intent(this, LoginActivity::class.java).apply {
//                startActivity(this)
                startActivityForResult(this, RC_LOGIN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_LOGIN) {
            if (resultCode == RESULT_OK) {
                val userid = data?.getStringExtra("LOGIN_USERID")
                val passwd = data?.getStringExtra("LOGIN_PASSWD")
                Log.d("RESULT", "$userid / $passwd")
            }
        }
    }
}