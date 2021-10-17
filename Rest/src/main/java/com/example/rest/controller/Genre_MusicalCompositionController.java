package com.example.rest.controller;


import com.example.rest.entity.Genre_MusicalComposition;
import com.example.rest.repository.GenreRepository;
import com.example.rest.repository.Genre_MusicalCompositionRepository;
import com.example.rest.repository.MusicalCompositionRepository;
import com.example.rest.resource.GenreResource;
import com.example.rest.resource.Genre_MusicalCompositionResource;
import com.example.rest.resource.MusicalCompositionResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/muscomp_genre")
public class Genre_MusicalCompositionController
{
    private final Genre_MusicalCompositionRepository genre_MusicalCompositionRepository;

    private final MusicalCompositionRepository musicalCompositionRepository;
    private final GenreRepository genreRepository;

    public Genre_MusicalCompositionController(Genre_MusicalCompositionRepository genre_MusicalCompositionRepository, MusicalCompositionRepository musicalCompositionRepository, GenreRepository genreRepository)
    {
        this.genre_MusicalCompositionRepository = genre_MusicalCompositionRepository;
        this.musicalCompositionRepository = musicalCompositionRepository;
        this.genreRepository = genreRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    Genre_MusicalCompositionResource[] getAll(
            @RequestParam(required = false) Integer musicalCompositionId,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) Object expand)
    {
        Set<Genre_MusicalComposition> entities = null;
        if((musicalCompositionId == null) && (genreId == null))
        {
            entities = Set.of(genre_MusicalCompositionRepository.select());
        }
        else if((musicalCompositionId == null) && (genreId != null))
        {
            entities = Set.of(genre_MusicalCompositionRepository.selectByGenreId(genreId));
        }
        else if((musicalCompositionId != null) && (genreId == null))
        {
            entities = Set.of(genre_MusicalCompositionRepository.selectByMusicalCompositionId(musicalCompositionId));
        }
        else if((musicalCompositionId != null) && (genreId != null))
        {
            Set<Genre_MusicalComposition> entitiesByGenre = Set.of(genre_MusicalCompositionRepository.selectByGenreId(genreId));
            Set<Genre_MusicalComposition> entitiesByMusicalComposition = Set.of(genre_MusicalCompositionRepository.selectByMusicalCompositionId(musicalCompositionId));
            entities = intersection(entitiesByGenre, entitiesByMusicalComposition);
        }
        return entities.stream()
            .map(entity -> {
                Genre_MusicalCompositionResource resource = new Genre_MusicalCompositionResource(entity);
                if (expand != null)
                {
                    resource.setGenreResources(
                            new GenreResource(genreRepository.select(entity.getGenreId()))
                    );
                    resource.setMusicalCompositionResource(
                            new MusicalCompositionResource(musicalCompositionRepository.select(entity.getMusicalCompositionId()))
                    );
                }
                return resource;
            })
            .toArray(Genre_MusicalCompositionResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Genre_MusicalCompositionResource get(
            @PathVariable Integer id,
            @RequestParam(required = false) Object expand) {
        Genre_MusicalComposition entity = genre_MusicalCompositionRepository.select(id);
        if (entity == null) return null;
        Genre_MusicalCompositionResource resource = new Genre_MusicalCompositionResource(entity);
        if (expand != null)
        {
            resource.setMusicalCompositionResource(
                    new MusicalCompositionResource(musicalCompositionRepository.select(entity.getMusicalCompositionId()))
            );
            resource.setGenreResources(
                    new GenreResource(genreRepository.select(entity.getMusicalCompositionId()))
            );
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    Genre_MusicalCompositionResource post(@RequestBody Genre_MusicalCompositionResource resource) {
        Genre_MusicalComposition entity = genre_MusicalCompositionRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new Genre_MusicalCompositionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Genre_MusicalCompositionResource put(@PathVariable Integer id,
                          @RequestBody Genre_MusicalCompositionResource resource) {
        Genre_MusicalComposition entity = genre_MusicalCompositionRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new Genre_MusicalCompositionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Genre_MusicalCompositionResource delete(@PathVariable Integer id) {
        Genre_MusicalComposition entity = genre_MusicalCompositionRepository.delete(id);
        if (entity == null) return null;
        return new Genre_MusicalCompositionResource(entity);
    }
    
    private Set<Genre_MusicalComposition> intersection(Set<Genre_MusicalComposition> A, Set<Genre_MusicalComposition> B)
    {
        ArrayList<Genre_MusicalComposition> preanswer = new ArrayList<>();
        for(Genre_MusicalComposition a : A)
        {
            for(Genre_MusicalComposition b : B)
            {
                if (a.getId().equals(b.getId()))
                {
                    preanswer.add(b);
                }
            }
        }
        return new HashSet<Genre_MusicalComposition>(preanswer);
    }
}

