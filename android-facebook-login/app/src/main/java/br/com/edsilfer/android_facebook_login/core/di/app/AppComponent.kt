package br.com.edsilfer.android_facebook_login.core.di.app

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.android_facebook_login.core.di.acttivity.ActivityBinding
import br.com.edsilfer.android_facebook_login.core.di.session.SessionComponent
import br.com.edsilfer.android_facebook_login.core.di.FacebookModule
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
                AppModule::class,
                FacebookModule::class,
                ActivityBinding::class,
                AndroidInjectionModule::class
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

}
