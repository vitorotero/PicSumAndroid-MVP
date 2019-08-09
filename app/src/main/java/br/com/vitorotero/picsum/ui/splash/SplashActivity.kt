package br.com.vitorotero.picsum.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import br.com.vitorotero.picsum.R
import br.com.vitorotero.picsum.ui.photos.list.ListPhotosActivity
import kotlinx.android.synthetic.main.splash_layout.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        initViews()
    }

    private fun initViews() {
        btnGo.setOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, btnGo, "transition")
            val revealX = (btnGo.x + btnGo.width / 2).toInt()
            val revealY = (btnGo.y + btnGo.height / 2).toInt()

            val intent = Intent(this, ListPhotosActivity::class.java)
            intent.putExtra(ListPhotosActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
            intent.putExtra(ListPhotosActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)

            ActivityCompat.startActivity(this, intent, options.toBundle())
        }
    }
}
