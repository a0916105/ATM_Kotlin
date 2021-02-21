package tw.idv.jew.atm

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {
    val PERMISSION_CAMER = 300
    private var imageUri: Uri? = null
    private val REQUEST_CAPTURE = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if (ActivityCompat.checkSelfPermission(this, CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE),
                    PERMISSION_CAMER
            )
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE , "My Picture")
            put(MediaStore.Images.Media.DESCRIPTION , "Testing")
        }
        imageUri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        camera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(camera, REQUEST_CAPTURE)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CAMER) {
            if (grantResults.size > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAPTURE) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(imageUri)
            }
        }
    }
}