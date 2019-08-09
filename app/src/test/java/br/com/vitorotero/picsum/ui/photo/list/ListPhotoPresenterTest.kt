package br.com.vitorotero.picsum.ui.photo.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.vitorotero.picsum.shared.api.ApiPhoto
import br.com.vitorotero.picsum.shared.manager.PhotoManager
import br.com.vitorotero.picsum.shared.model.Photo
import br.com.vitorotero.picsum.ui.photos.list.ListPhotoContract
import br.com.vitorotero.picsum.ui.photos.list.ListPhotoPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
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


class ListPhotoPresenterTest {

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    private val testScheduler = TestScheduler()

    @Mock
    lateinit var apiPhoto: ApiPhoto

    @Mock
    lateinit var photoManager: PhotoManager

    @Mock
    lateinit var view: ListPhotoContract.View

    private lateinit var presenter: ListPhotoContract.Presenter

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        photoManager = mock()
        apiPhoto = mock()
        view = mock()
        presenter = ListPhotoPresenter(photoManager, view)
    }

    @After
    fun after() {
        presenter.detachView()
    }

    @Test
    fun test_noDataOnApi_returnsEmptyList() {
        val empty: List<Photo> = emptyList()

        whenever(photoManager.listPhotos())
            .thenReturn(Observable.just(empty))

        presenter.listPhotos()
        testScheduler.triggerActions()

        verify(view).setList(empty)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun test_DataOnApi_returnsValues() {
        val photos = listOf(Photo(0, "", "", "", "", ""))
        val result = Observable.just(photos)

        whenever(photoManager.listPhotos())
            .thenReturn(result)

        presenter.listPhotos()
        testScheduler.triggerActions()

        verify(view).setList(photos)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun test_DataOnApi_returnsError() {
        val result = Throwable("Error")

        whenever(photoManager.listPhotos())
            .thenReturn(Observable.error(result))

        presenter.listPhotos()
        testScheduler.triggerActions()

        verifyNoMoreInteractions(view)
    }

}