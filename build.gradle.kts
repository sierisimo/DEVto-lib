import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
	id("org.jetbrains.dokka") version "0.10.1"

	`maven-publish`
}

group = "com.sierisimo"
version = "0.1.0"

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
		outputFormat = "html"
		outputDirectory = "$buildDir/javadoc"
	}

	val dokkaJar by creating(Jar::class) {
		group = JavaBasePlugin.DOCUMENTATION_GROUP
		description = "Assembles Kotlin docs with Dokka"
		archiveClassifier.set("javadoc")
		from(dokka)
	}

	artifacts {
		//add("archives", sourcesJar)
		add("archives", dokkaJar)
	}
}

publishing {
	publications {
		create<MavenPublication>("default") {
			from(components["java"])
			artifact(tasks.getByName("dokkaJar"))
		}
	}
	repositories {
		maven {
			url = uri("$buildDir/repository")
		}
	}
}

repositories {
	mavenCentral()
	jcenter()
	maven(url = "https://dl.bintray.com/arrow-kt/arrow-kt/")
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	implementation("io.github.openfeign", "feign-okhttp", "11.0")
	implementation("io.github.openfeign", "feign-gson", "11.0")
	implementation("io.github.openfeign", "feign-slf4j", "11.0")

	implementation("com.squareup.okhttp3", "logging-interceptor", "4.5.0")

	testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

	testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.4")
	testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.4")

	testImplementation("io.mockk:mockk:1.10.0")
}