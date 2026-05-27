package com.snapbizz.snapturbo.onboarding.login.domain.model

import com.snapbizz.snapturbo.onboarding.login.data.remote.model.RetailerDetails
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.StoreDetails
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.VerifyOtpApiResponse

data class VerifyOtpResult(
    val accessToken: String?,
    val store: Store?,
    val retailer: Retailer?,
    val storeType: List<String>,
    val posId: Int?,
    val isVerified: Boolean,
    val message: String,
    val authToken : String
)

data class Store(
    val storeId: Long,
    val name: String,
    val phone: Long,

    val salespersonNumber: Long,

    val isDisabled: Boolean,

    val address: String,

    val city: String,

    val state: String,

    val pincode: Long,

    val stateId: Int,

    val tin: Long?

)

data class Retailer(
    val retailerId: Long,
    val name: String,
    val address: String,
    val email: String,
    val gstin: String
)

fun VerifyOtpApiResponse.toDomain(): VerifyOtpResult {
    val verified = status.equals("Success", ignoreCase = true)

    return VerifyOtpResult(
        accessToken = accessToken,
        store = storeDetails?.toDomain(),
        retailer = retailerDetails?.toDomain(),
        storeType = storeType ?: emptyList(),
        posId = posId,
        isVerified = verified,
        message = status,
        authToken = token
    )
}

fun StoreDetails.toDomain(): Store {

    return Store(
        storeId = storeId,

        name = name,

        phone = phone,

        salespersonNumber = salespersonNumber,

        isDisabled = isDisabled,

        address = address,

        city = city,

        state = state,

        pincode = pincode,

        stateId = stateId,

        tin = tin
    )
}

fun RetailerDetails.toDomain(): Retailer {
    return Retailer(
        retailerId = retailerId,
        name = name,
        address = address,
        email = email,
        gstin = gstin
    )
}
