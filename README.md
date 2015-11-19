# Meteoroid

Uploading files to Slack. Simple Java Library. 

# How to use

```java
File uploadFile = new File(..);

new Meteoroid.Builder()
	.token("your slack api token")
	.uploadFile(uploadFile)
	.channels("#general")
	.title("test titke")
	.initialComment("test comment")
	.build()
	.post(this);
```


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

compile 'com.os.operando.meteoroid:meteoroid:1.0.1'
```
