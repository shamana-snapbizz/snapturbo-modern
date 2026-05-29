package com.snapbizz.snapturbo.onboarding.downloadsync.data.sync

import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

object ZipUtils {

    fun unzip(
        zipFile: File,
        destinationDir: File
    ): List<File> {

        val extractedFiles = mutableListOf<File>()

        ZipInputStream(zipFile.inputStream()).use { zis ->

            var entry = zis.nextEntry

            while (entry != null) {

                val outFile =
                    File(destinationDir, entry.name)

                FileOutputStream(outFile).use {
                    zis.copyTo(it)
                }

                extractedFiles.add(outFile)

                zis.closeEntry()

                entry = zis.nextEntry
            }
        }

        return extractedFiles
    }
}