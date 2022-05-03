## PLEASE NOTE: SPUPORT FOR JAVA 7 IS ENDING, AND THE CJWIZARD PROJECT'S SUPPORT POLICY IS CHANGING.
Going forward, each new CJWizard release will officially support usage of the library with all OpenJDK versions that have long-term support, PLUS the version that is current, at release time. This change will be effective with the next release of CJWizard.

# CJWizard

CJWizard is a library for creating swing based wizard dialogs in Java.

This library is meant to be an improvement on the Java Wizard library, formerly at https://wizard.dev.java.net/; that library offered a good framework for simple wizards, but it dictated a great deal of what you can do - justified by the java usability guidelines. However, not all of the restrictions  are justified by those guidelines. For example, it is particularly difficult to change the type of widget used on the left side of the wizards (it is initially an image with the list of wizard pages displayed over top of it). Making this area interactive, as a global navigation tool, is non-trivial.

In addition, it is extremely cumbersome to define complex wizards that change their branching behavior at runtime.  Generally, the Java Wizard API is difficult to use, and at times contradictory.

That said, we've learned a lot from the Java Wizard API, and some of those ideas have been incorporated into CJWizard.

Take a look at the [FAQ](src/site/markdown/FAQ.md) and [QuickStartGuide](src/site/markdown/quickstart.md) to get going, and send me questions if you have any.

## Screenshots

This shows CJWizard in action, with a custom PageTemplate (drawing the list of dialog pages on the left) that wraps around a !TitledPageTemplate (drawing the underlined title of the !WizardPage).  The Windows L&F was used to make it look like a native app.  (The Finish button is enabled because the surrounding app is robust to missing data, and allows for the user to come back to the wizard at a later date, if needed.  Generally, and by default, the Finish button is disabled if it is not explicitly enabled, which often happens in the `render()` method of the last !WizardPage)

![sample dialog](./src/site/resources/images/cjwizard1.png)

## Maven coordinates

CJWizard is now hosted in Lobos Studios' `java-dev` repository.

To use the repo with Gradle:

```groovy
repositories {
    mavenCentral()
    maven {
        url "https://repos.lobosstudios.com/repository/java-dev/"
    }
}

dependencies {
    // this is just an example; the other Maven artifacts are
    // still available, same as before.
    implementation "com.github.cjwizard:cjwizard:[VERSION]"
}
```

To use it with Maven:

```xml
    <repositories>
        <repository>
            <id>lobosstudios-nexus-java-dev</id>
            <name>Lobos Studios java-dev</name>
            <url>https://repos.lobosstudios.com/repository/java-dev/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.github.cjwizard</groupId>
            <artifactId>cjwizard</artifactId>
            <version>[VERSION]</version>
        </dependency>
    </dependencies>
```

***repos.lobosstudios.com runs on a Sonatype Nexus server. Repos hosted on Nexus require the slash at the end of the URL. Don't forget it...***

## Documentation

See [docs folder](./src/site/markdown)

## Requirements

To use this library you need

* Java 7 or later

## Dependencies

This library depends on

* slf4j-api
