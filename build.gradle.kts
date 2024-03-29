val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kgraphql_version: String by project
val koin_version: String by project
val kmongo_version: String by project
val bcrypt_version: String by project
plugins {
  application
  kotlin("jvm") version "1.8.10"
  id("io.ktor.plugin") version "2.2.3"
  id("com.github.johnrengelman.shadow") version "6.1.0"
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}

application{
  mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks.withType<Jar> {
  manifest {
    attributes(
      mapOf(
        "Main-Class" to application.mainClass
      )
    )
  }
}

group = "br.com.gabrielmorais"
version = "0.0.1"
application {
  mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation("org.koin:koin-ktor:$koin_version")
  implementation("org.litote.kmongo:kmongo:$kmongo_version")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("com.apurebase:kgraphql:$kgraphql_version")
  implementation("com.apurebase:kgraphql-ktor:$kgraphql_version")
  implementation("io.ktor:ktor-auth:$ktor_version")
  implementation("io.ktor:ktor-auth-jwt:$ktor_version")
  implementation("at.favre.lib:bcrypt:$bcrypt_version")
  testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")