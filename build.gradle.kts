plugins {
    idea
    eclipse
    `java-library`
    `maven-publish`
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.spotless)
}

allprojects {
    apply(plugin = "maven-publish")

    publishing {
        setupRepositories(repositories)
    }

    tasks.withType<GenerateModuleMetadata> {
        enabled = false
    }

    apply(plugin = "java-library")
    apply(plugin = "fabric-loom")
    apply(plugin = "com.diffplug.spotless")

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(17)
    }

    java {
        withSourcesJar()
    }

    loom {
        splitEnvironmentSourceSets()
    }

    sourceSets {
    }

    loom {
        runtimeOnlyLog4j.set(true)
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.parchmentmc.net/")
        maven("https://maven.nucleoid.xyz")
        maven("https://api.modrinth.com/maven") {
            content {
                includeGroup("maven.modrinth")
            }
        }
    }

    dependencies {
        minecraft(rootProject.libs.minecraft)
        mappings(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-1.19.3:${rootProject.libs.versions.parchment.get()}@zip")
        })

        modImplementation(rootProject.libs.fabric.loader)
        modImplementation(rootProject.libs.fabric.api)
    }

    tasks.withType<ProcessResources>().configureEach {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(
                mutableMapOf("version" to project.version)
            )
        }
    }

    spotless {
        java {
            importOrder("java|javax","","net.minecraft","net.fabricmc","dev.phomc","\\#")

            removeUnusedImports()
            cleanthat()
            formatAnnotations()

            licenseHeaderFile(rootProject.file("HEADER"))
        }
    }

    tasks.withType<AbstractArchiveTask> {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }

    task<net.fabricmc.loom.task.ValidateMixinNameTask>("validateMixinName") {
        source(sourceSets.main.get().output)
    }
}

subprojects {
    publishing {
        publications {
            register("mavenJava", MavenPublication::class) {
                artifact(tasks.remapJar) {
                    builtBy(tasks.remapJar)
                }

                artifact(tasks.remapSourcesJar) {
                    builtBy(tasks.remapSourcesJar)
                }
            }
        }
    }
}

fun setupRepositories(repositories: RepositoryHandler) {
}
