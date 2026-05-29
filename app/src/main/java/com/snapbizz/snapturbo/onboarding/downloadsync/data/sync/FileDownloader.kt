package com.snapbizz.snapturbo.onboarding.downloadsync.data.sync

import com.snapbizz.snapturbo.commons.di.PublicClient
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import javax.inject.Inject

class FileDownloader @Inject constructor(

    @PublicClient
    private val client: OkHttpClient

) {
    fun downloadFile(
        url: String,
        targetFile: File
    ) {

        val request =
            Request.Builder()
                .url(url)
                .build()

        client.newCall(request)
            .execute()
            .use { response ->

                if (!response.isSuccessful) {
                    error("Download failed")
                }

                response.body?.byteStream()?.use { input ->

                    targetFile.outputStream().use {
                        input.copyTo(it)
                    }
                }
            }
    }
}