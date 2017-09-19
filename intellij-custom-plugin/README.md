# InteliJ Custom Plugins

# Index

1. [Introduction](#introduction)
    * [Plugin Ecosystem](#plugin-ecosystem)
2. [Plugin Development](#plugin-development)
    * [Project Structure](#project-structure)
    * [Basic environment setup](#environment-setup)
    * [Understanding the ```plugin.xml``` file](#plugin-xml)
3. [Links](#links)
4. [License](#license)

<a name="introduction"></a>

## Introduction
IntelliJ is an open source Java Integrated Development Environment (IDE) licensed under [Apache 2.0](https://tldrlegal.com/license/apache-license-2.0-(apache-2.0)), created in 2001, developed and maintained by JetBrains. As an IDE it offers, among others, the following features:

| #  | Feature                               | Explanation                                                                                                                                                                                                                                                                                                                        |
|----|---------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 01 | Coding assistant                      |  Code completion through context analysis; code navigation; code refactoring; smells highlight through Lint tool; _Supported languages for community editions are: Java, Groovy, Kotlin, Scala (plugin), Erlang (plugin), Go (Plugin), Dart (plugin), XML, JSON, YAML, AsciiDoc and Markdown (plugin), Python and Jython (plugin)_ |
| 02 | Built in tools and integration        | Integration with grunt, bower, gradle and SBT among others                                                                                                                                                                                                                                                                                      |
| 03 | Version control tools and integration | Integration with SVN, git, Mercurial and Perforce among others                                                                                                                                                                                                                                                                                  |
| 04 | Database integration                  | Microsoft SQL Server, ORACLE, PostgreSQL and MySQL can be accessed from within the IDE                                                                                                                                                                                                                                             |
| 05 | Plugin ecosystem                     | Allows the development of code that adds functionalities to the IDEA. It counts with a market place where these plugins can be commercialized, downloaded and installed in an easy way                                                                                                                                             |

_**source:** https://en.wikipedia.org/wiki/IntelliJ_IDEA_

<a name="plugin-ecosystem"></a>

### Plugin Ecosystem
As it can be seen in feature's description, IntelliJ offers a large number of its features through plugins. Plugins are nothing more than modules inside IDE's source code that interacts with it in order to extend or provide features. Adding new plugins can modify the software to the point that it can be distributed and commercialized, under a new label. Below is a list of some derived versions of IntelliJ:
 - [Android Studio](https://developer.android.com/studio/index.html): developed by google, this IntelliJ version supports Android development;
 - [PyCharm](https://www.jetbrains.com/pycharm/): IntelliJ version focused in improving productiveness for Python based projects;
 - [WebStorm](https://www.jetbrains.com/webstorm/): IntelliJ version focused on front-end technologies such as JavaScript and TypeScript;

Plugins can also be distributed through a market place that can be accessed from within the IDE itself. There, developers can post their work for free or charge for it. The use of plugins is an excellent way to customize the software so it can support complex projects offering maximum productiveness.

<a name="plugin-development"></a>

## Plugin Development
_Disclaimer: the official IntelliJ documentation can be found [here](https://www.jetbrains.org/intellij/sdk/docs/welcome.html). It will contain the latest information about IDE development and must be the primary source of knowledge about this environment._

As described in IDE features, there are different types of plugin that can fulfill different tasks, for example:
 - **Language support:** adds the ability to understand a new language, highlighting reserved words, allowing code refactor and recognizing file extensions;
 - **Framework integration:** improves suggestions, warning, and build for specific frameworks, i.e. Kotlin plugin that recognizes specific language features inside android studio (understanding that a name given in a res file can be used inside code with no need for linking it via ```findById```)
 - **User Interface add-ons:** uses the code editing screen space to display some graphic information, usually, derived from an existing IDE project, for instance, generating a UML Class Diagram from a project's existing classes;
 - **Tool integration plugins:** allows the control of third party tools from within the IDE, i.e. gradle plugin, database control plugins, etc.

Each one of these plugin has its own development particularities and this file doesn't aim to describe a walk through for all of them.

<a name="project-structure"></a>

### Project Structure
_**Disclaimer:** This section is a copy and paste of [this](https://www.jetbrains.org/intellij/sdk/docs/basics/project_structure.html) page from the official documentation_

 - **Project**: encapsulates the source code, libraries and build instructions into a single organization unit. It is composed by multiples modules. Anything an user does while using the IDE is performed within the context of a project;
 - **Module**: a discrete unit of functionalities that can be run, tested and debugged independently. Includes such things as source code, build scripts, unit tests and deployment descriptors. Each module can use a specific SDK or inherit SDK defined on the project level. A module can depend on other modules of the project;
 - **Library**: archive of compiled code (such as JAR files) that modules depend on. It can be of module, project or global levels.;
 - **SDK**: stands from Software Development Kit (SDK) - for Java projects, SDK is referred to as JDK (Java Development Kit). It determines which API library is used to build the project;
 - **Facet**: represents a configuration, specific for a particular framework/technology, associated with a module. A module can have multiple facets. E.g. Spring specific configuration is stored in a Spring facet.

<a name="environment-setup"></a>

### Basic environment setup
In order to develop plugins the IDE itself can be used. For that, start with the following procedure:
 1. Create a directory called ```intellik-idea```;
 2. Navigate to the newly create directory and clone the following project:
     - [IntelliJ Community source code](https://github.com/JetBrains/intellij-community);
     - [IntelliJ Documentation and sample plugins](https://github.com/JetBrains/intellij-sdk-docs);
 3. Run ```getPlugins.sh``` from the cloned source code directory;
 3. Open an instance of default IntelliJ and open the cloned source code as a project;
     - Install missing plugins if needed;
 4. Inside Project Structure Dialog (```⌘ + ;``` on MaxOS):
     - Configure a JSDK named “IDEA jdk” (case sensitive), pointing to an installation of JDK 1.8;
     - Unless you’re running on a Mac with an Apple JDK, add ```/lib/tools.jar``` to the set of “IDEA jdk” jars;
     - Configure a JSDK named “1.8”, pointing to an installation of JDK 1.8;
     - Add ```/lib/tools.jar``` to the set of “1.8” jars.
 5. Use ```Use Build | Make Project``` to build the code;
 6. Close the project and open Documentation and Sample;
 7. Open Project Structure Dialog:
     - Create a new IntelliJ Platform SDK and point it to the default InteliJ installation folder - from where the current active instance is running. _P.S.: it is not the directory cloned on step 2_. On Mac you can find it inside ```Applications``` folder, in Windows it is likely to find it under ```C:/Program Files```;
     - In the **Sourcepath** tab of the SDK settings, click the **Add** button and choose the cloned source code of IntelliJ (done in step 2). Add all recognized files;
     - select the newly created IntelliJ Platform SDK as the default SDK for the plugin module;

If everything is right it should be able to navigate to ```code_samples```, open some file inside ```src``` package and check that all imports are recognized, making possible to navigate to them.

<a name="plugin-xml"></a>

### Understanding the ```plugin.xml``` file
Specifies the plugin name, description, version, vendor, the supported IntelliJ IDEA version, plugin components, actions and action groups, and action user interface placement. Below is a brief description of the most important elements of ```plugin.xml```:
  - ```<id>```: company unique id;
  - ```<name>```: plugin display name;
  - ```<version>```: plugin version;
  - ```<vendor>```: fill in the attributes ```email``` with company support address, ```url``` with company's URL and the element value with company name;
  - ```<description>```: short plugin description that accepts HTML marks;
  - ```<change-notes>```: short description of change notes for this version. It also accepts HMTL entries as long as they're encapsulated inside ```<![CDATA[]]>``` tag;
  - ```<depends>```: specify that a plugin depends on one or more other plugins. In this case the class loaders of those plugins will be used for classes not found in the current plugin. This allows a plugin to reference classes from other plugins;
  - [```<xpto-component>```](https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_components.html): fundamental concept of plugin integration; they can be: application, project or module level, each one of them being created at an specific time. In order to create a new component, it must to be first declared as a pair Interface/Implementation through the ```<level-component>``` annotation of ```plugin.xml```. The access happens through ```getComponent(Class)``` - _note that the state of components can be persisted since it implements the proper interface interface_;
  - ```<extensions```: allows the underdevelopment plugin to access other plugin's functionalities;
  - ```<extensionPoint(s)>```: allows other plugins to extend the underdevelopment plugin's functionalities;
    - **service**:  a plugin component loaded on demand, when the underdevelopment plugin calls the ```getService()``` method of the ```ServiceManager``` class;
  - ```<action>```: a class, derived from ```AnAction``` class, whose ```actionPerformed()``` method is called when the menu item or toolbar button is selected;

<a name="links"></a>

## References & Useful Links
 - [Official IntelliJ Documentation;](https://www.jetbrains.org/intellij/sdk/docs/welcome.html)
 - [IntelliJ Community Edition source code;](https://github.com/JetBrains/intellij-community)
 - [IntelliJ Documentation and Sample Plugins](https://github.com/JetBrains/intellij-sdk-docs)

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
