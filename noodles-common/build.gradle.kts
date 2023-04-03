loom {
    accessWidenerPath.set(file("src/main/resources/noodles-common.accesswidener"))
}

dependencies {
    implementation(project(":noodles-api", "namedElements"))
}
