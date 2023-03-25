package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieMap;
    HashMap<String, Director> directorMap;
    HashMap<String, List<String>> pairMap;

    public MovieRepository(HashMap<String, Movie> movieMap, HashMap<String, Director> directorMap, HashMap<String, List<String>> pairMap) {
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.pairMap = new HashMap<>();
    }

    public String addMovie(Movie movie) {
        String name = movie.getName();
        if (!movieMap.containsKey(name)) {
            movieMap.put(name, movie);
        }
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        String name = director.getName();
        if (!directorMap.containsKey(name)) {
            directorMap.put(name, director);
        }
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        if (!movieMap.containsKey(movieName) || !directorMap.containsKey(directorName)) {
            return "movieName or directorName does not found";
        }
        if (pairMap.containsKey(directorName)) {
            pairMap.get(directorName).add(movieName);
        } else {
            List<String> ans = new ArrayList<>();
            ans.add(movieName);
            pairMap.put(directorName, ans);
        }
        return "director movie pair added successfully";
    }

    public Movie getMovieByName(String name){
        if(!movieMap.containsKey(name)) return null;
        else{
            return movieMap.get(name);
        }
    }
    public Director getDirectorByName(String name){
        if(!directorMap.containsKey(name)) return null;
        return directorMap.get(name);
    }
    public List<String > getMoviesByDirectorName(String director){
        if(pairMap.containsKey(director)){
            return pairMap.get(director);
        }
        else{
            return null;
        }
    }

    public List<String > findAllMovies(){
        List<String > ans = new ArrayList<>();
        for(String name: movieMap.keySet()){
            ans.add(name);
        }
        return ans;
    }

    public String deleteDirectorByName(String directorName){
        List<String> movies = pairMap.get(directorName);
        for(int i=0; i<movies.size(); i++){
            if(movieMap.containsKey(movies.get(i))){
                movieMap.remove(movies.get(i));
            }
        }
        pairMap.remove(directorName);
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
        return "Directors and movies are removed sucessfully";
    }
    public String deleteAllDirectors() {
        for (String dir : pairMap.keySet()) {
            List<String> lis = pairMap.get(dir);
            for (String name : lis) {
                if (movieMap.containsKey(name)) {
                    movieMap.remove(name);
                }
            }
            directorMap.remove(dir);
        }
        for (String d : directorMap.keySet()) {
            directorMap.remove(d);
        }

        return "All directors and all of their movies removed successfully";
    }



}
