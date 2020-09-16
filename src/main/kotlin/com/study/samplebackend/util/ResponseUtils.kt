package com.study.samplebackend.util

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

enum class ObjectStatus {
    CREATED, UPDATED, DELETED
}

data class ObjectStatusResource internal constructor(
    @JsonProperty("id")
    val objectId: String? = null,
    @JsonProperty("status")
    val objectStatus: ObjectStatus
) : RepresentationModel<ObjectStatusResource>()

fun createdResponse(
    id: Any? = null,
    linkBuilder: WebMvcLinkBuilder? = null
): ResponseEntity<ObjectStatusResource> {
    var responseBuilder = ResponseEntity.status(HttpStatus.CREATED)
    responseBuilder = linkBuilder
        ?.let { responseBuilder.location(it.toUri()) }
        ?: responseBuilder

    return responseBuilder.body(
        createdResponse(
            id?.let { it },
            ObjectStatus.CREATED,
            linkBuilder
        )
    )
}

fun updatedResponse(id: Any? = null, linkBuilder: WebMvcLinkBuilder? = null) =
    ResponseEntity.ok(createdResponse(id, ObjectStatus.UPDATED, linkBuilder))

fun deletedResponse(id: Any? = null, linkBuilder: WebMvcLinkBuilder? = null) =
    ResponseEntity.ok(createdResponse(id, ObjectStatus.DELETED, linkBuilder))

fun <T> okResponse(responseBody: T) = ResponseEntity.ok(responseBody)

private fun createdResponse(
    id: Any?,
    objectStatus: ObjectStatus,
    linkBuilder: WebMvcLinkBuilder? = null
): ObjectStatusResource {
    val response = ObjectStatusResource(id?.toString(), objectStatus)

    linkBuilder?.let { response.add(it.withSelfRel()) }

    return response
}
