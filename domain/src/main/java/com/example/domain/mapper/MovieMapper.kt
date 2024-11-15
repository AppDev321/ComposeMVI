package com.example.domain.mapper

import com.example.models.MovieResponse
import javax.inject.Inject

class MovieMapper @Inject constructor() :
    MapperInterface<MovieResponse?, ArrayList<MovieResponse.Results>?> {

    override fun mapFrom(from: MovieResponse?): ArrayList<MovieResponse.Results>? {
        val list = arrayListOf<MovieResponse.Results>()
        from?.results?.map {
            list.add(
                MovieResponse.Results(
                    id = it?.id,
                    original_title = it?.original_title,
                    overview = it?.overview,
                    poster_path = it?.poster_path,
                    vote_average = it?.vote_average
                )
            )
        }
        return list
    }
}