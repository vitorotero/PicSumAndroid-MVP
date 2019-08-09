package br.com.vitorotero.picsum.ui.photos.detail

import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.mvp.BasePresenterImp

class DetailPhotoPresenter(private val photo: Photo, view: DetailPhotoContract.View) :
    BasePresenterImp<DetailPhotoContract.View>(view),
    DetailPhotoContract.Presenter {

    override fun getPhoto() {
        view?.prepareLayout(photo)
    }
}