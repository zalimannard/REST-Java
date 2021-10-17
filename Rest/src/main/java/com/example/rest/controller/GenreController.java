package com.example.rest.controller;


import com.example.rest.entity.Genre;
import com.example.rest.repository.GenreRepository;
import com.example.rest.repository.Genre_MusicalCompositionRepository;
import com.example.rest.resource.GenreResource;
import com.example.rest.resource.Genre_MusicalCompositionResource;
import java.util.Arrays;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/genres")
public class GenreController
{
    private final GenreRepository genreRepository;
    
    private final Genre_MusicalCompositionRepository genre_MusicalCompositionRepository;

    public GenreController(GenreRepository genreRepository, Genre_MusicalCompositionRepository genre_MusicalCompositionRepository)
    {
        this.genreRepository = genreRepository;
        this.genre_MusicalCompositionRepository = genre_MusicalCompositionRepository;
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    GenreResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(genreRepository.select())
            .map(entity -> {
                GenreResource resource = new GenreResource(entity);
                if (expand != null)
                    resource.setGenre_MusicalCompositionResourcesItems(
                        Arrays.stream(genre_MusicalCompositionRepository.selectByGenreId(entity.getId()))
                            .map(e -> new Genre_MusicalCompositionResource(e))
                            .toArray(Genre_MusicalCompositionResource[]::new)
                    );
                return resource;
            })
            .toArray(GenreResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GenreResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Genre entity = genreRepository.select(id);
        if (entity == null) return null;
        GenreResource resource = new GenreResource(entity);
        if (expand != null)
            resource.setGenre_MusicalCompositionResourcesItems(
                Arrays.stream(genre_MusicalCompositionRepository.selectByGenreId(entity.getId()))
                    .map(e -> new Genre_MusicalCompositionResource(e))
                    .toArray(Genre_MusicalCompositionResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    GenreResource post(@RequestBody GenreResource resource) {
        Genre entity = genreRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new GenreResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    GenreResource put(@PathVariable Integer id,
                          @RequestBody GenreResource resource) {
        Genre entity = genreRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new GenreResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    GenreResource delete(@PathVariable Integer id) {
        Genre entity = genreRepository.delete(id);
        if (entity == null) return null;
        return new GenreResource(entity);
    }
}

