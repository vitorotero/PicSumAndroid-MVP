package br.com.vitorotero.picsum.ui.photos.list.adapter

import android.view.View
import br.com.vitorotero.picsum.shared.model.Photo

interface PhotoAdapterListener {
    fun openUrlAuthor(url: String)
    fun openDetail(imageView: View, textView: View, photo: Photo)
}