
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.axonframework:axon-spring-boot-starter")
    implementation(project(":core-api"))
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.axonframework:axon-test")
}
