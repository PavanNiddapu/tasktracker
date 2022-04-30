import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.pavan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

val kotestVersion = "5.2.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	// Use JUnit Jupiter API for testing.
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")

	// Use JUnit Jupiter Engine for testing.
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")

	// This dependency is used by the application.
	implementation("com.google.guava:guava:29.0-jre")

	// https://mvnrepository.com/artifact/io.projectreactor/reactor-core
	implementation("io.projectreactor:reactor-core")

	testImplementation("io.mockk:mockk:1.10.6")
	testImplementation("io.mockk:mockk:1.10.0")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
