package com.theo.mynews.models

import com.google.gson.annotations.SerializedName

data class MostPopular(
    val copyright: String,
    val num_results: Int,
    val results: List<Results>,
    val status: String
){
     class Results{
         var `abstract`: String? = null
         var adx_keywords: String?= null
         var asset_id: Long? = null
         var byline: String? = null
         var column: String? = null
         var count_type: String? = null
         //var des_facet: List<String>? = null
         var email_count: Int? = null
         var eta_id: Int? = null
         //var geo_facet: String? = null
         var id: Long? = null
         var media: List<Media>? = null
         var nytdsection: String? = null
         //var org_facet: List<String>? = null
         //var per_facet: List<String>? = null
         var published_date: String? = null
         var section: String? = null
         var source: String? = null
         var subsection: String? = null
         var title: String? = null
         var r_type: String? = null
         var updated: String? = null
         var uri: String? = null
         var url: String? = null
         class Media{
             var approved_for_syndication: Int? = null
             var caption: String? = null
             var copyright: String? = null
             @SerializedName("media-metadata")
             var media_metadata: List<MediaMetadata>? = null
             var subtype: String? = null
             var type: String? = null
             class MediaMetadata{
                 var format: String? = null
                 var height: Int? = null
                 var url: String? = null
                 var width: Int? = null
             }
         }

     }


}