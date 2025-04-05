// Add compose gradle plugin
plugins {
    kotlin("multiplatform") version "2.0.20"
    kotlin("plugin.compose") version "2.0.20"
    id("org.jetbrains.compose") version "1.7.0-alpha03"
}
group = "io.github.theapache64.composedaisyui"
version = "1.0.0-alpha01"

// Add maven repositories
repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}


val postcss = "8.3.5"
val postcssLoader = "4.2.0"
val autoprefixer = "10.2.6"
val tailwind = "2.2.4"

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                scssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)


                implementation(npm("postcss", postcss))
                implementation(npm("postcss-loader", postcssLoader))
                implementation(npm("autoprefixer", autoprefixer))
                implementation(npm("tailwindcss", tailwind))
                implementation(npm("daisyui", "5.0.12"))
            }
        }
    }
}

val copyTailwindConfig = tasks.register<Copy>("copyTailwindConfig") {
    from("./tailwind.config.js")
    into("${rootProject.buildDir}/js/packages/${rootProject.name}-${project.name}")

    dependsOn(":kotlinNpmInstall")
}

val copyPostcssConfig = tasks.register<Copy>("copyPostcssConfig") {
    from("./postcss.config.js")
    into("${rootProject.buildDir}/js/packages/${rootProject.name}-${project.name}")

    dependsOn(":kotlinNpmInstall")
}

tasks.named("jsProcessResources") {
    dependsOn(copyTailwindConfig)
    dependsOn(copyPostcssConfig)
}

// Workaround for https://youtrack.jetbrains.com/issue/KT-49124
rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}
