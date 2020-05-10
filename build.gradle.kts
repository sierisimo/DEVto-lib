import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.dokka") version "0.10.1"
    `maven-publish`
}

group = "com.sierisimo"
version = "0.1.4"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    withSourcesJar()
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    dokka {
        outputFormat = "javadoc"
        outputDirectory = "$buildDir/javadoc"
    }

    val dokkaJar by creating(Jar::class) {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        description = "Assembles Kotlin docs with Dokka"
        archiveClassifier.set("javadoc")
        from(dokka)
    }
}

publishing {
    repositories {
        maven {
            name = "local-publish"
            url = uri("$buildDir/repository")
        }

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sierisimo/devto-lib")
            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("USERNAME") ?: "") as String
                password = (project.findProperty("gpr.key") ?: System.getenv("TOKEN") ?: "") as String
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            afterEvaluate {
                artifact(tasks.getByName("dokkaJar"))
            }
        }
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.github.openfeign", "feign-okhttp", "11.0")
    implementation("io.github.openfeign", "feign-gson", "11.0")
    implementation("io.github.openfeign", "feign-slf4j", "11.0")

    implementation("com.squareup.okhttp3", "logging-interceptor", "4.5.0")

    //testImplementation("com.github.stephanenicolas.toothpick:toothpick-testing-junit5:3.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.4")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.4")
    testImplementation("io.kotest:kotest-property-jvm:4.0.4")

    testImplementation("io.mockk:mockk:1.10.0")
}