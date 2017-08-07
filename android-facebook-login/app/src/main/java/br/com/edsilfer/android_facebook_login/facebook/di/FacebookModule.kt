package br.com.edsilfer.android_facebook_login.facebook.di

import br.com.edsilfer.android_facebook_login.facebook.data.FacebookRepositoryImpl
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.GraphAPIDataSource
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.GraphAPIDataSourceImpl
import br.com.edsilfer.android_facebook_login.facebook.domain.FacebookRepository
import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 04/08/17.
 */
@Module
class FacebookModule {

    @Provides
    fun providesCallbackManafer () : CallbackManager = CallbackManager.Factory.create()

    // DATA TIER
    @Provides
    fun providesGraphAPIDataSource () : GraphAPIDataSource = GraphAPIDataSourceImpl()

    @Provides
    fun providesFacebookRepository (graphAPIDataSource: GraphAPIDataSource) : FacebookRepository = FacebookRepositoryImpl(graphAPIDataSource)

}
