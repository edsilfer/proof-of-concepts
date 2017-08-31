package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.core.di.acttivity.ActivityScope
import br.com.edsilfer.android_facebook_login.core.di.app.AppComponent
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewOptionsImpl
import dagger.Component

/**
 * Created by edgar on 06/08/17.
 */
@ActivityScope
@Component(
        modules = arrayOf(
                HomepageViewOptionsModule::class
        ),
        dependencies = arrayOf(
                AppComponent::class
        )
)
interface HomepageViewOptionsComponent {

    fun inject(homepageButtonPanelViewImpl: HomepageViewOptionsImpl)


}
