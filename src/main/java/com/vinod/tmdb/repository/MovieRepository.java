package com.vinod.tmdb.repository;

import com.vinod.tmdb.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository  extends JpaRepository<Movie, Long> {
}
