package com.github.cesar1287.filmes20mob.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val id: Int,
    val adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    var title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    var genres: String = "",
    var year: String = ""
) : Parcelable {
    @IgnoredOnParcel
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf()

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<MovieItem> =
            object : DiffUtil.ItemCallback<MovieItem>() {
                override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}