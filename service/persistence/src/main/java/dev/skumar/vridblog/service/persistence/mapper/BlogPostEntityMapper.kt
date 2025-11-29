package dev.skumar.vridblog.service.persistence.mapper

import dev.skumar.mcq.base.util.mappers.EntityMapper
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.service.persistence.db.blog.BlogPostEntity


class BlogPostEntityMapper: EntityMapper<BlogPostEntity, BlogPost> {


    override fun mapFromEntity(entity: BlogPostEntity): BlogPost {
        return BlogPost(
            id = entity.id,
            featuredMediaUrl = entity.featureMediaUrl,
            title = entity.title,
            shortDescription = entity.shortDescription,
            isShortDescriptionProtected = entity.isShortDescriptionProtected,
            publishedOn = entity.publishedOn,
            onlineLink = entity.onlineLink,
            content = entity.content,
            isContentProtected = entity.isContentProtected
        )
    }


    override fun mapToEntity(model: BlogPost): BlogPostEntity {
        return BlogPostEntity(
            id = model.id,
            featureMediaUrl = model.featuredMediaUrl,
            title = model.title,
            shortDescription = model.shortDescription,
            isShortDescriptionProtected = model.isShortDescriptionProtected,
            publishedOn = model.publishedOn,
            onlineLink = model.onlineLink,
            content = model.content,
            isContentProtected = model.isContentProtected
        )
    }


}