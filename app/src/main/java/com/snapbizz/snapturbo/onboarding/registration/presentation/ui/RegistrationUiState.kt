package com.snapbizz.snapturbo.onboarding.registration.presentation.ui

data class RegistrationUiState(
    // ── Pre-filled from OTP response ──────────────────────────────────────
    val ownerName: String = "",
    val ownerEmail: String = "",
    val gstin: String = "",
    val storePhone: String = "",
    val salesPersonPhone: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val storeName: String = "",
    val storeAddress: String = "",
    val posId: String = "",
    val storeId: String = "",

    // ── Language ──────────────────────────────────────────────────────────
    val selectedLanguage: String = "English",
    val languageExpanded: Boolean = false,

    // ── Toggles ─────────────────────────────
    val isMultiPosClient: Boolean = false,
    val skipDownloadSync: Boolean = false,

    // ── Terms ─────────────────────────────────────────────────────────────
    val isTermsAccepted: Boolean = false,
    val showTermsDialog: Boolean = false,

    // ── Validation error ──────────────────────────────────────────────────
    val error: String? = null

){
    val isRegisterEnabled: Boolean
        get() = isTermsAccepted &&
                salesPersonPhone.isNotBlank() &&
                gstin.isNotBlank() &&
                zipCode.isNotBlank() &&
                storeName.isNotBlank() &&
                storeAddress.isNotBlank() &&
                posId.isNotBlank()
}