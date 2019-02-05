package com.example.rssfeeder.services.model

/**
 * data class to hold songs list data
 * @author Deeapk Kumar
 */
data class SongList(
    val songList: List<Song>,
    val success: Boolean,
    val total: Int
)

data class Song(
    val album: Album,
    val artists: ArrayList<Artist>,
    val id: Int,
    val name: String,
    val needPay: Boolean
)

data class Artist(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class Album(
    val cover: String,
    val coverBig: String,
    val coverSmall: String,
    val id: Int,
    val name: String
)
