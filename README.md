
# ToastJam Android Dependency

ToastJam is a dependency for showing beautiful toast messages with custom shapes and colors.
ToastJam lets you to simply customize messages showing in your app and make them look native and more belonging to your app rather than same boring default one.


![Screenshots](https://user-images.githubusercontent.com/55023827/123615624-2792b580-d80e-11eb-93f4-dc08261808ce.png) 


## How to integrate ToastJam into your project?

##### 1) Add the JitPack repository to the bottom of root build.gradle:
**For gradle:**
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**For maven:**
```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

##### 2) Add the following dependency to the project level build.gradle:
**For gradle:**
```
dependencies {
  implementation 'com.github.Ahlam-M:ToastJam:v2.0.0'
}
```

**For maven:**
```
<dependency>
  <groupId>com.github.Ahlam-M</groupId>
  <artifactId>ToastJam</artifactId>
  <version>v2.0.0</version>
</dependency>
```

## Usage
**Basic example:**
```
ToastJam.setup(this, "Hi there !").start();
```

**Full example with all available features:**
```
ToastJam.setup(this, "Hi there !")
        .setGravity(TGravity.BOTTOM)
        .setShape(TShape.ELLIPSE)
        .setTextColor(ContextCompat.getColor(this, R.color.text_color))
        .setColor(ContextCompat.getColor(this, R.color.background_color))
        .setDurationInSec(2)
        .start();
```


## Issues
Submit any issues here: [ToastJam Issues](https://github.com/Ahlam-M/ToastJam/issues)


## Feedback
If you have an experience using ToastJam, please feel free to share it with us through this form: [ToastJam Feedback Form](https://docs.google.com/forms/d/e/1FAIpQLSe-7PS6md5rEibiWRekf4BFCVmLrR2-67oBvNavRmUnvic1Qg/viewform)
Any suggestions are welcome.


## License
```
Licensed under the Apache License, Version 2.0
Reference: http://www.apache.org/licenses/LICENSE-2.0
```

