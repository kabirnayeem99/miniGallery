package io.github.kabirnayeem99.minigallery.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.kabirnayeem99.minigallery.ui.folders.FolderListScreen
import io.github.kabirnayeem99.minigallery.ui.folders.FolderViewModel
import io.github.kabirnayeem99.minigallery.ui.theme.MiniGalleryTheme
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


const val READ_EXTERNAL_STORAGE_REQ_CODE = 553

class HostActivity : ComponentActivity() {

    private val folderViewModel = FolderViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MiniGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FolderListScreen(folderViewModel = folderViewModel)
                }
            }
        }

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

