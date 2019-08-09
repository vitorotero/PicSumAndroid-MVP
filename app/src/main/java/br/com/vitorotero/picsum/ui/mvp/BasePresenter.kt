package br.com.vitorotero.picsum.ui.mvp

import io.reactivex.disposables.Disposable

interface BasePresenter {
    fun detachView()
    fun addDisposable(disposable: Disposable)
}