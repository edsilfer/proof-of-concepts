# Android Custom Lint Rules

# Index
1. [Introduction](#intro)
    - [Basic Concepts](#concepts)
2. [Tutorial](#tutorial)
    - [Create](#tuto-create)
    - [Testing Detector and Checkers](#tuto-test)
        - [Approach 01: LintDetectorTest](#tuto-test-app01)
        - [Approach 02: Lint JUnit Rule](#tuto-test-app02)
3. [Known Issues](#issues)
4. [Glossary](#glossary)
5. [References & Useful Links](#references)
6. [License](#license)

<a ref="intro"></a>

## Introduction
There are many reason why one should consider writing Custom Lint Rule for his project, two of them worth mentioning:
1. **When releasing libraries to be used by third developers:** lint is a powerful mechanism to enforce users to correctly use your code;
2. **Team with not experiences members in a certain technology:** in this case, good practices acquired along the way can be documented in the shape of custom rules - ensuring that they will be effectively shared;

For further information on these topics, read the articles from REF002 and REF003;

<a ref="concepts"></a>

### Basic Concepts
Before jumping into the tutorial, there are a few concepts to be understood:
 - **Issue**: an error/problem/smell/bad practice present in xml, java or configuration files that composes the project under analysis. This is exactly what Lint looks for while scanning projects;
 - **Detector**: responsible for screening files that might contain issues. It also send those files as input for checkers;
 - **Checker**: verifies if a given input contains issues - raising them once they're found;
 - **Registry**: component that registers all issues for which Lint should be looking for;

<a ref="tutorial"></a>

## Tutorial

<a ref="tuto-create"></a>

### Create
1. Create a new Android Project;
2. Create a new Java Library Module - _Custom Lint Rules are packaged into .jar libraries once they are ready, therefore the easiest way to implement them using them is inside a Java Module Library_;
3. On module's [```build.gradle```]():
   - add target and source compatibility to Java 1.7;
   - add dependencies for lint-api, lint-checks and test dependencies;
   - add jar packing task containing two attributes: ```Manifest-Version``` and ```Lint-Registry```, set the first to 1.0 and the second as the full path to a class that will later on contain the issue's catalog;
   - add a default tasl ```assemble```;
   - **[OPTIONAL]:** add a task that will copy the generated .jar into ```~/.android/lint```;
4. Check REF001 and choose a Detector that best suits your needs, based on it create and implement a class to fulfill the Detector's role;
5. Still based on REF0001 chosen file, create and implement a Checker class, later referring to it inside Detector's ```createJavaVisitor()``` method;
   - _for the sake of SRP, do not place Checker in the same file of Detector's class_;
6. Copy the generated .jar file from ```build/lib``` to ```~/.android/lint``` - _if you added a task on ```build.gradle``` that does this you can skip this step_;
7. Restart the computer - once created and moved into ```~/.android/lint```, the Custom Rules should be read by Lint next time the program starts. In order to have the alert boxes inside Android Studio, it is enough to _invalidate caches and restart_ the IDE, however, to have your custom rules caught on Lint Report when ```./gradlew check```, it might be necessary to restart your computer;

<a ref="tuto-test"></a>

### Testing Detectors and Checkers
Testing Custom Rules is not an easy task to do - mainly due the lack of documentation for official APIs. This section will present two approaches for dealing with this. The main goal of this project is to create custom rules that will be run against real files, therefore, test files will be necessary for testing them. They can be places in ```src/test/resources``` folder from your Lint Java Library Module;

<a ref="tuto-test-app01"></a>

#### Approach 01: LintDetectorTest
1. Make sure you've added all test dependencies - checkout [sample project's](https://github.com/edsilfer/proof-of-concepts/blob/master/android-custom-lint/lint-rules/build.gradle) ```build.gradle```;
2. Copy [```EnhancedLintDetectorTest.java```](https://github.com/edsilfer/proof-of-concepts/blob/master/android-custom-lint/lint-rules/src/test/java/br/com/edsilfer/lint_rules/util/EnhancedLintDetectorClass.java) and [```FileUtils.java```](https://github.com/edsilfer/proof-of-concepts/blob/master/android-custom-lint/lint-rules/src/test/java/br/com/edsilfer/lint_rules/util/FileUtil.java) into your project's test directory;
    - There is a known bug with Android Studio that prevents it from seeing files from ```src/test/resources``` folder, these files are a workaround for that;
    - ```EnhancedLintDetectorTest.java``` should return all issues that will be subject of tests. A nice way to do so is getting them from Issue Registry;
3. Create a test class that extends from ```EnhancedLintDetectorTest.java```;
4. Implement ```getDetector()``` method returning an instance of the Detector to be tested;
5. Use ```lintFiles("test file path taking resources dir as root")``` to perform the check of the Custom Rules and use its result object to assert the tests;

_Note that ```LintDetectorTest.java``` derives from ```TestCase.java```, therefore, you're limited to JUnit 3._

<a ref="tuto-test-app02"></a>

#### Approach 02: Lint JUnit Rule
You might have noticed that Approach 01 might be a little overcomplicated, despite the fact that you're limited to JUnit 3 features. Because of that [GitHub user a11n](https://github.com/a11n) created a [Lint JUnit Rule](https://github.com/a11n/lint-junit-rule) that allows the test of Custom Lint Rules in a easier way that counts with JUnit 4 and up features. Please, refer to his project [README.md](https://github.com/a11n/lint-junit-rule/blob/master/README.md) for details about how to create tests using this apprach.

_Currently, [Lint JUnit Rule](https://github.com/a11n/lint-junit-rule) do not correct the root dir for test files and you might no be able to see the tests passing from the IDE - however it works when test are run from command line. An [issue](https://github.com/a11n/lint-junit-rule/issues/17) and [PR](https://github.com/a11n/lint-junit-rule/pull/16) were created in order to fix this bug._

<a ref="issues"></a>

## Known Issues
| # 	| Description                                                                                                                	| Error Message            	| How can I reproduce?                                                                                                           	| Fix                                                                                                                                                                                                                                                                                                                                                                                       	|
|---	|----------------------------------------------------------------------------------------------------------------------------	|--------------------------	|--------------------------------------------------------------------------------------------------------------------------------	|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| 1 	| After copying generated .jar file into ```~/.android/lint```, Android Studio doesn't show alerting boxes.                  	| -                        	| Generate a .jar file with Custom Lint Rules and copy it to ```~/.android/lint```                                               	| Clear caches and restart Android Studio.                                                                                                                                                                                                                                                                                                                                                  	|
| 2 	| After copying generated .jar file into ```~/.android/lint```, ```./gradlew check``` doesn't add Custom Rules to its report 	| -                        	| Generate a .jar file with Custom Lint Rules and copy it to ```~/.android/lint```                                               	| Restart computer                                                                                                                                                                                                                                                                                                                                                                          	|
| 3 	| Unable to locate test file from test resource folder                                                                       	| File XPTO was not found. 	| Place a test file (XML or JAVA) into ```src/test/resources``` and attempt to use it from a JUnit like in ```lintFiles(file)``` 	| If using Test Approach 01, make sure you test file descents from [```EnhancedLintDetectorClass.class```](https://github.com/edsilfer/proof-of-concepts/blob/master/android-custom-lint/lint-rules/src/test/java/br/com/edsilfer/lint_rules/util/EnhancedLintDetectorClass.java). If using Test Approach 02, check out [pull request #16](https://github.com/a11n/lint-junit-rule/pull/16) 	|


<a ref="issues"></a>

## Glossary
 > - **[Lint](https://developer.android.com/studio/write/lint.html)**: _lint was the name originally given to a particular program that flagged some suspicious and non-portable constructs (likely to be bugs) in C language source code. The term is now applied generically to tools that flag suspicious usage in software written in any computer language;_

 - **SRP**: Single Responsibility Principle, [S.O.L.I.D.](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design));

<a ref="references"></a>

## References and Useful Links
 - **REF001**: [Source code of Android's default detectors;](https://android.googlesource.com/platform/tools/base/+/master/lint/libs/lint-checks/src/main/java/com/android/tools/lint/checks);
 - **REF002**: [Building Custom Lint Checks in Android (2016), by Adam Compton](https://www.bignerdranch.com/blog/building-custom-lint-checks-in-android/);
 - **REF003**: [Writing custom lint rules and integrating them with Android Studio inspections (2016), by Adam Buick](https://android.jlelse.eu/writing-custom-lint-rules-and-integrating-them-with-android-studio-inspections-or-carefulnow-c54d72f00d30)

<a ref="license"></a>

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
