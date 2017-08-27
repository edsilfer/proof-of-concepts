# Android Dagger
---
## Index
* [Introduction](#introduction)
    * [A little bit of history...](#history)
* [Modules, components and scope](#modules_components_scope)
    * [Modules](#modules)
    * [Components](#components)
    * [Scope](#scope)
        * [How to create a custom scope?](#custom_scope)
* [Glossary](#glossary)
* [References & Useful Links](#references)
---

<a name="introduction"></a>

# Introduction
This repository contains sample projects that illustrates how Dagger 2 Dependency Injection Container works along with Kotlin. It also serves the purpose of registering the knowledge acquired so far and shall be in continuous evolution.

<a name="history"></a>

## A little bit of history...
_This section is mostly a transcript from [lecture](https://www.youtube.com/watch?v=oK_XtfXPkqw&feature=youtu.be) presented by [Gregory Kick](https://www.linkedin.com/in/gregory-kick-93514618/0) that introduced Dagger 2._

___
Dagger is project originally created by former Google's employees that went to work at Square, it mainly succeeds Google's Guice project. Their idea was to create an API that could address some of the main issues presented by popular containers for Dependency Injection at the time, like Guice and Spring. Some of these problems were:
 - Configuration in XML;
 - Need to start the application in order to verify that something went wrong with the graph configuration;
 - Problems during debbuging like, debug tools unable to collect state of injected object;
 - Code becomes untraceable because it is generated using reflection, therefore, you can't find where the constructor of the injected class is being called;

Some of the breakthroughs this new API has brought are:
 - Use of annotation processing for code generation, wiping the need of reflection and improving compile time - highly efficient provision;
 - Compile time validations for portions of the graph;
 - Easy debbuging (human friendly naming for generated artifacts;) ensuring entirely concrete call stack;

Despite the advances mentioned above, there were issues that also came with it like:
 - Ugly generated code;
 - Runtime graph composition;
 - Inefficient graph creation;
 - Partial traceability;
 - Map like API.

This initial project, under Square's banner, is nowadays referred as Dagger 1. In order to address the main issues from Dagger 1, Google forked the original Square's repository and created the so called Dagger 2. The main benefits of Dagger 2 included:
 - Compile time validation for the entire graph;
 - Ability to navigate the entire application with the use of tools like "find usage" and "open declaration";
 - Simplified the code with the use of annotations, i.e. ```@Module``` - no more map like API;
 - Improved performance targeting to be as fast as hand-written code;

<a name="modules_components_scope"></a>

# Modules, components and scope
_This section provides the definitions of each one of these word that, together, consists the core of Dagger API. This section is mostly taken from [post written by Randall Mitchell on Medium.](https://medium.com/@methodsignature/dagger-2-dependency-injection-for-android-developers-51d60e7397e6)_

___

<a name="modules"></a>

## Modules
Straight to the point: modules are public classes annotated with ```@Module``` that tells Dagger how to build and provide dependencies through public methods annotated with ```@Provides```. An application can be composed of one or many modules. A well designed application will be composed of many modules. Dependencies inside a module must be related - in order to figure if a dependency makes sense inside a module, it is necessary to evaluate the problem being implemented. Usually classes that lives inside the same package have a strong relation among them and are likely candidates to be provided inside the same module, however, there are cases where this rule will be violated. The code below shows an example of a situation:

```Kotlin
@Module
class BetaModule {
    @Provides
    fun providesDialogView (secondaryViewImpl: BetaViewImpl) : DialogView = DialogViewImpl(secondaryViewImpl)

    @Provides
    fun providesBetaView (betaViewImpl: BetaViewImpl) : BetaView = betaViewImpl

    @Provides
    fun providesBetaPresenter(
            betaView: BetaView,
            dialogView: DialogView
    ): BetaPresenter = BetaPresenterImpl(betaView, dialogView)
}
```

This module provides three dependencies:
 - **DialogView**: a generic class specialized in displaying Dialogs. It has a dependency with the current application living view;
 - **BetaView**: abstraction for BetaView that will be the current living view at some point;
 - **BetaPresenter**: on the MVP framework this is the presenter for BetaView;

As you can see, ```DialogView``` can be injected in different views throughout the application, however, it requires the current living view in order to work properly. Therefore, despite being defined in a common package for the application, it is provided many times in different modules. This fact doesn't mean that the module is not cohesive though, because dependencies are still close. Usually dependencies provided in a module are satisfied inside its own module. If a dependency starts requiring many dependencies from other modules, it is an indicative that the application design might not be very well planed.

<a name="components"></a>
## Components
When not all dependencies can be resolved inside the same module, it is possible to acquire them from different modules. Many modules can be grouped into a component and, when that happen, a module's dependency can be resolved by another module that belongs to the same component. Components are also the entry point where requesters will ask for dependencies. Modules create the dependencies, components offer then via its interface.

```Kotlin
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun providesFakeWorker(): FakeWorker

    fun inject(app: App)

}
```

Dagger will generate classes that implements the interface defined by the Components. It does it during the compilation phase and these classes are named as ```Dagger<component name>```. They shall be implemented by the requesters through a Builder provided by Dagger. If the modules provided through the component requires runtime parameters, it means, they have a non-empty public constructor, builder will expose methods that will take an implementation of these Modules in order to satisfy its dependencies.

<a name="cross_component_dependency"></a>
## Cross-component dependency provision
Components can also offer dependencies created by its modules to other components. This can be done via subcomponents and dependencies. The former is defined as any other component, the difference is in the parent that will add a provider method that returns the subcomponent and take its modules as parameters. The requester will then provides the module instantiation upon parent's component request:

```Kotlin
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun providesFakeWorker(): FakeWorker

    fun inject(app: App)

    fun AlphaComponent (alphaModule :AlphaModule) : AlphaComponent

}

@Module
class AlphaModule {

    @Provides
    fun providesMainView (mainViewImpl: AlphaViewImpl) : AlphaView = mainViewImpl

    @Provides
    fun providesRouter (mainViewImpl: AlphaViewImpl) : Router = RouterImpl(mainViewImpl)

}
```

An interesting point to keep in mind is that subcomponents can be stacked to create complex component graphs, by doing that, once a subcomponent is released Dagger 2 will allow them to fall out of scope and their dependencies can be garbage collected. The later option is to define one component as dependency of another. This is done via dependencies property of Component annotation:

```Kotlin
@Singleton
@Component(
    modules = arrayOf(AppModule::class),
    dependencies = arrayOf (AlphaModule::class)
)
interface AppComponent {

    fun providesFakeWorker(): FakeWorker

    fun inject(app: App)

}

@Module
class AlphaModule {

    @Provides
    fun providesMainView (mainViewImpl: AlphaViewImpl) : AlphaView = mainViewImpl

    @Provides
    fun providesRouter (mainViewImpl: AlphaViewImpl) : Router = RouterImpl(mainViewImpl)

}
```

As opposed to what happen in the subcomponent mechanism, dependencies of one component can only become available to other component if exposed through the first. This might let things more visible once you can manually choose which module's dependency will be exposed to other components that adds it as dependent. Just as in the subcomponent approach, adding component as dependencies also stacks, although it is still necessary to manually provide the dependencies through the newly built tree.

<a name="scope"></a>
## Scope
_This section is highly based on post [Miroslaw Stanek post on frogmercs.](http://frogermcs.github.io/dependency-injection-with-dagger-2-custom-scopes/)_
___
Scopes are a very important aspect to comprehend the lifecycle of the dependencies managed by Dagger 2. It a mechanism that tells for how long a managed dependency should live - in Dagger 2 there is only one scope ready to use, which is the Singleton one. It is possible to create custom scopes that can fit specific needs though. When used correctly, this feature can bring a lot of benefits to an application, mainly those based on authentication/authorization entry point.

**Example:**
An application that does not provides User object as a dependency managed by the Dependency Injection Container, usually requires many queries to the database in order to obtain the information needed to perform its business rules - or at least an static class that must handle updates to such user. With custom scopes, it is possible to provide the current logged user information through an ```UserSession```, for example. The lifecycle of such component is then managed by the developer whenever user logs in or out the system.

<a name="custom_scope"></a>
### How to create a custom scope?
Scope are applied to Components through the annotation ```@Scope``` or ```@Singleton``` - which is a pre-made scope. A component annotated with Singleton is defined as an Application Scope component, it means, its dependencies will leave as long as the Application leaves, even if the Component dies. Custom scopes are implement as classes like the example below:

```Kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity
```

Where:
 - ```@Scope``` represents annotates this class for pos annotation process;
 - ```@Retention(AnnotationRetention.RUNTIME)``` binds component and dependencies lifecycle, it mean, when the parent component dies, all dependencies will die as well;

After this custom scope is created, the developer becomes responsible for deciding when the Component and its dependencies will live or die, being able to create scopes such as: ```UserSession```, ```Activity```, ````Fragments````, etc. Prior to Dagger 2.10 the only way to apply these scopes where by annotating the component, however, with the advent of dagger.android, it is possible to attribute these scopes in a different way. Check out the section Dagger Android for further information on this subject.

<a name="glossary"></a>

# Glossary
 - **Object Graph**: process of resolving dependencies in order to resolve other dependencies eventually leads to a network of dependency resolutions. This network is known as an object graph. Dagger, along with other dependency injection libraries, are designed to allow objects to be requested out of this graph. When an object is requested, it’s dependencies are resolved — the object is constructed and handed back to the requestor.
 - **Dependency**: whenever a class creates and uses an object of another class, its is said the the former has a dependency with the last;

<a name="references"></a>

# References & Useful Links
  - [Square's Dagger 1 official website](http://square.github.io/dagger/);
  - [Google's Dagger 2 official website](https://google.github.io/dagger/);
  - [How to use custom scopes in order to provide user information through it own component](http://frogermcs.github.io/dependency-injection-with-dagger-2-custom-scopes/)?
