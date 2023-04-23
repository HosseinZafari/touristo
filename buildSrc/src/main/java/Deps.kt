import Deps.Version.constraint_compose
import Deps.Version.nav_version

object Deps {


    object Version {
        const val junit = "4.13.2"
        const val junit_test = "1.1.5"
        const val timber = "5.0.1"
        const val core_KTX = "1.9.0"
        const val lifecycle_runtime_ktx = "2.5.1"
        const val activity_compose = "1.6.1"
        const val compose_version  = "1.3.1"
        const val compose_material3 = "1.0.1"
        const val test_espresso_core = "3.5.1"
        const val appcompat = "1.4.2"
        const val nav_version = "2.5.3"
        const val room_runtime = "2.4.3"
        const val room_compiler = "2.4.3"
        const val room_ktx = "2.4.3"
        const val compose_runtime = "1.3.1"
        const val constraint_compose = "1.0.1"
        const val material_icons_extended = compose_version
        const val runtime_livedata = "1.3.3"
        const val appollo_graphql = "3.7.4"

    /*    const val ui_tooling = "1.2.1"
        const val compose_foundation = "1.1.0-rc03"
        const val material_icons_core = "1.2.1"
        const val material_icons_extended = "1.2.1"*/


    }

    const val appollo_graphql = "com.apollographql.apollo3:apollo-runtime:${Version.appollo_graphql}"
    const val junit = "junit:junit:${Version.junit}"
    const val constraint_compose = "androidx.constraintlayout:constraintlayout-compose:${Version.constraint_compose}"
    const val junit_test = "androidx.test.ext:junit:${Version.junit_test}"
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    const val core_KTX = "androidx.core:core-ktx:${Version.core_KTX}"
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle_runtime_ktx}"
    const val activity_compose = "androidx.activity:activity-compose:${Version.activity_compose}"
    const val compose_ui_ui = "androidx.compose.ui:ui:${Version.compose_version}"
    const val compose_ui_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:${Version.compose_version}"
    const val compose_material3 = "androidx.compose.material3:material3:${Version.compose_material3}"
    const val compose_material3_window_size_class = "androidx.compose.material3:material3-window-size-class:${Version.compose_material3}"
    const val test_espresso_core = "androidx.test.espresso:espresso-core:${Version.test_espresso_core}"
    const val compose_ui_ui_test_junit4 = "androidx.compose.ui:ui-test-junit4:${Version.compose_version}"
    const val compose_ui_ui_tooling = "androidx.compose.ui:ui-tooling:${Version.compose_version}"
    const val compose_ui_ui_test_manifest = "androidx.compose.ui:ui-test-manifest:${Version.compose_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val navigation_compose = "androidx.navigation:navigation-compose:${Version.nav_version}"
    const val room_runtime = "androidx.room:room-runtime:${Version.room_runtime}"
    const val room_compiler = "androidx.room:room-compiler:${Version.room_compiler}"
    const val room_ktx = "androidx.room:room-ktx:${Version.room_ktx}"
    const val compose_runtime = "androidx.compose.runtime:runtime:${Version.compose_runtime}"
    const val material_icons_extended =  "androidx.compose.material:material-icons-extended:${Version.material_icons_extended}"
    const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:${Version.runtime_livedata}"

   /* const val ui_tooling = "androidx.compose.ui:ui-tooling:${Version.ui_tooling}"
    const val compose_foundation = "androidx.compose.foundation:foundation:${Version.compose_foundation}"
    const val material_icons_core = "androidx.compose.material:material-icons-core:${Version.material_icons_core}"
    const val material_icons_extended = "androidx.compose.material:material-icons-extended:${Version.material_icons_extended}"
*/








}