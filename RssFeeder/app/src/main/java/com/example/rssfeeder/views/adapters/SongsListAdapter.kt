package com.example.rssfeeder.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeeder.R
import com.example.rssfeeder.services.model.Song
import com.example.rssfeeder.services.model.SongList
import com.example.rssfeeder.util.ActionListener
import com.example.rssfeeder.views.home.fragments.HomeFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.songslist.view.*

/**
 * An recycler view adapter class to bind songs list data with the recycler
 * view.
 * @author: Deepak Kumar
 */
class SongsListAdapter(val context: Context, private val actionListener: ActionListener) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            context,
            LayoutInflater.from(context).inflate(
                R.layout.songslist, parent, false
            ), actionListener
        )
    }

    override fun getItemCount(): Int {
        return currentStatus?.let {
            it.songList.size
        } ?: 0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val song = currentStatus?.songList?.get(position)
        holder.setData(song)
    }

    private var currentStatus: SongList? = null
    fun setData(status: SongList?) {
        currentStatus = status
        notifyDataSetChanged()
    }
}

/**
 * A view holder class to create the view for the list row.
 *
 * @author: Deepak Kumar
 */

class HomeViewHolder(val context: Context, view: View, actionListener: ActionListener) : RecyclerView.ViewHolder(view) {

    private var song: Song? = null

    init {
        itemView.setOnClickListener {
            actionListener.onAction(HomeFragment.ACTION_SHOW_DETAILS, song)
        }
    }

    /**
     * Method to set data on view items of list row
     * @param _song holding song data
     */
    fun setData(_song: Song?) {
        song = _song
        _song?.let {
            itemView.albumName.text = it.name
            itemView.songTitle.text = it.album.name
            var strNames = ""
            for (artist in it.artists) {
                if (!artist.name.isNullOrEmpty())
                    strNames += artist.name + " "
            }
            itemView.artistsName.text = strNames
            Picasso.with(context).load(it.album.cover).into(itemView.coverIcon)
        }
    }
}