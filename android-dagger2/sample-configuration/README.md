# Sample Configuration: Dagger 2 + Kotlin + _dagger.android_
---
# Index
 1. [Requeride Dependencies](#dependencies)
 2. [Prepare the Application](#application)
 3. [ActivityBuilder](#activitybuilder)
 4. [License](#license)
---
Dagger 2.10 introduced dagger.android, which is a framework that simplifies dependency injection with Dagger 2 in Android, getting rid of many boilerplate code and allowing Activity provision.

_This section is highly based on [Vandolf Estrellado post on Medium.](Vandolf Estrellado post on Medium)_

<a name="dependencies"></a>

## Required Dependencies
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

<a name="activitybuilder"></a>

## ActivityBuilder
One of the main benefits of using dagger.android classes is the ability to manage Activities via Dagger2. Through this feature it is possible to request an activity - or any of its internal resources, such as context or fragment manager - just like any other class managed by Dagger2. In order to implement it, it is useful to have a file that will manage all activities and its related modules. This file will also be the entry point for setting the scope of the activities dependencies. A good name for such file would be ```AcitivityBuilder```:

```Kotlin
@Module abstract class ActivitiesBuilder {
    @ContributesAndroidInjector(modules = arrayOf(WelcomeModule::class))
    abstract fun provides(): WelcomeViewImpl
}
```
Note that this class binds the activity injection and the Modules that will provide the dependencies required by it. Whenever a new activity is created, a new method must be entered in this class and the corresponding module specified.

<a name="license"></a>

# License
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
