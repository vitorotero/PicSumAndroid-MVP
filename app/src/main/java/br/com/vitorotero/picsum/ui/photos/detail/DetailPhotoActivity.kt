package br.com.vitorotero.picsum.ui.photos.detail

import android.content.Context
import android.content.Intent
import br.com.vitorotero.picsum.R
import br.com.vitorotero.picsum.shared.api.Api.Companion.API_PHOTO_URL
import br.com.vitorotero.picsum.shared.extensions.glide
import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.mvp.BaseActivity
import br.com.vitorotero.picsum.utils.DeviceUtils
import kotlinx.android.synthetic.main.detail_photo_layout.*
import kotlinx.android.synthetic.main.detail_photo_layout.toolBar
import kotlinx.android.synthetic.main.photo_list_layout.*

class DetailPhotoActivity : BaseActivity(), DetailPhotoContract.View {

    override var layoutRes: Int = R.layout.detail_photo_layout

    private lateinit var presenter: DetailPhotoContract.Presenter

    override fun initialize() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.detail_photo_activity_title)

        if (intent.hasExtra(PHOTO_ARGS)) {
            val photo = intent.getSerializableExtra(PHOTO_ARGS) as Photo
            presenter = DetailPhotoPresenter(photo, this)
            presenter.getPhoto()
        }

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun resume() {

    }

    override fun destroy() {
        presenter.detachView()
    }

    override fun prepareLayout(photo: Photo) {
        ivPhoto.glide(API_PHOTO_URL + photo.id)
        tvAuthorName.text = photo.author

        btnPageAuthor.setOnClickListener {
            DeviceUtils.openBrowserUrl(this, photo.authorUrl)
        }

        btnPagePhoto.setOnClickListener {
            DeviceUtils.openBrowserUrl(this, photo.postUrl)
        }
    }

    companion object {
        const val PHOTO_ARGS = "photo_args"

        fun getIntents(from: Context, photo: Photo): Intent {
            val intent = Intent(from, DetailPhotoActivity::class.java)
            intent.putExtra(PHOTO_ARGS, photo)
            return intent
        }
    }
}