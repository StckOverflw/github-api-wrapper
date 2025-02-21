rootProject.name = "github-api-wrapper"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "core",
    "repositories",
    "repositories:branches",
    "bom",
    "full",
    "repositories:autolinks",
    "repositories:collaborators",
    "repositories:comments",
    "repositories:commits",
    "repositories:community"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("ktor", "1.6.3")
            alias("ktor-client-core").to("io.ktor", "ktor-client-core").versionRef("ktor")
            alias("ktor-client-okhttp").to("io.ktor", "ktor-client-okhttp").versionRef("ktor")
            alias("ktor-client-js").to("io.ktor", "ktor-client-js").versionRef("ktor")
            alias("ktor-client-auth").to("io.ktor", "ktor-client-auth").versionRef("ktor")
            alias("ktor-client-mock").to("io.ktor", "ktor-client-mock").versionRef("ktor")
            alias("ktor-client-serialization").to("io.ktor", "ktor-client-serialization").versionRef("ktor")
            alias("kotlinx-serialization-json").to("org.jetbrains.kotlinx", "kotlinx-serialization-json")
                .version("1.2.2")
            alias("kotlinx-datetime").to("org.jetbrains.kotlinx", "kotlinx-datetime").version("0.2.1")
        }

        create("test") {
            version("junit", "5.7.2")
            alias("junit5").to("org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
        }
    }
}

val isCi = System.getenv("GITHUB_ACTIONS") == "true"

buildCache {
    remote<HttpBuildCache> {
        url = uri("https://gradle-cache.nycode.de/cache/")
        isPush = isCi
        credentials {
            username = System.getenv("GRADLE_CACHE_USER")
            password = System.getenv("GRADLE_CACHE_PASSWORD")
        }
    }
}
