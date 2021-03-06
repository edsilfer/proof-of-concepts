# Facebook Login on Android App
---
## Index
 * [Introduction](#introduction)
     - [Showcase](#showcase)
 * [Facebook Configuration](#fb-configuration)
 * [Android Configuration](#android-configuration)  
      - [Login with Facebook Button](#launchHomepage-fb-button)
      - [Check if user is already logged in](#check-logged)
      - [Graph API: Query user data](#fb-query-data)
 * [License](#license)
---

<a name="introduction"></a>

## Introduction
Whenever developing an application that requires an user to be signed in, one should analyze the possibility of using a social network as means of letting his client authenticate in the system. This is very useful once the developer may acquire a set of useful information from the user without having him to type it. Of course, each situation must be analyzed individually and this approach might not be feasible in all of them.

This project contains a sample application which allows authentication via Facebook and exhibits the retrieved information on a simple copy of Facebook User Profile.

<a name="showcase"></a>

### Showcase
<p align="center">
  <img src="showcase/showcase_001.jpg" align="center" width=200>
  <br /><br />
  <i><b>Figure 01:</b> Sample UI for this project</i>
</p>

<a name="fb-configuration"></a>

## Facebook Configuration
In order to use Facebook's SDK it is required the developer to fill in information about the app on Facebook page. Facebook back-end will only answer to requests generated by clients that matches the information provided in the app configuration phase.

1. Access [Facebook Application Dashboard](https://developers.facebook.com/apps/) and create a new application;
2. Access the settings table and fill in the following information:
   * application package;
   * application main activity - _a message will prompt alerting that Facebook coud not find the provided package on Google Play. Just hit 'Use provided package'_
   * platform (choose Android);
3. Run the command below to generate a key hash to identify your development machine. Paste the code on App Dashboard:

```bash
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
```
_Note that, sometimes the hash code generated via the command above might work for the emulator but not for other devices. If that's the case, run the piece of code below on App class ```onCreate()```method and add the printed hash on Facebook apps webpage:_

```Kotlin
object FacebookUtil {

    enum class Errors(val message: String) { HASH_NOT_FOUND("Hash code for application unable to be generated") }

    fun generatedHashCode(context: Context): String {
        @SuppressLint("PackageManagerGetSignatures")
        val info = context
                .packageManager
                .getPackageInfo(
                        "br.com.edsilfer.android_facebook_login",
                        PackageManager.GET_SIGNATURES
                )

        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            return Base64.encodeToString(md.digest(), Base64.DEFAULT)
        }

        return Errors.HASH_NOT_FOUND.message
    }

}
```
<a name="android-configuration"></a>

## Android configuration
 - Add Facebook SDK dependencies:

```Groovy
repositories {
  mavenCentral()
}

dependencies {
  def LIBRARY_VERSION_FACEBOOK_SDK = '[4,5)'
  implementation "com.facebook.android:facebook-android-sdk:$LIBRARY_VERSION_FACEBOOK_SDK"
}
```

 -  On ```strings.xml``` add required credentials for accessing the SDK - get the values from [Facebook App Dashboard](https://developers.facebook.com/apps/):
```xml
<string name="str_credentials_facebook_app_id">YOUR APP ID</string>
<string name="str_credentials_facebook_login_protocol_scheme">YOUR PROTOCOL SCHEME</string>
```

 - Add the internet permission and credentials on ```AndroidManifest.xml```:

```xml
<uses-permission android:name="android.permission.INTERNET"/>

<application android:label="@string/app_name">;
    <meta-data
      android:name="com.facebook.sdk.ApplicationId"
      android:value="@string/str_credentials_facebook_app_id" />

    <activity
      android:name="com.facebook.FacebookActivity"
      android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
      android:label="@string/app_name" />

    <activity
        android:name="com.facebook.CustomTabActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data android:scheme="@string/str_credentials_facebook_login_protocol_scheme" />
        </intent-filter>
    </activity>
</application>
```

 - [Optional] Add Facebook Login button to your application:

 ```xml
<com.facebook.launchHomepage.widget.LoginButton
    android:id="@+id/login_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center_horizontal"
    android:layout_marginTop="30dp"
    android:layout_marginBottom="30dp" />
 ```

<a name="launchHomepage-fb-button"></a>

### Login with Facebook button
In order to allow the Facebook button to work properly, not the following points:

 - Add the permissions and register the callback:
```Kotlin
buttonFacebookLogin.setReadPermissions(GraphAPISchema.ARG_USER_PERMISSIONS)
buttonFacebookLogin.registerCallback(callbackManager, presenter)
```
_Callback manager can be obtained as following: ```CallbackManager.Factory.create()```. Note that the presenter implements the interface: ```FacebookCallback<LoginResult>```._

 - Facebook starts a different Activity in order to allow user to authenticate, therefore, you must give the activity result back to Facebook API in order to proceed with the authentication:

```Kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    callbackManager.onActivityResult(requestCode, resultCode, data)
}
```

_For further details on how to use Facebook Login Button check the file ```LoginViewImpl```._

<a name="check-logged"></a>

### Check if user is already logged in
In order to check if user is already authenticated in the application do as follow: ```AccessToken.getCurrentAccessToken() != null```.

<a name="fb-query-data"></a>

### Graph API: Query user data
All query to user data is made through Facebook Graph APi. Acces ```GraphAPIDataSourceImpl``` and checkout how ```GraphRequest``` object was used to obtain ```id,name,email,gender,birthday,first_name,last_name,picture.type(large),about,cover,education,hometown,religion```user information.

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
