package br.com.edsilfer.android_facebook_login.core.di.session

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.reactive_facebook.domain.entity.User
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by edgar on 25/08/17.
 */
@SessionScope
@Subcomponent(
        modules = arrayOf(
                SessionModule::class,
                SessionActivityBinding::class
        )
)
interface SessionComponent : AndroidInjector<App> {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun user(user: User): SessionComponent.Builder

        fun build(): SessionComponent
    }

}
