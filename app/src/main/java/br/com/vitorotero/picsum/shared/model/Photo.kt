package br.com.vitorotero.picsum.shared.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("id") var id: Long,
    @SerializedName("format") var format: String,
    @SerializedName("filename") var fileName: String,
    @SerializedName("author") var author: String,
    @SerializedName("author_url") var authorUrl: String,
    @SerializedName("post_url") var postUrl: String

) : Serializable