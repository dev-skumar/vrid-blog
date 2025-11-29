package dev.skumar.vridblog.service.networking.routes

import io.ktor.resources.Resource


@Resource("/wp-json/wp/v2")
class RouteV2 {


    @Resource("posts")
    data class LoadPosts(
        val parent: RouteV2 = RouteV2(),
        val per_page: Int = 10,
        val page: Int
    )


}