package br.com.vitorotero.picsum.ui.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract var layoutRes: Int
    abstract fun initialize()
    abstract fun resume()
    abstract fun destroy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initialize()
    }

    override fun onResume() {
        super.onResume()
        resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
    }

}