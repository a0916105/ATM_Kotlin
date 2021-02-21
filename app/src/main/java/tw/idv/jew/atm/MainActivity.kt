package tw.idv.jew.atm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    var login = false

    companion object {
        val RC_LOGIN = 30
        val REQUEST_CAMERA = 50
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_contacts -> {
                startActivity(Intent(this, MaterialActivity::class.java))
            }
            R.id.action_camera -> {
                val camera = Intent(this, CameraActivity::class.java)
                startActivityForResult(camera, REQUEST_CAMERA)
            }
            R.id.action_expense -> {
                val exp = Intent(this, ExpenseActivity::class.java)
                startActivity(exp)
            }
            R.id.action_help -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}