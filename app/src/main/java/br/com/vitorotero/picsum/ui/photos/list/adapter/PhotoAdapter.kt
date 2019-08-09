package br.com.vitorotero.picsum.ui.photos.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.vitorotero.picsum.R
import br.com.vitorotero.picsum.shared.api.Api.Companion.API_PHOTO_URL
import br.com.vitorotero.picsum.shared.extensions.glide
import br.com.vitorotero.picsum.shared.model.Photo
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoAdapter(private var photos: List<Photo>, private val listener: PhotoAdapterListener) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun setPhotos(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View, private val listener: PhotoAdapterListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Photo) {
            itemView.pbPhotoLoading.visibility = View.VISIBLE
            itemView.ivPhoto.glide(API_PHOTO_URL + item.id, itemView.pbPhotoLoading)
            itemView.tvAuthor.text = item.author
            itemView.tvAuthorUrl.text = item.authorUrl

            itemView.tvAuthorUrl.setOnClickListener {
                listener.openUrlAuthor(item.authorUrl)
            }

            itemView.clContent.setOnClickListener {
                listener.openDetail(it, itemView.tvAuthor, item)
            }
        }
    }

}