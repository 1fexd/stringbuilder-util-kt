plugins {
    kotlin("jvm") version "1.9.24"
    java
    `maven-publish`
    id("net.nemerosa.versioning") version "3.1.0"
}

group = "fe.stringbuilder-util"
version = versioning.info.tag ?: versioning.info.full

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            version = project.version.toString()

            from(components["java"])
        }
    }
}
