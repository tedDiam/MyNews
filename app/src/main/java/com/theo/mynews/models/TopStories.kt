package com.theo.mynews.models

data class TopStories(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Results>,
    val section: String,
    val status: String
) {
    class Results {
        var `abstract`: String?= null
        var byline: String?= null
        var created_date: String?=null
        var des_facet: List<String>?= null
        var geo_facet: List<Any>?= null
        var item_type: String?=null
        var kicker: String?=null
        var material_type_facet: String?=null
        var multimedia: List<Multimedias>?=null
        var org_facet: List<String>?=null
        var per_facet: List<String>?=null
        var published_date: String?=null
        var section: String?=null
        var short_url: String?=null
        var subsection: String?=null
        var title: String?=null
        var updated_date: String?=null
        var url: String?=null

        class Multimedias {
            var caption: String? = null
            var copyright: String? = null
            var format: String? = null
            var height: Int? = null
            var subtype: String? = null
            var type: String? = null
            var url: String? = null
            var width: Int? = null
        }

    }

}