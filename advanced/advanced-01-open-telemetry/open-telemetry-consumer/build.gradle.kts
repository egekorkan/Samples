import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *       ZF Friedrichshafen AG - add dependency
 *
 */

plugins {
    `java-library`
    id("application")
    alias(libs.plugins.shadow)
}

dependencies {

    implementation(libs.edc.control.plane.api.client)
    implementation(libs.edc.control.plane.api)
    implementation(libs.edc.control.plane.core)

    implementation(libs.edc.dsp)
    implementation(libs.edc.configuration.filesystem)
    implementation(libs.edc.vault.filesystem)

    implementation(libs.edc.iam.mock)
    implementation(libs.edc.management.api)
    implementation(libs.edc.transfer.data.plane)
    implementation(libs.edc.transfer.pull.http.receiver)

    implementation(libs.edc.data.plane.selector.api)
    implementation(libs.edc.data.plane.selector.core)
    implementation(libs.edc.data.plane.selector.client)

    implementation(libs.edc.data.plane.api)
    implementation(libs.edc.data.plane.core)
    implementation(libs.edc.data.plane.http)

    implementation(libs.edc.api.observability)
    implementation(libs.edc.auth.tokenbased)

    runtimeOnly(libs.edc.monitor.jdk.logger)
}

application {
    mainClass.set("org.eclipse.edc.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveFileName.set("consumer.jar")
}

tasks.register("copyOpenTelemetryJar", Copy::class) {
    val openTelemetry = configurations.create("open-telemetry")

    dependencies {
        openTelemetry(libs.opentelemetry)
    }

    from(openTelemetry)
    into("build/libs")
}

tasks.build {
    finalizedBy("copyOpenTelemetryJar")
}