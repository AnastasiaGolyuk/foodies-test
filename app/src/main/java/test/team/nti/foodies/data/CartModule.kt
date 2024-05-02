package test.team.nti.foodies.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.team.nti.foodies.data.repository.CartRepository
import test.team.nti.foodies.data.repository.repositoryImpl.CartRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CartModule {
    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }
}