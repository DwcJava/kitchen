# kitchen

This is where we're brewing new stuff. We use this repo to prepare new components for early adopters, before they go to the core engine - mostly when
we're still in the phase of discovery.

Any component you see in this repo may be discontinued at any time, either because it was a bad idea or when we moved it to the engine.

We will deprecate classes early to give you time to shop for alternatives, though, or adjust your code to loading the final component once it's made it to the engine.

Please note that these components are not production-ready. We will only publish snapshot builds on Central to underline the experimental character of the controls in this repo!.

Put this in your settings.xml to enable snapshots in your project:

```xml
<profiles>
    <profile>
        <id>allow-snapshots</id>
        <activation><activeByDefault>true</activeByDefault></activation>
        <repositories>
            <repository>
                <id>snapshots-repo</id>
                <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
                <releases><enabled>false</enabled></releases>
                <snapshots><enabled>true</enabled></snapshots>
            </repository>
        </repositories>
    </profile>
</profiles>
```

Then, reference the kitchen components from your project by adding 

```xml
<dependency>
    <groupId>org.dwcj</groupId>
    <artifactId>dwcj-kitchen</artifactId>
    <version>23.03-SNAPSHOT</version>
</dependency>
```