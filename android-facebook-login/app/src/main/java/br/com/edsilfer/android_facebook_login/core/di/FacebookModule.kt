package br.com.edsilfer.android_facebook_login.core.di

import br.com.edsilfer.reactive_facebook.data.FacebookRepositoryImpl
import br.com.edsilfer.reactive_facebook.data.datasource.rest.GraphAPIDataSource
import br.com.edsilfer.reactive_facebook.data.datasource.rest.GraphAPIDataSourceImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManagerImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookRepository
import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 04/08/17.
 */
@Module
class FacebookModule {

    @Provides
    fun providesCallbackManager(): CallbackManager = CallbackManager.Factory.create()

    @Provides
    fun providesFacebookAPIManager(): FacebookAPIManager = FacebookAPIManagerImpl()

    // DATA TIER
    @Provides
    fun providesGraphAPIDataSource(): GraphAPIDataSource = GraphAPIDataSourceImpl()

    @Provides
    fun providesFacebookRepository(graphAPIDataSource: GraphAPIDataSource): FacebookRepository = FacebookRepositoryImpl(graphAPIDataSource)

}
