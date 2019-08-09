package br.com.vitorotero.picsum.ui.photo.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.photos.detail.DetailPhotoContract
import br.com.vitorotero.picsum.ui.photos.detail.DetailPhotoPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock

class DetailPhotoPresenterTest {

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    private val testScheduler = TestScheduler()

    @Mock
    lateinit var view: DetailPhotoContract.View

    private lateinit var presenter: DetailPhotoContract.Presenter

    private var photo = Photo(0, "", "", "", "", "")

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        view = mock()
        presenter = DetailPhotoPresenter(photo, view)
    }

    @After
    fun after() {
        presenter.detachView()
    }

    @Test
    fun test_photo_correct() {
        presenter.getPhoto()
        testScheduler.triggerActions()

        verify(view).prepareLayout(photo)
        verifyNoMoreInteractions(view)
    }
}