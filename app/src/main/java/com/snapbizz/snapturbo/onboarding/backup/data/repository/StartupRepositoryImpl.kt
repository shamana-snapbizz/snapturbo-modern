package com.snapbizz.snapturbo.onboarding.backup.data.repository

import android.content.Context
import com.snapbizz.snapturbo.onboarding.backup.domain.respository.StartupRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class StartupRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StartupRepository {

    override suspend fun prepareStorage() = withContext(Dispatchers.IO) {
        val snapRoot = getSnapFolder(context)

        val dbPath = File(snapRoot, "snap_Databases")
        if (!dbPath.exists()) {
            dbPath.mkdirs()
            Timber.tag("pathName:").d("$dbPath")
        }

        /*SnapSharedUtils.setManualBackupFolder(context, dbPath.absolutePath)
        SnapSharedUtils.setAutoBackupFolder(context, dbPath.absolutePath)

        Log.d("StartupRepo", "DB folder prepared at ${dbPath.absolutePath}")*/
    }

    override suspend fun ensureDeviceId() = withContext(Dispatchers.IO) {
        /* if (PreferenceCache.deviceId == DEFAULT_DEVICE_ID) {
            PreferenceCache.deviceId = SnapCommonUtils.getDeviceId(context)
        }*/
    }

    private fun getSnapFolder(context: Context): File {

        val folder = File(context.getExternalFilesDir(null)!!.absolutePath, "SnapBizz-Data")
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder
    }
}