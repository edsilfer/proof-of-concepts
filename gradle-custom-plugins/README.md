# Gradle Custom Plugin
---
# Index
1. [Introduction](#introduction)
    * [Basic concepts](#basic-concepts)
    * [Purpose](#purpose)
3. [Tutorial](#tutorial)
    * [Summary]("summary)
    * [Testing](#testing)
    * [Project Extensions](#project-extensions)
2. [Glossary](#Glossary)
3. [References & Useful Links](#links)
4. [License](#license)

---

<a name="introduction"></a>

## Introduction

<a name="basic-concepts"></a>

### Basic concepts
> Build automation is the process of automating the creation of a software build and the associated processes including: compiling computer source code into binary code, packaging binary code, and running automated tests.

_https://en.wikipedia.org/wiki/Build_automation_

> [Gradle](https://docs.gradle.org/current/userguide/userguide.html) is an open source build automation system that builds upon the concepts of Apache Ant and Apache Maven and introduces a Groovy-based domain-specific language (DSL) instead of the XML form used by Apache Maven for declaring the project configuration.

_https://en.wikipedia.org/wiki/Gradle_

_Before proceeding any further, read the [Glossary](#glossary) section._

<a name="purpose"></a>

### Purpose
Thanks to the flexibility offered by Gradle, Google Engineers have been able to create [Android Gradle Plugin](http://google.github.io/android-gradle-dsl/current/index.html#N10009), which is a DSL that provides easy configuration that allows the deploy of Android Applications. Despite the vast options provided by this plugin, it is common that extra build steps have to be added in a mobile project in order to automatize boring tasks such as:
 - running unit tests;
 - checking code quality;
 - packing libraries in the shape of Maven artifacts;
 - upload Maven artifacts to external or local repositories;
 - parse ```build.gradle``` in order to build ```buck.configuration``` allowing the use of two idependent build system in a single project;

In order to make these extra steps useful through different project, the following characteristics should be present:
 - capable of being shared;
 - versioned;
 - testable;
 - able to be connected in a chain of steps that, together, composes the build phase;

Given these information, Gradle Custom Plugins appears as an excellent chose in order to reach the desirable quality of code that can power up your build process.

<a name="tutorial"></a>

## Tutorial
In order to create a project that can be latter on distributed, ideally, plugin should be place in a standalone project. Custom plugins can be built on:
    - **Build script**: automatically compiled and added to the build classpath. Only visible inside build script block that declared it;
    - **buildSrc project (```rootDir/buildSrc/src/main/language```)**: automatically compiled and added to the build classpath. Visible to all build scripts;
    - **Standalone project**: external project that generates a JAR that can be used in multiple builds;

It can also be written in any language that compiles to bytecode, however, most of the tutorials are written in Groovy.

<a name= "summary"></a>

### Summary

 1. Create a standalone Groovy project and add gradle plugin dependencies:
     - ```compile gradleApi()```;
     - ```compile localGroovy()```;
     - ```testCompile gradleTestKit()```;
     - ```testCompile "junit:junit:$test_version_junit"```;
     - ```testCompile ("org.spockframework:spock-core:$test_version_spock") {
         exclude group: 'org.codehaus.groovy', module: 'groovy-all'
     }``` - there is a conflict with the ```groovy-all```library and the Spock test framework, therefore, this module shall be removed;
 2. Create tasks classes that performs the desired job:
     - tasks should extend ```DefaultTask``` class;
     - tasks should have a method anottated with ```@TaskAction```, this will be the entry point that will be called by the plugin;
 3. Create the plugin class:
     - make it implements ```Plugin<t>``` - make the generic type be ```Project```;
     - inside ```apply()``` add the tasks with: ``` project.task("task_name", type: TaskClass)```;
 4. Steps 1 to 3 will make the code ready to be packet into a JAR file. Once it is done, it has to be placed in the new project's classpath. In order to do so, [Gradle Maven Plugin](https://docs.gradle.org/current/userguide/maven_plugin.html) can be used to generate the maven artifacts, after that it can be installed in the local repository via ```gradle install``` task or, it can be deployed in an remote repository and added as a dependency;

<a name="testing"></a>

### Testing the code
In order to test the code, the best approach is to use [Spock Framework](http://spockframework.org/spock/docs/1.1/index.html):

> Spock is a testing and specification framework for Java and Groovy applications. What makes it stand out from the crowd is its beautiful and highly expressive specification language. Thanks to its JUnit runner, Spock is compatible with most IDEs, build tools, and continuous integration servers. Spock is inspired from JUnit, jMock, RSpec, Groovy, Scala, Vulcans, and other fascinating life forms.

Understanding Spock is out of scope for this project. As far as Custom Gradle Plugin peculiarities are involved, what needs to be understood is the use of ```gradleTestKit()```, which allows testing build scripts, retrieving their result for later assertion:

```Groovy
GradleRunner.create()
    .withProjectDir(getTestProjectDirectory())
    .withArguments(ARG_TASK)
    .build()
```

Further details on testing tasks and the plugin can be found on the sample project.

<a name="project-extensions"></a>

### Project extensions
Data can be gathered from build script through the use of project extensions:
 - create a POJO class where data from build script will be mapped into;
 - inside plugin class, add the line: ```project.extensions.create("obj_name", POJOClass)```;
 - inside build scrip (```build.gradle```file), assign the values like: ```obj_name.field1 = 'sample value'```, another option would be the following sintaxe:

```Groovy
obj_name {
   filed1 = 'sample value'
   field2 = 'sample value'
}
```

<a name="Glossary"></a>

## Glossary
 - > **Project**: what a project represents depends on what it is that you are doing with Gradle. For example, a project might represent a library JAR or a web application. It might represent a distribution ZIP assembled from the JARs produced by other projects. A project does not necessarily represent a thing to be built. It might represent a thing to be done, such as deploying your application to staging or production environments. ;
 - > **Task**: each project is made up of one or more tasks. A task represents some atomic piece of work which a build performs. This might be compiling some classes, creating a JAR, generating Javadoc, or publishing some archives to a repository.;
 - **Plugin**: a software module packet into a JAR file that can be distributed among projects. Contains a set of tasks that can be executed when the plugin is applied in other projects;
 - **build script**: ```build.gradle``` file invoked when using the command ```gradle``` or ```./gradlew``` on bash. Defines a project and its tasks;

<a name="links"></a>

## References & Useful Links
 - [Gradle User Guide homepage](https://docs.gradle.org/current/userguide/userguide.html)
 - [Gradle: Writing Custom Plugins](https://docs.gradle.org/4.1/userguide/custom_plugins.html);
 - [Spock Testing Framework](http://spockframework.org/spock/docs/1.1/index.html)

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
