package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        String res = movieService.addMovie(movie);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        String res = movieService.addDirector(director);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName) {
        String res = movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name) {
        Movie res = movieService.getMovieByName(name);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name) {
        Director res = movieService.getDirectorByName(name);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String director) {
        List<String> res = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies() {
        List<String> res = movieService.findAllMovies();
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName) {
        String res = movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {
        String res = movieService.deleteAllDirectors();
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);


    }
}