package io.github.kabirnayeem99.minigallery.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


const val READ_EXTERNAL_STORAGE_REQ_CODE = 553

@AndroidEntryPoint
class HostActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { MinGalleryRoot() }
        requestReadExternalStoragePermission()
    }


    private fun requestReadExternalStoragePermission() {

        val permissionRequestBuilder = PermissionRequest.Builder(
            this,
            READ_EXTERNAL_STORAGE_REQ_CODE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        permissionRequestBuilder
            .setRationale("ASFSF")
            .setPositiveButtonText("Allow")
            .setNegativeButtonText("Cancel")

        val permissionRequest = permissionRequestBuilder.build()

        EasyPermissions.requestPermissions(permissionRequest)
    }


    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

