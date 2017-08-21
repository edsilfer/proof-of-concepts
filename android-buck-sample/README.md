# Android Buck Sample

## Introduction
**[REF001]** Buck is a build system developed and used by Facebook. It encourages the creation of small, reusable modules consisting of code and resources, and supports a variety of languages on many platforms.
Speed up your builds. Buck builds independent artifacts in parallel to take advantage of multiple cores on your machine. Further, it reduces incremental build times by keeping track of unchanged modules so that the minimal set of modules is rebuilt.
 - **Add reproducibility to your builds**. Buck only uses the declared inputs, which means everybody gets the same results.
 - **Get correct incremental builds**. Buck looks at the contents of your inputs, not their timestamps to figure out what needs to be built. As a result, incremental builds should always be correct, so there's no need to perform a clean build.
 - **Understand your dependencies**. With buck query, you can better understand your dependencies and what is required to build your product.
 - **Integrate with your IDE**. With buck project, your project can be better understood by your IDE, making you and your team more productive.

## Purpose
This repository contains a simple project with instructions on how to build itself using [Uber's OkBuck Gradle Plugin](https://github.com/uber/okbuck).

### Why Buck?
| Build all Apps           | Gradle          | Buck          | Speed Up |
|--------------------------|-----------------|---------------|----------|
| Clean                    | 15 mins 38 secs | 1 min 58 secs | 8x       |
| Incremental - ABI change | 9 mins 15 secs  | 1 min 10 secs | 8x       |
| Incremental - Same API   | 10 mins 10 secs | 45 secs       | 14x      |
| No operation             | 1 min 24 secs   | 0.2 secs      | 420x     |

_**Source:** the information above was extracted from [Uber's presentation on Droidcon 2016](https://www.youtube.com/watch?v=j6CiHlapado)_

## Instructions
1. Make sure you have Buck installed and running **[REF002]**;
2. Navigate to the project folder via terminal;
3. [Optional] If not present, run ```./gradlew :buckWrapper``` in order to generate ```./buckw```;
4. Run ```./buckw build appDebug```;
5. Run ```./buckw install appDebug```, make sure you have a running emulator or connected device;
6. If everything went successfully you should see the application deployed on the running emulator/device;

## Known issues
| #  | Description                                 | Error Message                                                                                                                                                                                      | How can I reproduce?                               | Fix                                                                                                                                                                                                                                                                                                                                                                                                          |
|----|---------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 01 | Attempt to build a target                   |  - missing add-ons/google_api_2+...   - Different error messages thrown on console...                                                                                                              | run ```./buckw build appDebug```                   |  - List the current installed SDK targets with android list targets --compact and replace value of target inside module's .buckconfig for one of the printed values;   - Check the version of Android Gradle Plugin - last time checked there was no compatibility with version 3+;   - Check the version of android build tools version - last time checked there was no compatibility with buildTools 26+; |
| 02 | Attempt to list avdmanager targets          | "android" command is deprecated;                                                                                                                                                                   | run ```android list targets --compact;```          | Use ```$ANDROID_HOME/tools/bin/sdkmanager --list``` - consider adding this dir to the PATH;                                                                                                                                                                                                                                                                                                                  |
| 03 | Attempt to start emulator from command line | [140735833674688]:ERROR:./android/qt/qt_setup.cpp:28:Qt library not found at ../emulator/lib64/qt/libCould not launch '../emulator/qemu/darwin-x86_64/qemu-system-i386': No such file or directory | run ```emulator -avd Nexus_5X_API_26_Android_O_``` | - Run the command from inside $ANDROID_HOME/tools dir;   - add the following function do ~/.bashrc and restart it with source ~/.bashrc after editing the file: ```function emulator { cd "$(dirname "$(which emulator)")" && ./emulator "$@"; }```                                                                                                                                                          |

## References and Useful Links
 - **REF001**: [Buck official website](https://buckbuild.com);
 - **REF002**: [Buck Installation Guide](https://buckbuild.com/setup/getting_started.html);

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
