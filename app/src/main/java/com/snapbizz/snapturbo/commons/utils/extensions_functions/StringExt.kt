package com.snapbizz.snapturbo.commons.utils.extensions_functions

import android.util.Patterns

/* -------------------- NULL & EMPTY -------------------- */
fun String?.isNullOrEmptySafe(): Boolean =
    this == null || this.trim().isEmpty()

fun String?.isNotNullOrEmptySafe(): Boolean =
    !this.isNullOrEmptySafe()

fun String?.orEmptySafe(): String =
    this ?: ""

/* -------------------- USER LOGIN & PASSWORD -------------------- */

fun String.isStrongPassword(): Boolean =
    length >= 8 &&
            any { it.isUpperCase() } &&
            any { it.isLowerCase() } &&
            any { it.isDigit() } &&
            any { !it.isLetterOrDigit() }

fun String.isMediumPassword(): Boolean =
    length >= 6 &&
            any { it.isLetter() } &&
            any { it.isDigit() }

/* -------------------- MOBILE NUMBER -------------------- */

fun String.isValidPhone(): Boolean =
    matches(Regex("^[+]?[0-9]{10,13}$"))

fun String.isValidIndianMobile(): Boolean =
    matches(Regex("^[6-9]\\d{9}$"))

/* -------------------- REGISTRATION USERNAME -------------------- */

fun String.isValidName(): Boolean =
    matches(Regex("^[a-zA-Z ]{2,40}$"))

fun String.isValidUsername(): Boolean =
    matches(Regex("^[a-zA-Z0-9_]{4,16}$"))

/* -------------------- EMAIL -------------------- */
fun String.isValidEmail(): Boolean =
    isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

/* -------------------- NUMERIC -------------------- */

fun String.isNumeric(): Boolean =
    matches(Regex("^-?\\d+(\\.\\d+)?$"))

fun String.isDigitsOnly(): Boolean =
    matches(Regex("^\\d+$"))

/* -------------------- LENGTH -------------------- */

fun String.hasMinLength(min: Int): Boolean =
    length >= min

fun String.hasMaxLength(max: Int): Boolean =
    length <= max

fun String.lengthBetween(min: Int, max: Int): Boolean =
    length in min..max
/* -------------------- SPECIAL CHAR -------------------- */

fun String.containsSpecialChar(): Boolean =
    any { !it.isLetterOrDigit() }

fun String.hasNoSpecialChar(): Boolean =
    all { it.isLetterOrDigit() }

/* -------------------- MATCHING -------------------- */

fun String.equalsIgnoreCaseSafe(other: String?): Boolean =
    other != null && equals(other, ignoreCase = true)

fun String.isSameAs(other: String): Boolean =
    this == other

/* -------------------- Removing Spaces  -------------------- */

fun String.removeSpaces(): String =
    replace(" ", "")

fun String.trimAll(): String =
    trim().replace("\\s+".toRegex(), " ")