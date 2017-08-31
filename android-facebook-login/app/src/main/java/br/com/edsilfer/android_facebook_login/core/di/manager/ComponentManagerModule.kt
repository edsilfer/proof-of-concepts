package br.com.edsilfer.android_facebook_login.core.di.manager

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.reactive_facebook.domain.FacebookRepository
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 30/08/17.
 */
@Module
class ComponentManagerModule {

    @Provides
    fun providesComponentManager(
            app: App,
            facebookRepository: FacebookRepository
    ): ComponentManager = ComponentManagerImpl(app, facebookRepository)

}