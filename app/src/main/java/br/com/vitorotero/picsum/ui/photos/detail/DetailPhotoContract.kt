package br.com.vitorotero.picsum.ui.photos.detail

import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.mvp.BasePresenter
import br.com.vitorotero.picsum.ui.mvp.BaseView

interface DetailPhotoContract {

    interface View: BaseView {
        fun prepareLayout(photo: Photo)
    }

    interface Presenter: BasePresenter {
        fun getPhoto()
    }

}
