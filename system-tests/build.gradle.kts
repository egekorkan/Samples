/*
 *  Copyright (c) 2023 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

plugins {
    `java-library`
}

dependencies {
    implementation(libs.edc.transfer.spi)

    testImplementation(libs.edc.junit)
    testImplementation(libs.edc.json.ld)
    testImplementation(libs.awaitility)
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.restAssured)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit)
    testImplementation(libs.testcontainers.kafka)
    testImplementation(libs.kafka.clients)

    // runtimes
    testCompileOnly(project(":basic:basic-01-basic-connector"))
    testCompileOnly(project(":basic:basic-02-health-endpoint"))
    testCompileOnly(project(":basic:basic-03-configuration"))

    testCompileOnly(project(":transfer:transfer-00-prerequisites:connector"))
    testCompileOnly(project(":transfer:transfer-04-event-consumer:consumer-with-listener"))
    testCompileOnly(project(":transfer:transfer-04-event-consumer:listener"))
    testCompileOnly(project(":transfer:streaming:streaming-01-http-to-http:streaming-01-runtime"))
    testCompileOnly(project(":transfer:streaming:streaming-02-kafka-to-http:streaming-02-runtime"))

    testCompileOnly(project(":advanced:advanced-01-open-telemetry:open-telemetry-provider"))
    testCompileOnly(project(":advanced:advanced-01-open-telemetry:open-telemetry-consumer"))
}