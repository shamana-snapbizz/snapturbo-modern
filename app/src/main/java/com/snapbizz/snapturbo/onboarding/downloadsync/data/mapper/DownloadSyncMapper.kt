package com.snapbizz.snapturbo.onboarding.downloadsync.data.mapper

import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.CustomerEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.InventoryEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.ProductEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.ApiCustomerDto
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.ApiInventoryDto
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.ApiProductDto

fun ApiProductDto.toEntity() =
    ProductEntity(
        productCode = productCode.toString(),
        name = name.orEmpty(),
        mrp = mrp,
        uom = uom,
        image = image,
        vatRate = vatRate,
        categoryId = categoryId,
        barcode = barcode,
        brandName = brandName,
        companyName = companyName,
        isDeleted = isDeleted
    )

fun ApiCustomerDto.toEntity() =
    CustomerEntity(
        phone = phone,
        name = name,
        address = address,
        email = email,
        creditLimit = creditLimit,
        gstin = gstin,
        membershipId = membershipId,
        isDisabled = isDisabled
    )

fun ApiInventoryDto.toEntity() =
    InventoryEntity(
        productCode = productCode,
        batchId = batchId,
        quantity = quantity,
        reorderPoint = reorderPoint,
        minimumBaseQuantity = minimumBaseQuantity,
        isDeleted = isDeleted
    )