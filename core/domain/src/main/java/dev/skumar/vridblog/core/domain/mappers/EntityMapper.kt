package dev.skumar.mcq.base.util.mappers

interface EntityMapper <Entity, Model> {

    fun mapFromEntity(entity: Entity): Model

    fun mapToEntity(model: Model): Entity

}