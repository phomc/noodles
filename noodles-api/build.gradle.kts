loom {
    accessWidenerPath.set(file("src/main/resources/noodles-api.accesswidener"))
}

dependencies {

    // MixinExtras
    modApi(rootProject.libs.mixinextras)?.let {
        annotationProcessor(it)
        include(it)
    }
}
