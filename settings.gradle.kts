pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
    }
}

rootProject.name = "noodles"

include("noodles-api")

include("noodles-common")
