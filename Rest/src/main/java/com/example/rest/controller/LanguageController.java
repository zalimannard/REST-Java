package com.example.rest.controller;


import com.example.rest.entity.Language;
import com.example.rest.repository.LanguageRepository;
import com.example.rest.repository.MusicalCompositionRepository;
import com.example.rest.resource.LanguageResource;
import com.example.rest.resource.MusicalCompositionResource;
import java.util.Arrays;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/languages")
public class LanguageController
{
    private final LanguageRepository languageRepository;
    
    private final MusicalCompositionRepository musicalCompositionRepository;

    public LanguageController(LanguageRepository languageRepository, MusicalCompositionRepository musicalCompositionRepository)
    {
        this.languageRepository = languageRepository;
        this.musicalCompositionRepository = musicalCompositionRepository;
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    LanguageResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(languageRepository.select())
            .map(entity -> {
                LanguageResource resource = new LanguageResource(entity);
                if (expand != null)
                    resource.setMusicalCompositionItems(
                        Arrays.stream(musicalCompositionRepository.selectByLanguageId(entity.getId()))
                            .map(e -> new MusicalCompositionResource(e))
                            .toArray(MusicalCompositionResource[]::new)
                    );
                return resource;
            })
            .toArray(LanguageResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    LanguageResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Language entity = languageRepository.select(id);
        if (entity == null) return null;
        LanguageResource resource = new LanguageResource(entity);
        if (expand != null)
            resource.setMusicalCompositionItems(
                Arrays.stream(musicalCompositionRepository.selectByLanguageId(entity.getId()))
                    .map(e -> new MusicalCompositionResource(e))
                    .toArray(MusicalCompositionResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    LanguageResource post(@RequestBody LanguageResource resource) {
        Language entity = languageRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new LanguageResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    LanguageResource put(@PathVariable Integer id,
                          @RequestBody LanguageResource resource) {
        Language entity = languageRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new LanguageResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    LanguageResource delete(@PathVariable Integer id) {
        Language entity = languageRepository.delete(id);
        if (entity == null) return null;
        return new LanguageResource(entity);
    }
}

