package br.com.vitorotero.picsum.ui.photos.list

import android.app.ActivityOptions
import android.util.Pair
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import br.com.vitorotero.picsum.R
import br.com.vitorotero.picsum.shared.api.Api
import br.com.vitorotero.picsum.shared.api.ApiPhoto
import br.com.vitorotero.picsum.shared.manager.PhotoManagerImp
import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.mvp.BaseActivity
import br.com.vitorotero.picsum.ui.photos.detail.DetailPhotoActivity
import br.com.vitorotero.picsum.ui.photos.list.adapter.PhotoAdapter
import br.com.vitorotero.picsum.ui.photos.list.adapter.PhotoAdapterListener
import br.com.vitorotero.picsum.utils.DeviceUtils
import br.com.vitorotero.picsum.utils.TransictionsUtils
import kotlinx.android.synthetic.main.photo_list_layout.*
import kotlin.math.max

class ListPhotosActivity : BaseActivity(), ListPhotoContract.View, PhotoAdapterListener {

    private lateinit var presenter: ListPhotoContract.Presenter
    private val adapter = PhotoAdapter(emptyList(), this)
    private var revealX: Int = 0
    private var revealY: Int = 0

    override var layoutRes: Int = R.layout.photo_list_layout

    override fun initialize() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.list_photos_activity_title)

        val apiPhoto: ApiPhoto = Api().retrofit.create(ApiPhoto::class.java)
        presenter = ListPhotoPresenter(PhotoManagerImp(apiPhoto), this)

        setupTransaction()
        initRecyclerView()
    }

    override fun resume() {
        presenter.listPhotos()
    }

    override fun destroy() {
        presenter.detachView()
    }

    override fun setList(photos: List<Photo>) {
        adapter.setPhotos(photos)
    }

    override fun openUrlAuthor(url: String) {
        DeviceUtils.openBrowserUrl(this, url)
    }

    override fun openDetail(imageView: View, textView: View, photo: Photo) {
        val intent = DetailPhotoActivity.getIntents(this, photo)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this, Pair.create(imageView, "photoTransition"), Pair.create(textView, "nameAuthorTransition")
        )
        startActivity(intent, options.toBundle())
    }

    private fun setupTransaction() {
        if (intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) && intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)

            val viewTreeObserver = clContent.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        val radius = (max(clContent.width, clContent.height) * 1.1).toFloat()
                        TransictionsUtils.revealActivity(clContent, revealX, revealY, radius, 400)
                        clContent.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        }
    }

    private fun initRecyclerView() {
        rvPhotos.adapter = adapter
    }

    companion object {
        const val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
        const val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    }

}
