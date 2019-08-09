package br.com.vitorotero.picsum.shared.manager

import br.com.vitorotero.picsum.shared.model.Photo
import io.reactivex.Observable

interface PhotoManager {
    fun listPhotos(): Observable<List<Photo>>
}
