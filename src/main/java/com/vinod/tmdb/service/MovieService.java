package com.vinod.tmdb.service;

import com.vinod.tmdb.exceptions.InvalidDataException;
import com.vinod.tmdb.exceptions.NotFoundException;
import com.vinod.tmdb.model.Movie;
import com.vinod.tmdb.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    //CRUD operations

    public Movie createMovie(Movie movie) {
        if (movie == null)
            throw new InvalidDataException("Invalid Movie : null");

        return movieRepository.save(movie);
    }

    public Movie fetchMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id : " + id));
    }

    public void updateMovie(Long id, Movie update) {
        if (update == null || id == null)
            throw new InvalidDataException("Invalid Movie : null");

        if (movieRepository.existsById(id)) {
            Movie movie = movieRepository.getReferenceById(id);
            movie.setName(update.getName());
            movie.setDirector(update.getDirector());
            movie.setActors(update.getActors());
            movieRepository.save(movie);
        } else
            throw new NotFoundException("Movie not found with id : " + id);
    }

    public void deleteMovie(Long id) {
        if (movieRepository.existsById(id))
            movieRepository.deleteById(id);
        else
            throw new NotFoundException("Movie not found with id : " + id);
    }
}
