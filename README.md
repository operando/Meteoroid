# Meteoroid

Uploading files to Slack. Simple Java Library. 

# How to use

```java
File uploadFile = new File(..);

Response response = new Meteoroid.Builder()
	.token("your slack api token")
	.uploadFile(uploadFile)
	.channels("#general")
	.title("test titke")
	.initialComment("test comment")
	.build()
	.post();
```

or

```java
File uploadFile = new File(..);

new Meteoroid.Builder()
	.token("your slack api token")
	.uploadFile(uploadFile)
	.channels("#general")
	.title("test titke")
	.initialComment("test comment")
	.build()
	.post(callback);
```


## Dependencies

* [okhttp](https://github.com/square/okhttp)
 * Http Client

## Download

Gradle:
```groovy
allprojects {
    repositories {
        jcenter()
        maven {
            url "https://dl.bintray.com/operandoos/maven/"
        }
    }
}

compile 'com.os.operando.meteoroid:meteoroid:1.2.0'
```
