package dev.skumar.vridblog.service.networking.mappers

import dev.skumar.mcq.base.util.mappers.DtoMapper
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.service.networking.dto.blog.BlogPostDto
import dev.skumar.vridblog.service.networking.dto.blog.Content
import dev.skumar.vridblog.service.networking.dto.blog.Excerpt
import dev.skumar.vridblog.service.networking.dto.blog.Title


class BlogPostDtoMapper: DtoMapper<BlogPostDto, BlogPost> {


    override fun mapFromDto(dto: BlogPostDto): BlogPost {
        return BlogPost(
            id = dto.id,
            featuredMediaUrl = dto.featuredMediaUrl,
            title = dto.title.rendered,
            shortDescription = dto.excerpt.rendered,
            isShortDescriptionProtected = dto.excerpt.protected,
            publishedOn = dto.date,
            onlineLink = dto.link,
            content = dto.content.rendered,
            isContentProtected = dto.content.protected
        )
    }


    override fun mapToDto(model: BlogPost): BlogPostDto {
        return BlogPostDto(
            id = model.id,
            featuredMediaUrl = model.featuredMediaUrl,
            title = Title(
                rendered = model.title
            ),
            excerpt = Excerpt(
                rendered = model.shortDescription,
                protected = model.isShortDescriptionProtected
            ),
            date = model.publishedOn,
            link = model.onlineLink,
            content = Content(
                rendered = model.content,
                protected = model.isContentProtected
            )
        )
    }


}