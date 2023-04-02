dependencies {

    // MixinExtras
    modApi(rootProject.libs.mixinextras)?.let {
        annotationProcessor(it)
        include(it)
    }
}
