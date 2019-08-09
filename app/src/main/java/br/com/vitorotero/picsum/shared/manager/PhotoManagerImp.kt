package br.com.vitorotero.picsum.shared.manager

import br.com.vitorotero.picsum.shared.api.ApiPhoto
import br.com.vitorotero.picsum.shared.model.Photo
import io.reactivex.Observable

class PhotoManagerImp(private var apiPhoto: ApiPhoto) : PhotoManager {

    override fun listPhotos(): Observable<List<Photo>> {
        return apiPhoto.listPhotos()
    }

}
