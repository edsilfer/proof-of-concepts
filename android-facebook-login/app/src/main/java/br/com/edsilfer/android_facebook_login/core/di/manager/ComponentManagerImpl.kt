package br.com.edsilfer.android_facebook_login.core.di.manager

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.android_facebook_login.core.di.app.AppComponent
import br.com.edsilfer.android_facebook_login.core.di.app.DaggerAppComponent
import br.com.edsilfer.android_facebook_login.core.di.session.SessionComponent
import br.com.edsilfer.reactive_facebook.domain.FacebookRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by edgar on 30/08/17.
 */
class ComponentManagerImpl(
        private val app: App,
        private val facebookRepository: FacebookRepository
) : ComponentManager {

    private var appComponent: AppComponent? = null
    private var sessionComponent: SessionComponent? = null

    override fun startAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder()
                    .application(app)
                    .build()

            appComponent!!.inject(app)
        }

        return appComponent!!
    }

    override fun startSessionComponent(): Completable {
        if (appComponent == null) startAppComponent()

        return facebookRepository
                .readUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { user ->
                    sessionComponent = appComponent!!.sessionSubComponent()
                            .user(user)
                            .build()

                    sessionComponent?.inject(app)
                }
                .doOnError { error ->
                    Timber.e(error.message)
                }.toCompletable()

    }

    override fun stopSessionComponent() {
        sessionComponent = null
    }

}
