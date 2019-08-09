package br.com.vitorotero.picsum.shared.api

import br.com.vitorotero.picsum.shared.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiPhoto {

    @GET("list")
    fun listPhotos(): Observable<List<Photo>>

}