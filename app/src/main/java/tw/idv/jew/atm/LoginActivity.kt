package tw.idv.jew.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        val userid = ed_userid.text.toString()
        val passwd = ed_passwd.text.toString()
        if (userid == "jack" && passwd == "1234") {
            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show()

            intent.putExtra("LOGIN_USERID", userid)
            intent.putExtra("LOGIN_PASSWD", passwd)
            setResult(RESULT_OK, intent)

            finish()
        } else {
            AlertDialog.Builder(this)
                .setTitle("ATM")
                .setMessage("登入失敗")
                .setPositiveButton("OK", null)
                .show()
        }

    }

    fun cancel(view: View) {

    }
}