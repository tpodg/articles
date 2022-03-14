subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    apply(plugin = "idea")
    apply(plugin = "java")
}