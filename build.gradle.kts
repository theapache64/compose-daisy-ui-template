plugins {
    kotlin("multiplatform").version("2.1.0")
    kotlin("plugin.compose").version("2.1.0")
    id("org.jetbrains.compose").version("1.7.3")
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

                // Tailwind
                implementation(npm("tailwindcss", "4.1.3"))
                implementation(npm("@tailwindcss/postcss", "4.1.3"))

                // Post-CSS
                implementation(npm("postcss", "8.5.3"))
                implementation(devNpm("postcss-loader", "8.1.1"))
                implementation(devNpm("postcss-import", "16.1.0"))
                implementation(devNpm("style-loader", "4.0.0"))
                implementation(devNpm("css-loader", "7.1.2"))

                // DaisyUI
                // implementation(npm("daisyui", "5.0.12"))
            }
        }
    }
}
