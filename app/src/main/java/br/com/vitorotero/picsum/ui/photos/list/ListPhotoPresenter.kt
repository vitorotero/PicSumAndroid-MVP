package br.com.vitorotero.picsum.ui.photos.list

import br.com.vitorotero.picsum.shared.manager.PhotoManager
import br.com.vitorotero.picsum.ui.mvp.BasePresenterImp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListPhotoPresenter(private val photoManager: PhotoManager, view: ListPhotoContract.View) :
    BasePresenterImp<ListPhotoContract.View>(view),
    ListPhotoContract.Presenter {

    override fun listPhotos() {
        addDisposable(
            photoManager.listPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { photos -> view?.setList(photos) },
                    { })
        )
    }

}