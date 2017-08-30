# Session Scope
---
## Index
 1. [Introduction](#introduction)
 2. [Implementation](#implementation)
     - [Tutorial](#tutorial)
 3. [License](#license)

---

<a name="introduction"></a>

## Introduction
_Before reading this, make sure you've read and understand the basics of [Dagger2](https://github.com/edsilfer/proof-of-concepts/tree/master/android-dagger2)._

> Scopes are a very important aspect to comprehend the lifecycle of the dependencies managed by Dagger 2. It a mechanism that tells for how long a managed dependency should live - in Dagger 2 there is only one scope ready to use, which is the Singleton one. It is possible to create custom scopes that can fit specific needs though. When used correctly, this feature can bring a lot of benefits to an application, mainly those based on authentication/authorization entry point.

This repository explains the process to implement ```@SessionScope``` in an application that contains a process of authentication/authorization. The objective of it is to grant the ability to inject an ```User``` dependency, that provides information of the current logged user. A project that follows this implementation can be found [here](https://github.com/edsilfer/proof-of-concepts/tree/master/android-facebook-login).

<a name="implementation"></a>

## Implementation
This entire implementation relies on [subcomponents and custom scopes](https://github.com/edsilfer/proof-of-concepts/tree/master/android-dagger2). It will also use the new [Dagger 2.1 ```android.injection``` API](https://github.com/edsilfer/proof-of-concepts/tree/master/android-dagger2/sample-configuration) in order to allow activity provision.

<a name="tutorial"></a>

### Tutorial
 1. Create a custom scope named ```@SessionScope```;
 2. Create a class named as ```SessionActivityBinding```:
     - add a method that will bind the activity sub component that will be provided under session scope - further details on this implementation can be found [here](https://github.com/edsilfer/proof-of-concepts/tree/master/android-dagger2/sample-configuration);
 3. Create a subcomponent named ```SessionComponent```:
     - annotate this subcomponent with the custom scope created on step 1;
     - create a ```@Subcomponent.Builder``` and add a method that will receive a ```User``` object as parameter;
         - annotate this method with ```@BindsInstance```;
         - add this method ```fun build(): SessionComponent``` in order to allow the provision of this ```SessionComponent``` - this will be necessary in order to release this component when user loggs out;
     - add ```SessionActivityBinding``` class into the module array;
     - **[OPTIONAL]**: Add a ```SessionModule``` class to provide dependencies that should be provided under ```@SessionScope```;
 4. On ```AppModule``` add ```SessionComponent``` as a subcomponent;
 5. Add the ```AppModule``` in the list of module's from ```AppComponent```;
 6. When user successfully authenticate into the system, and an User object is acquired, create an instance of ```SessionComponent``` that must remain live until user logs out;
 7. Whenever user logs out, discard the old instance of ```SessionComponent``` like: ```sessionComponent = null``` - this will discard all dependencies provided under ```@SessionScope```;
 8. Whenever user logs in again, repeat the process from step 6;

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
