plugins {
    kotlin("multiplatform") version "1.9.21"
    id("org.jetbrains.compose") version "1.5.11"
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")

            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)

                // webpack
                implementation(npm("postcss", "8.4.31"))
                implementation(npm("postcss-loader","7.3.3"))
                implementation(npm("autoprefixer", "10.4.16"))
                implementation(npm("css-loader", "6.8.1"))
                implementation(npm("style-loader", "3.3.3"))
                implementation(npm("cssnano", "6.0.1"))

                // tailwind
                implementation(npm("tailwindcss", "3.3.5"))
                implementation(npm("@tailwindcss/typography", "0.5.10"))
                implementation(npm("@tailwindcss/forms", "0.5.7")) // optional

                implementation(npm("daisyui", "4.6.0"))

            }
        }
    }
}