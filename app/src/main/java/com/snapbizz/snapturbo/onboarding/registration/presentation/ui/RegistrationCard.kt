package com.snapbizz.snapturbo.onboarding.registration.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.language.LocalAppResources
import com.snapbizz.snapturbo.onboarding.registration.presentation.viewmodel.RegistrationViewModel
import com.snapbizz.snapturbo.ui.components.SnapCard
import com.snapbizz.snapturbo.ui.components.SnapTextField
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.components.text.SnapTextVariant

// ─── Colors ──────────────────────────────────────────────────────────────────

private val HeaderBlue     = Color(0xFF283593)
private val AccentBlue     = Color(0xFF1565C0)
private val SurfaceWhite   = Color(0xFFFFFFFF)
private val BackgroundGray = Color(0xFFF0F0F0)
private val TermsBg        = Color(0xFF3A3F5C)
private val TermsText      = Color(0xFFFFFFFF)
private val AcceptGreen    = Color(0xFF4CAF50)
private val DenyRed        = Color(0xFFF44336)
private val LabelGray      = Color(0xFF757575)

// ─── Compact labelled field ───────────────────────────────────────────────────

@Composable
private fun CompactField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        SnapText(
            text = label,
            textVariant = SnapTextVariant.SMALL,
            color = LabelGray,
        )
        SnapTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        )
    }
}

// ─── Section card ─────────────────────────────────────────────────────────────

@Composable
private fun SectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    SnapCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            SnapText(
                text = title,
                textVariant = SnapTextVariant.LABEL,
                color = AccentBlue,
                isBold = true,
            )
            content()
        }
    }
}

// ─── Pill button ─────────────────────────────────────────────────────────────

@Composable
private fun PillButton(
    text: String,
    backgroundColor: Color,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (enabled) backgroundColor else Color.Gray)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        SnapText(
            text = text,
            textVariant = SnapTextVariant.LABEL,
            color = SurfaceWhite,
            isBold = true,
        )
    }
}

// ─── Full-screen Terms & Conditions overlay ──────────────────────────────────

@Composable
private fun TermsAndConditionsScreen(
    onAgree: () -> Unit,
    onDismiss: () -> Unit,
) {
    val appResources = LocalAppResources.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TermsBg),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
        ) {
            SnapText(
                text = "BY INSTALLING OR USING THIS SOFTWARE AND ANY APPLICATIONS USED IN " +
                        "CONJUNCTION WITH THIS SOFTWARE (\"SOFTWARE\") YOU ARE AGREEING TO BE " +
                        "BOUND BY ALL OF THE TERMS SET FORTH BELOW. IF YOU DO NOT AGREE TO THE " +
                        "TERMS OF THIS AGREEMENT, DO NOT INSTALL OR USE THE SOFTWARE..",
                textVariant = SnapTextVariant.DEFAULT,
                color = TermsText,
                isBold = true,
            )

            Spacer(Modifier.height(16.dp))

            SnapText(
                text = "Limited License. SnapBizz CloudTech Pte. Ltd, the licensor of the " +
                        "Software (\"We\" or \"Licensor\"), grants you a non-exclusive limited " +
                        "license to install the object code version of the Software on one device " +
                        "and to use the Software on such device. All rights not expressly granted " +
                        "are reserved by the Licensor.",
                textVariant = SnapTextVariant.DEFAULT,
                color = TermsText,
            )

            Spacer(Modifier.height(16.dp))

            SnapText(
                text = "Privacy Policy. We encourage you to review our privacy policy which can " +
                        "be found at bottom of the page (SnapBizz Privacy Policy). By agreeing to " +
                        "this End User License Agreement you are also agreeing to our Privacy Policy. " +
                        "Our Privacy Policy governs the collection, use and disclosure of information " +
                        "we collect from you. The information we collect is stored and processed by " +
                        "us on servers in India and potentially other countries. By installing the " +
                        "Software you consent to any such transfer of your information outside your " +
                        "country and/or outside the country where you use or have installed the Software.",
                textVariant = SnapTextVariant.DEFAULT,
                color = TermsText,
            )

            Spacer(Modifier.height(16.dp))

            SnapText(
                text = "Restrictions. You agree not to reproduce, modify, make derivative works " +
                        "or distribute the Software to any third party. You agree not to decompile, " +
                        "disassemble or reverse engineer the Software. You agree not to (i) remove " +
                        "any copyright or other proprietary notice from the Software, or (ii) rent, " +
                        "lease, lend, upload, host, sell, or sublicense the Software, in whole or in part.",
                textVariant = SnapTextVariant.DEFAULT,
                color = TermsText,
            )

            Spacer(Modifier.height(16.dp))

            SnapText(
                text = "Ownership. You agree that the Licensor and its licensors retain all right, " +
                        "title and interest in and to the Software and has unconditional right to " +
                        "lock-out or disable the software. You agree to erase the Software from your " +
                        "wireless device upon receipt of notice.",
                textVariant = SnapTextVariant.DEFAULT,
                color = TermsText,
            )

            Spacer(Modifier.height(16.dp))

            SnapText(
                text = "Termination. This Agreement shall terminate immediately, without notice, if " +
                        "you fail to comply with any material term of this Agreement. Upon termination " +
                        "you agree to immediately erase the Software from your device. Termination is " +
                        "in addition to and not in lieu of any other rights and remedies Licensor may have.",
                textVariant = SnapTextVariant.DEFAULT,
                color = TermsText,
            )

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PillButton(
                    text = appResources.getString(R.string.agree),
                    backgroundColor = AcceptGreen,
                    onClick = onAgree,
                )
                Spacer(Modifier.width(12.dp))
                PillButton(
                    text = appResources.getString(R.string.cancel),
                    backgroundColor = DenyRed,
                    onClick = onDismiss,
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

// ─── Registration Card ───────────────────────────────────────────────────────

/**
 * Self-contained UI card for registration — mirrors the LoginCard pattern.
 *
 * Reads all UI state from [registrationViewModel]; no business logic lives here.
 *
 * @param isLoading propagated from [RegistrationScreen] so the card knows
 *                  whether a network call is in flight.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationCard(
    registrationViewModel: RegistrationViewModel,
    isLoading: Boolean,
) {
    val appResources = LocalAppResources.current
    val uiState by registrationViewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        // ── Scrollable main content ───────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
                .verticalScroll(rememberScrollState()),
        ) {

            // ── HEADER ───────────────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HeaderBlue)
                    .padding(horizontal = 20.dp, vertical = 14.dp),
            ) {
                SnapText(
                    text = appResources.getString(R.string.snapbizz_registration),
                    textVariant = SnapTextVariant.HEADING,
                    color = SurfaceWhite,
                    isBold = true,
                )
                Spacer(Modifier.height(4.dp))
                SnapText(
                    text =
                        "${BuildConfig.APP_VERSION_PREFIX} : ${BuildConfig.VERSION_NAME}\nStore ID: ${uiState.storeId}",
                    textVariant = SnapTextVariant.SMALL,
                    color = Color(0xFFC5CAE9),
                )
            }

            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                // ── ROW 1 : MultiPOS | Extensions ────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    SnapCard(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            SnapText(
                                text = appResources.getString(R.string.as_multipos_client),
                                textVariant = SnapTextVariant.DEFAULT,
                            )
                            Switch(
                                checked = uiState.isMultiPosClient,
                                onCheckedChange = { registrationViewModel.updateMultiPosClient(it) },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = SurfaceWhite,
                                    checkedTrackColor = AccentBlue,
                                    uncheckedThumbColor = SurfaceWhite,
                                    uncheckedTrackColor = Color(0xFFE0E0E0),
                                ),
                            )
                        }
                    }

                    SnapCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            SnapText(
                                text = appResources.getString(R.string.extensions),
                                textVariant = SnapTextVariant.LABEL,
                                color = AccentBlue,
                                isBold = true,
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                repeat(3) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(22.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFFE8EAF6)),
                                    )
                                }
                            }
                        }
                    }
                }

                // ── ROW 2 : Language | POS ID | Skip Sync ───────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    // Language dropdown
                    SnapCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            SnapText(
                                text = appResources.getString(R.string.select_language),
                                textVariant = SnapTextVariant.LABEL,
                                color = AccentBlue,
                                isBold = true,
                            )
                            ExposedDropdownMenuBox(
                                expanded = uiState.languageExpanded,
                                onExpandedChange = {
                                    registrationViewModel.updateLanguageExpanded(!uiState.languageExpanded)
                                },
                            ) {
                                Row(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .clickable {
                                            registrationViewModel.updateLanguageExpanded(!uiState.languageExpanded)
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                ) {
                                    SnapText(
                                        text = uiState.selectedLanguage,
                                        textVariant = SnapTextVariant.DEFAULT,
                                    )
                                    SnapText(
                                        text = "▼",
                                        textVariant = SnapTextVariant.DEFAULT,
                                        color = AccentBlue,
                                        modifier = Modifier.padding(top = 1.dp),
                                    )
                                }

                                ExposedDropdownMenu(
                                    modifier = Modifier.width(180.dp),
                                    expanded = uiState.languageExpanded,
                                    onDismissRequest = {
                                        registrationViewModel.updateLanguageExpanded(false)
                                    },
                                ) {
                                    listOf("English", "Kannada", "Hindi", "Tamil").forEach { language ->
                                        DropdownMenuItem(
                                            text = { Text(language) },
                                            onClick = {
                                                registrationViewModel.updateSelectedLanguage(language)
                                                registrationViewModel.updateLanguageExpanded(false)
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // POS ID
                    SnapCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            SnapText(
                                text = appResources.getString(R.string.pos_id),
                                textVariant = SnapTextVariant.LABEL,
                                color = AccentBlue,
                                isBold = true,
                            )
                            SnapText(
                                text = uiState.posId,
                                textVariant = SnapTextVariant.DEFAULT,
                            )
                        }
                    }

                    // Skip Download Sync
                    SnapCard(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            SnapText(
                                text = appResources.getString(R.string.skip_download_sync),
                                textVariant = SnapTextVariant.DEFAULT,
                            )
                            Switch(
                                checked = uiState.skipDownloadSync,
                                onCheckedChange = { registrationViewModel.updateSkipDownloadSync(it) },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = SurfaceWhite,
                                    checkedTrackColor = AccentBlue,
                                    uncheckedThumbColor = SurfaceWhite,
                                    uncheckedTrackColor = Color(0xFFE0E0E0),
                                ),
                            )
                        }
                    }
                }

                // ── MAIN CONTENT ─────────────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    // Left column — Sales Person + Store Details
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        SectionCard(title = appResources.getString(R.string.sales_person_details)) {
                            CompactField(
                                label = appResources.getString(R.string.phone_number),
                                value = uiState.salesPersonPhone,
                                onValueChange = { registrationViewModel.updateSalesPersonPhone(it) },
                            )
                        }
                        SectionCard(title = appResources.getString(R.string.store_details)) {
                            CompactField(
                                label = appResources.getString(R.string.store_name),
                                value = uiState.storeName,
                                onValueChange = { registrationViewModel.updateStoreName(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.address),
                                value = uiState.storeAddress,
                                onValueChange = { registrationViewModel.updateStoreAddress(it) },
                            )
                        }
                    }

                    // Right column — Owner Details
                    Column(modifier = Modifier.weight(1f)) {
                        SectionCard(title = appResources.getString(R.string.owner_details)) {
                            CompactField(
                                label = appResources.getString(R.string.owner_name),
                                value = uiState.ownerName,
                                onValueChange = { registrationViewModel.updateOwnerName(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.email),
                                value = uiState.ownerEmail,
                                onValueChange = { registrationViewModel.updateOwnerEmail(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.store_phone_number),
                                value = uiState.storePhone,
                                onValueChange = { registrationViewModel.updateStorePhone(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.state),
                                value = uiState.state,
                                onValueChange = { registrationViewModel.updateState(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.store_city),
                                value = uiState.city,
                                onValueChange = { registrationViewModel.updateCity(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.zip_code),
                                value = uiState.zipCode,
                                onValueChange = { registrationViewModel.updateZipCode(it) },
                            )
                            CompactField(
                                label = appResources.getString(R.string.gstin_tin_number),
                                value = uiState.gstin,
                                onValueChange = { registrationViewModel.updateGstin(it) },
                            )
                        }
                    }
                }

                // ── BOTTOM : Terms + Buttons ──────────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = uiState.isTermsAccepted,
                            onCheckedChange = { registrationViewModel.showTermsDialog() },
                            colors = CheckboxDefaults.colors(
                                checkedColor = AccentBlue,
                                uncheckedColor = Color(0xFF888888),
                            ),
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(Modifier.width(8.dp))
                        SnapText(
                            text = appResources.getString(R.string.terms_prefix),
                            textVariant = SnapTextVariant.SMALL,
                        )
                        SnapText(
                            text = appResources.getString(R.string.terms_and_conditions),
                            textVariant = SnapTextVariant.SMALL,
                            color = AccentBlue,
                            modifier = Modifier.clickable { registrationViewModel.showTermsDialog() },
                        )
                        SnapText(
                            text = appResources.getString(R.string.terms_suffix),
                            textVariant = SnapTextVariant.SMALL,
                        )
                    }

                    Spacer(Modifier.width(16.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PillButton(
                            text = if (isLoading)
                                appResources.getString(R.string.registering)
                            else
                                appResources.getString(R.string.accept),
                            backgroundColor = AcceptGreen,
                            enabled = uiState.isRegisterEnabled && !isLoading,
                            onClick = { registrationViewModel.onRegisterClicked() },
                        )
                        PillButton(
                            text = appResources.getString(R.string.deny),
                            backgroundColor = DenyRed,
                            onClick = {
                                TODO("DENY action not implemented")
                            }
                        )
                    }
                }
            }
        }

        // ── Error snackbar ────────────────────────────────────────────────────
        uiState.error?.let { error ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 90.dp, end = 24.dp),
                contentAlignment = Alignment.BottomEnd,
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFD96B6B))
                        .padding(horizontal = 24.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Text(text = "⚠", color = Color.Yellow)
                    SnapText(
                        text = error,
                        textVariant = SnapTextVariant.DEFAULT,
                        color = Color.Black,
                        isBold = true,
                    )
                }
            }
        }

        // ── T&C full-screen overlay ───────────────────────────────────────────
        if (uiState.showTermsDialog) {
            TermsAndConditionsScreen(
                onAgree = {
                    registrationViewModel.updateTermsAccepted(true)
                    registrationViewModel.hideTermsDialog()
                },
                onDismiss = {
                    registrationViewModel.hideTermsDialog()
                },
            )
        }
    }
}