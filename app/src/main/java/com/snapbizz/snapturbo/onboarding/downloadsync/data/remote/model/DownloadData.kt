package com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model

data class DownloadData(

    val products: List<FileURLModel>?,

    val customers: List<FileURLModel>?,

    val inventories: List<FileURLModel>?
)