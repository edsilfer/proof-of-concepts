package br.com.edsilfer.android_facebook_login.core.di.app

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.android_facebook_login.core.di.FacebookModule
import br.com.edsilfer.android_facebook_login.core.di.acttivity.ActivityBinding
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManager
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManagerModule
import br.com.edsilfer.android_facebook_login.core.di.session.SessionComponent
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import br.com.edsilfer.reactive_facebook.domain.FacebookRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by edgar on 03/08/17.
 */
@Singleton
@Component(
        modules = arrayOf(
                FacebookModule::class,
                AppModule::class,
                ActivityBinding::class,
                AndroidInjectionModule::class,
                ComponentManagerModule::class
        )
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun sessionSubComponent(): SessionComponent.Builder

    override fun inject(app: App)

    /*
     Necessary for submodules
    */
    fun providesFacebookRepository(): FacebookRepository

    fun providesFacebookAPIManager(): FacebookAPIManager

    fun providesComponentManager(): ComponentManager

}
