# Sample Configuration: Dagger 2 + Kotlin + _dagger.android_
---
# Index
 1. [Required dependencies](#dependencies)
 2. [Prepare the Application](#application)
 3. [Approach 01: ```ActivityBuilder```](#app01)
 4. [Approach 02: ```ActivityBinding```](#app01)
 5. [License](#license)
---

## Introduction
Dagger 2.10 introduced ```dagger.android```, which is a framework that simplifies dependency injection in Android, getting rid of many boilerplate code and allowing Activity provision. One of the main benefits of using ```dagger.android``` classes is the ability to manage Activities via Dagger2. Through this feature it is possible to request an activity - or any of its internal resources, such as context or fragment manager - just like any other class managed by Dagger2.

_This section is highly based on [Vandolf Estrellado post on Medium](https://proandroiddev.com/how-to-android-dagger-2-10-2-11-butterknife-mvp-part-1-eb0f6b970fd) and [this article from Nimrod Dayan.](https://android.jlelse.eu/android-and-dagger-2-10-androidinjector-5e9c523679a3)_

<a name="dependencies"></a>

## Required dependencies
```Groovy
dependencies {
    def daggerVersion = '2.11'

    annotationProcessor "com.google.dagger:dagger-compiler:$daggerVersion"
    annotationProcessor "com.google.dagger:dagger-android-processor:$daggerVersion"

    implementation "com.google.dagger:dagger:$daggerVersion"
    implementation "com.google.dagger:dagger-android:$daggerVersion"
}
```

<a name="application"></a>

## Prepare the Application
There are two ways to prepare the application class to work with _android.dagger_ classes: implementing the ```HasActivityInjector``` interface or extending the DaggerApplication:

```Kotlin
class App : Application(), HasActivityInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)
}
```

_It is aways interesting to note that inheritance should be avoided in order to keep the class open in case in the future there is really no other option rather than implementing it. Also, note that if using inheriting from DaggerApplication, you should correctly implement the Component in order to provide it from App the way shown above._

<a name="app01"></a>

## Approach 01: ```ActivityBuilder```
This approach uses the ```@ContributesAndroidInjector``` annotation to add activities's modules to Dagger2's object graph:

```Kotlin
@Module abstract class ActivitiesBuilder {
    @ContributesAndroidInjector(modules = arrayOf(WelcomeModule::class))
    abstract fun provides(): WelcomeViewImpl
}
```

The greatest benefit of this approach is the simplicity. Whenever a new activity is created, a new method is placed in this class and the corresponding module specified.

<a name="app02"></a>

## Approach 02: ```ActivityBinding```
This approach relies on subcomponents in order to add activities's modules to Dagger2's object graph:

```Kotlin
@Module
class SampleActivityModule {
    @Provides
    fun providesSampleActivity(sampleActivity : SampleActivity): SampleActivity = sampleActivity
}

@Subcomponent(modules = arrayOf(SampleActivityModule::class))
interface SampleActivitySubComponent : AndroidInjector<SampleActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SampleActivity>()
}

@Module
abstract class ActivityBinding {
    @Binds
    @IntoMap
    @ActivityKey(SampleActivity::class)
    abstract fun bindLoginView(builder: SampleActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@Singleton
@Component(
        modules = arrayOf(
                AppModule::class,
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

    override fun inject(app: App)
}
```

The greatest benefit of this approach is the ability to set these modules in a component in a different scope rather than the Application. This is particularly useful in a context where a ```@SessionComponent``` is required, for instance.

<a name="license"></a>

## License
    Copyright 2017 Edgar da Silva Fernandes

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
