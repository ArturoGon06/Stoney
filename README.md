# Stoney

Fabric mod for Minecraft Java 26.3. Flowing lava meeting water generates the matching block when granite, diorite, andesite, calcite, deepslate or tuff is directly beneath the normal output. The reference block is not consumed. Obsidian and basalt generation are unchanged.

Install Fabric Loader 0.19.5 or newer and Fabric API for 26.3. Place the built JAR in the mods folder on the server (and on clients for single-player). On first launch, `config/stoney.properties` is created. Set any variant to `false` and restart to restore its vanilla output.

Build with JDK 25: `.\gradlew.bat build` on Windows or `./gradlew build` elsewhere. The mod JAR appears in `build/libs`.

Status: The sources compile with standalone javac against the 26.3 client JAR, but the Gradle build and in-game behavior have not been verified. Loom failed to prepare its Minecraft JARs with a ReadOnlyFileSystemException in this environment.
