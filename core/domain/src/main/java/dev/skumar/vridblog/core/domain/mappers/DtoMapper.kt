package dev.skumar.mcq.base.util.mappers


interface DtoMapper <Dto, Model> {

    fun mapFromDto(dto: Dto): Model

    fun mapToDto(model: Model): Dto

}