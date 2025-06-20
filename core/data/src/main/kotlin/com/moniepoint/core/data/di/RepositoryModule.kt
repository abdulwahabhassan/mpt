package com.moniepoint.core.data.di

import com.moniepoint.core.data.repository.impl.DefaultShipmentRepository
import com.moniepoint.core.data.repository.intf.ShipmentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsShipmentRepository(
        repository: DefaultShipmentRepository,
    ): ShipmentRepository

}
