package com.snapbizz.snapturbo.onboarding.registration.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.VerifyOtpResult
import com.snapbizz.snapturbo.onboarding.registration.data.remote.model.RegistrationRequest
import com.snapbizz.snapturbo.onboarding.registration.domain.usecase.RegistrationUseCase
import com.snapbizz.snapturbo.onboarding.registration.presentation.ui.RegistrationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val appDataStore: AppDataStore
) : ViewModel() {

    companion object {
        private const val MAX_POS_ID = 97
    }

    private val _uiState =
        MutableStateFlow(RegistrationUiState())

    val uiState: StateFlow<RegistrationUiState> =
        _uiState.asStateFlow()

    private val _registrationState =
        MutableStateFlow<NetworkResult<Unit>?>(null)

    val registrationState:
            StateFlow<NetworkResult<Unit>?> =
        _registrationState.asStateFlow()

    init {
        Log.e("REG_VM", "INIT")
        loadRegistrationData()
    }

    private fun loadRegistrationData() {

        viewModelScope.launch {

            val json =
                appDataStore.verifyOtpResponseFlow
                    .firstOrNull()

            Log.e(
                "REG_DEBUG",
                "verifyOtpJson = $json"
            )

            if (json.isNullOrEmpty()) return@launch

            val response = Gson().fromJson(
                json,
                VerifyOtpResult::class.java
            )

            _uiState.value = _uiState.value.copy(

                ownerName =
                    response.retailer?.name.orEmpty(),

                ownerEmail =
                    response.retailer?.email.orEmpty(),

                gstin =
                    response.retailer?.gstin.orEmpty(),

                storePhone =
                    response.store?.phone?.toString().orEmpty(),

                salesPersonPhone =
                    response.store?.salespersonNumber?.toString().orEmpty(),

                city =
                    response.store?.city.orEmpty(),

                state =
                    response.store?.state.orEmpty(),

                zipCode =
                    response.store?.pincode?.toString().orEmpty(),

                storeName =
                    response.store?.name.orEmpty(),

                storeAddress =
                    response.store?.address.orEmpty(),

                posId =
                    response.posId?.toString().orEmpty(),

                storeId =
                    response.store?.storeId?.toString().orEmpty()
            )
        }
    }

    fun updateOwnerName(value: String) {
        _uiState.value = _uiState.value.copy(
            ownerName = value,
            error = null
        )
    }

    fun updateOwnerEmail(value: String) {
        _uiState.value = _uiState.value.copy(
            ownerEmail = value,
            error = null
        )
    }

    fun updateStorePhone(value: String) {
        _uiState.value = _uiState.value.copy(
            storePhone = value
        )
    }

    fun updateSalesPersonPhone(value: String) {
        _uiState.value = _uiState.value.copy(
            salesPersonPhone = value,
            error = null
        )
    }

    fun updateGstin(value: String) {
        _uiState.value = _uiState.value.copy(
            gstin = value,
            error = null
        )
    }

    fun updateZipCode(value: String) {
        _uiState.value = _uiState.value.copy(
            zipCode = value,
            error = null
        )
    }

    fun updateState(value: String) {
        _uiState.value = _uiState.value.copy(
            state = value,
            error = null
        )
    }

    fun updateCity(value: String) {
        _uiState.value = _uiState.value.copy(
            city = value,
            error = null
        )
    }

    fun updateStoreName(value: String) {
        _uiState.value = _uiState.value.copy(
            storeName = value,
            error = null
        )
    }

    fun updateStoreAddress(value: String) {
        _uiState.value = _uiState.value.copy(
            storeAddress = value,
            error = null
        )
    }

    fun updatePosId(value: String) {
        _uiState.value = _uiState.value.copy(
            posId = value,
            error = null
        )
    }

    fun updateTermsAccepted(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            isTermsAccepted = value,
            error = null
        )
    }

    fun updateSelectedLanguage(value: String) {
        _uiState.value = _uiState.value.copy(
            selectedLanguage = value,
            error = null
        )
    }

    fun updateLanguageExpanded(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            languageExpanded = value,
            error = null
        )
    }

    fun updateMultiPosClient(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            isMultiPosClient = value,
            error = null
        )
    }

    fun updateSkipDownloadSync(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            skipDownloadSync = value,
            error = null
        )
    }

    fun showTermsDialog() {
        _uiState.value = _uiState.value.copy(
            showTermsDialog = true,
            error = null
        )
    }

    fun hideTermsDialog() {
        _uiState.value = _uiState.value.copy(
            showTermsDialog = false
        )
    }

    private fun validateRegistration(): Boolean {

        val state = _uiState.value

        // SALES PERSON PHONE

        if (
            state.salesPersonPhone.isEmpty() ||
            state.salesPersonPhone.toLongOrNull() == null ||
            state.salesPersonPhone.toLongOrNull()!! <= 999999999
        ) {

            _uiState.value = state.copy(
                error = "Invalid sales person phone number"
            )

            return false
        }

        // GST / TIN

        if (state.gstin.isEmpty()) {

            _uiState.value = state.copy(
                error = "GSTIN / TIN cannot be empty"
            )

            return false
        }

        if (state.gstin.length < 15) {

            if (state.gstin.length > 11) {

                _uiState.value = state.copy(
                    error = "Invalid GSTIN"
                )

                return false
            }

            if (
                state.gstin.length < 11 ||
                !state.gstin.all { it.isDigit() }
            ) {

                _uiState.value = state.copy(
                    error = "Invalid TIN"
                )

                return false
            }
        }

        // ZIP CODE

        if (state.zipCode.isEmpty()) {

            _uiState.value = state.copy(
                error = "Invalid zip code"
            )

            return false
        }

        if (
            state.zipCode.toIntOrNull() != null &&
            state.zipCode.toInt() <= 99999
        ) {

            _uiState.value = state.copy(
                error = "Invalid zip code"
            )

            return false
        }

        // STORE NAME

        if (state.storeName.isEmpty()) {

            _uiState.value = state.copy(
                error = "Store name cannot be empty"
            )

            return false
        }

        // STORE ADDRESS

        if (state.storeAddress.isEmpty()) {

            _uiState.value = state.copy(
                error = "Store address cannot be empty"
            )

            return false
        }

        // POS ID

        val posId = state.posId.toIntOrNull()

        if (
            posId == null ||
            posId <= 0 ||
            posId > MAX_POS_ID
        ) {

            _uiState.value = state.copy(
                error = "Invalid POS ID"
            )

            return false
        }

        // TERMS

        if (!state.isTermsAccepted) {

            _uiState.value = state.copy(
                error = "Please accept terms and conditions"
            )

            return false
        }

        _uiState.value = state.copy(
            error = null
        )

        return true
    }

    fun onRegisterClicked() {

        if (!validateRegistration()) {
            return
        }

        viewModelScope.launch {

            _registrationState.value =
                NetworkResult.Loading

            val verifyOtpJson =
                appDataStore.verifyOtpResponseFlow
                    .firstOrNull()

            if (verifyOtpJson.isNullOrEmpty()) {

                _registrationState.value =
                    NetworkResult.Error(
                        "Registration data missing"
                    )

                return@launch
            }

            val verifyOtpResponse =
                Gson().fromJson(
                    verifyOtpJson,
                    VerifyOtpResult::class.java
                )

            Log.e(
                "TIN_DEBUG",
                "store tin = ${verifyOtpResponse.store?.tin}"
            )

            Log.e(
                "TIN_DEBUG",
                "retailer gstin = ${verifyOtpResponse.retailer?.gstin}"
            )

            val state = _uiState.value

            val request = RegistrationRequest(

                storeId =
                    verifyOtpResponse.store?.storeId ?: 0L,

                isDisabled =
                    verifyOtpResponse.store?.isDisabled
                        ?: false,

                phone = state.storePhone.toLongOrNull() ?: 0L,

                salesPersonNumber =
                    state.salesPersonPhone.toLongOrNull(),

                state =
                    state.state,

                city =
                    state.city,

                pincode =
                    state.zipCode.toIntOrNull() ?: 0,

                name =
                    state.storeName,

                address =
                    state.storeAddress,

                retailerId =
                    verifyOtpResponse.retailer?.retailerId
                        ?: 0L,

                stateId =
                    verifyOtpResponse.store?.stateId
                        ?: 0,

                tin =
                    when (state.gstin.length) {

                        11 -> state.gstin.toLongOrNull()

                        15 -> null

                        else -> null
                    },

                retailerGstin =
                    when (state.gstin.length) {

                        15 -> state.gstin

                        11 -> verifyOtpResponse.retailer?.gstin

                        else -> null
                    }

            )

            _registrationState.value =
                registrationUseCase(

                    deviceId =
                        appDataStore.deviceIdFlow
                            .firstOrNull().orEmpty(),

                    accessToken =
                        verifyOtpResponse.accessToken.orEmpty(),

                    request = request
                )
        }
    }
}
