package com.example.rest.controller;


import com.example.rest.entity.MusicalComposition;
import com.example.rest.repository.CreatorRepository;
import com.example.rest.repository.Genre_MusicalCompositionRepository;
import com.example.rest.repository.LanguageRepository;
import com.example.rest.repository.MusicalCompositionRepository;
import com.example.rest.resource.CreatorResource;
import com.example.rest.resource.Genre_MusicalCompositionResource;
import com.example.rest.resource.LanguageResource;
import com.example.rest.resource.MusicalCompositionResource;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/music")
public class MusicalCompositionController
{
    private final MusicalCompositionRepository musicalCompositionRepository;

    private final LanguageRepository languageRepository;
    private final CreatorRepository creatorRepository;
    private final Genre_MusicalCompositionRepository genre_MusicalCompositionRepository;

    public MusicalCompositionController(MusicalCompositionRepository musicalCompositionRepository, LanguageRepository languageRepository, CreatorRepository creatorRepository, Genre_MusicalCompositionRepository genre_MusicalCompositionRepository)
    {
        this.musicalCompositionRepository = musicalCompositionRepository;
        this.languageRepository = languageRepository;
        this.creatorRepository = creatorRepository;
        this.genre_MusicalCompositionRepository = genre_MusicalCompositionRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    MusicalCompositionResource[] getAll(
            @RequestParam(required = false) Integer languageId,
            @RequestParam(required = false) Integer composerId,
            @RequestParam(required = false) Integer singerId,
            @RequestParam(required = false) Object expand)
    {
        Set<MusicalComposition> entities = null;
        if((languageId == null) && (composerId == null) && (singerId == null))
        {
            entities = Set.of(musicalCompositionRepository.select());
        }
        else if((languageId == null) && (composerId == null) && (singerId != null))
        {
            entities = Set.of(musicalCompositionRepository.selectBySingerId(singerId));
        }
        else if((languageId == null) && (composerId != null) && (singerId == null))
        {
            entities = Set.of(musicalCompositionRepository.selectByComposerId(composerId));
        }
        else if((languageId == null) && (composerId != null) && (singerId != null))
        {
            Set<MusicalComposition> entitiesBySingers = Set.of(musicalCompositionRepository.selectBySingerId(singerId));
            Set<MusicalComposition> entitiesByComposers = Set.of(musicalCompositionRepository.selectByComposerId(composerId));
            entities = intersection(entitiesBySingers, entitiesByComposers);
        }
        else if((languageId != null) && (composerId == null) && (singerId == null))
        {
            entities = Set.of(musicalCompositionRepository.selectByLanguageId(languageId));
        }
        else if((languageId != null) && (composerId == null) && (singerId != null))
        {
            Set<MusicalComposition> entitiesBySingers = Set.of(musicalCompositionRepository.selectBySingerId(singerId));
            Set<MusicalComposition> entitiesByLanguages = Set.of(musicalCompositionRepository.selectByLanguageId(languageId));
            entities = intersection(entitiesBySingers, entitiesByLanguages);
        }
        else if((languageId != null) && (composerId != null) && (singerId == null))
        {
            Set<MusicalComposition> entitiesByComposers = Set.of(musicalCompositionRepository.selectByComposerId(composerId));
            Set<MusicalComposition> entitiesByLanguages = Set.of(musicalCompositionRepository.selectByLanguageId(languageId));
            entities = intersection(entitiesByComposers, entitiesByLanguages);
        }
        else if((languageId != null) && (composerId != null) && (singerId != null))
        {
            Set<MusicalComposition> entitiesBySingers = Set.of(musicalCompositionRepository.selectBySingerId(singerId));
            Set<MusicalComposition> entitiesByComposers = Set.of(musicalCompositionRepository.selectByComposerId(composerId));
            Set<MusicalComposition> entitiesByLanguages = Set.of(musicalCompositionRepository.selectByLanguageId(languageId));
            entities = intersection(entitiesBySingers, entitiesByComposers);
            entities = intersection(entities, entitiesByLanguages);
        }
        return entities.stream()
            .map(entity -> {
                MusicalCompositionResource resource = new MusicalCompositionResource(entity);
                if (expand != null)
                {
                    resource.setLanguageResource(
                            new LanguageResource(languageRepository.select(entity.getLanguageId()))
                    );
                    resource.setComposerResource(
                            new CreatorResource(creatorRepository.select(entity.getComposerId()))
                    );
                    resource.setSingerResource(
                            new CreatorResource(creatorRepository.select(entity.getSingerId()))
                    );
                    resource.setGenre_MusicalCompositionResource(
                            Arrays.stream(genre_MusicalCompositionRepository.selectByMusicalCompositionId(entity.getId()))
                                    .map(e -> new Genre_MusicalCompositionResource(e))
                                    .toArray(Genre_MusicalCompositionResource[]::new)
                    );
                }
                return resource;
            })
            .toArray(MusicalCompositionResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    MusicalCompositionResource get(
            @PathVariable Integer id,
            @RequestParam(required = false) Object expand) {
        MusicalComposition entity = musicalCompositionRepository.select(id);
        if (entity == null) return null;
        MusicalCompositionResource resource = new MusicalCompositionResource(entity);
        if (expand != null)
        {
            resource.setLanguageResource(
                    new LanguageResource(languageRepository.select(entity.getLanguageId()))
            );
            resource.setComposerResource(
                    new CreatorResource(creatorRepository.select(entity.getComposerId()))
            );
            resource.setSingerResource(
                    new CreatorResource(creatorRepository.select(entity.getSingerId()))
            );
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    MusicalCompositionResource post(@RequestBody MusicalCompositionResource resource) {
        MusicalComposition entity = musicalCompositionRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new MusicalCompositionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    MusicalCompositionResource put(@PathVariable Integer id,
                          @RequestBody MusicalCompositionResource resource) {
        MusicalComposition entity = musicalCompositionRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new MusicalCompositionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    MusicalCompositionResource delete(@PathVariable Integer id) {
        MusicalComposition entity = musicalCompositionRepository.delete(id);
        if (entity == null) return null;
        return new MusicalCompositionResource(entity);
    }
    
    private Set<MusicalComposition> intersection(Set<MusicalComposition> A, Set<MusicalComposition> B)
    {
        ArrayList<MusicalComposition> preanswer = new ArrayList<>();
        for(MusicalComposition a : A)
        {
            for(MusicalComposition b : B)
            {
                if (a.getId().equals(b.getId()))
                {
                    preanswer.add(b);
                }
            }
        }
        return new HashSet<MusicalComposition>(preanswer);
    }
}
