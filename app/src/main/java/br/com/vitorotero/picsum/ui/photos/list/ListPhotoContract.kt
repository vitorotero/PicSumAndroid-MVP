package br.com.vitorotero.picsum.ui.photos.list

import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.mvp.BasePresenter
import br.com.vitorotero.picsum.ui.mvp.BaseView

interface ListPhotoContract {

    interface View : BaseView {
        fun setList(photos: List<Photo>)
    }

    interface Presenter : BasePresenter {
        fun listPhotos()
    }

}