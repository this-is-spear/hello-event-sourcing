plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

val axonVersion = "4.9.4"

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    group = "hello.tis"
    version = "1.0-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }

    dependencyManagement {
        imports {
            mavenBom("org.axonframework:axon-bom:$axonVersion")
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
