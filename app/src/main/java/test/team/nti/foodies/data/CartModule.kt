package test.team.nti.foodies.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.team.nti.foodies.data.repository.CartRepository
import test.team.nti.foodies.data.repository.repositoryImpl.CartRepositoryImpl
import javax.inject.Singleton

/**
 * Cart module to provide cart repository through the app
 */
@Module
@InstallIn(SingletonComponent::class)
class CartModule {

    /**
     * Provide cart repository
     *
     * @return implementation on Cart Repository interface
     */
    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }
}