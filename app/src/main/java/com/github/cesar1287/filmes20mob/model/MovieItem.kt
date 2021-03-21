package com.github.cesar1287.filmes20mob.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val id: Int? = null,
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val homepage: String? = null,
    val popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    var title: String? = null,
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    var genres: String? = "",
    var year: String? = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    var isFavorite: Boolean? = false
) : Parcelable {

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