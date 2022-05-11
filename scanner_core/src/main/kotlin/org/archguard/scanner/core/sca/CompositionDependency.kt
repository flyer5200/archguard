package org.archguard.scanner.core.sca

import kotlinx.serialization.Serializable

@Serializable
data class CompositionDependency(
    val id: String,
    val name: String,
    val version: String,
    val parentId: String,
    val packageManager: String,
    val depName: String,
    val depGroup: String,
    val depArtifact: String,
    val depMetadata: String = "",
    val depSource: String = "",
    val depScope: String,
    val depVersion: String
)
