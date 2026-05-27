package com.snapbizz.snapturbo.onboarding.backup.data.repository

import android.content.Context
import com.snapbizz.snapturbo.onboarding.backup.domain.respository.BackupRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class BackupRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BackupRepository {

    override suspend fun runAutoBackup(): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
              /*  val folderPath = SnapSharedUtils.getAutoBackUpFolder(context)
                    ?: return@withContext Result.failure(Exception("Auto backup folder missing"))

                createBackup(folderPath, isAuto = true)*/
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun runManualBackup(): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
              /*  val folderPath = SnapSharedUtils.getManualBackUpFolder(context)
                    ?: return@withContext Result.failure(Exception("Manual backup folder missing"))

                createBackup(folderPath, isAuto = false)*/
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    private fun createBackup(folderPath: String, isAuto: Boolean) {
        val backupFolder = File(folderPath)
        if (!backupFolder.exists()) backupFolder.mkdirs()

    /*    ToolkitV2.getSourceFilePathsToBackUp(context).forEach { source ->
            if (File(source).exists()) {
                ToolkitV2.createBackUp(context, source, folderPath, isAuto)
            }
        }

        if (isAuto) {
            SnapSharedUtils.setAutoBackUpLatestFileIndex(
                context,
                SnapSharedUtils.getAutoBackUpLatestFileIndex(context) + 1
            )
        }*/
    }
}
