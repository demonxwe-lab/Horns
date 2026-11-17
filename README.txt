HORNS MOD - Fabric 1.21.11 (client only)

This is the SOURCE project. It must be built into a .jar once:

1. Install JDK 21.
2. Open a terminal in this folder.
3. If there is no gradlew file: copy the "gradle" folder, "gradlew" and
   "gradlew.bat" from a fresh project made at https://fabricmc.net/develop/template/
   (or run "gradle wrapper" if you have Gradle installed).
4. Run:  ./gradlew build        (Windows: gradlew.bat build)
5. Your mod is build/libs/horns-1.0.0.jar  (NOT the -sources jar).
6. Put that jar + Fabric API (1.21.11) in your Lunar Fabric mods folder.

If Gradle says a version doesn't exist, update gradle.properties with the
values from https://fabricmc.net/develop

NO-INSTALL OPTION (GitHub):
Create a free GitHub repo, upload all files of this folder (including the
hidden .github folder), open the "Actions" tab, wait for "Build mod" to
finish, and download the "horns-jar" artifact. Unzip it to get horns-1.0.0.jar.
