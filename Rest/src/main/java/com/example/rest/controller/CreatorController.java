package com.example.rest.controller;


import com.example.rest.entity.Creator;
import com.example.rest.entity.MusicalComposition;
import com.example.rest.repository.CountryRepository;
import com.example.rest.repository.CreatorRepository;
import com.example.rest.repository.MusicalCompositionRepository;
import com.example.rest.resource.CountryResource;
import com.example.rest.resource.CreatorResource;
import com.example.rest.resource.MusicalCompositionResource;
import java.util.Arrays;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/creators")
public class CreatorController
{
    private final CreatorRepository creatorRepository;

    private final CountryRepository countryRepository;
    private final MusicalCompositionRepository musicalCompositionRepository;

    public CreatorController(CreatorRepository creatorRepository, CountryRepository countryRepository, MusicalCompositionRepository musicalCompositionRepository)
    {
        this.creatorRepository = creatorRepository;
        this.countryRepository = countryRepository;
        this.musicalCompositionRepository = musicalCompositionRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    CreatorResource[] getAll(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Object expand)
    {
        Creator[] entities = countryId == null
                ? creatorRepository.select()
                : creatorRepository.selectByCountryId(countryId);
        return Arrays.stream(entities)
                .map(entity ->
                {
                    CreatorResource resource = new CreatorResource(entity);
                    if (expand != null)
                    {
                        resource.setCountryResource(new CountryResource(
                                countryRepository.select(entity.getCountryId()))
                        );
                        resource.setMusicalCompositionItems(
                            Arrays.stream(musicalCompositionRepository.selectByComposerId(entity.getId()))
                                .map(e -> new MusicalCompositionResource(e))
                                .toArray(MusicalCompositionResource[]::new)
            );
                    }
                    return resource;
                })
                .toArray(CreatorResource[]::new);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    CreatorResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Creator entity = creatorRepository.select(id);
        if (entity == null) return null;
        CreatorResource resource = new CreatorResource(entity);
        if (expand != null)
            resource.setCountryResource(
                    new CountryResource(countryRepository.select(entity.getCountryId()))
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    CreatorResource post(@RequestBody CreatorResource resource) {
        Creator entity = creatorRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new CreatorResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    CreatorResource put(@PathVariable Integer id,
                        @RequestBody CreatorResource resource) {
        Creator entity = creatorRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new CreatorResource(entity);
         return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    CreatorResource delete(@PathVariable Integer id) {
        Creator entity = creatorRepository.delete(id);
        if (entity == null) return null;
        CreatorResource resource = new CreatorResource(entity);
        return resource;
    }
}

