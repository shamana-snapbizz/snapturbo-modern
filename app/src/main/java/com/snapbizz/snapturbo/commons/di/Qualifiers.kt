package com.snapbizz.snapturbo.commons.di

import javax.inject.Qualifier

// ---------------- Retrofit qualifiers ----------------
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicV2Api

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedV2Api

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicV3Api

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedV3Api

// ---------------- OkHttp qualifiers ----------------
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedClient

// ---------------- Other APIs ----------------
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SubscriptionApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CashfreeApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SnapOrderApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BrandPortalApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetailerPortalApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnterprisePortalApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SyncPortalApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SnapBizzCoreApi
