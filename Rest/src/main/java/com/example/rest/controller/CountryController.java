package com.example.rest.controller;


import com.example.rest.repository.CountryRepository;
import com.example.rest.repository.CreatorRepository;
import com.example.rest.resource.CountryResource;
import com.example.rest.resource.CreatorResource;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/countries")
public class CountryController
{
    private final CountryRepository countryRepository;
    private final CreatorRepository creatorRepository;

    public CountryController(CountryRepository countryRepository, CreatorRepository creatorRepository)
    {
        this.countryRepository = countryRepository;
        this.creatorRepository = creatorRepository;
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    CountryResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(countryRepository.select())
            .map(entity -> {
                CountryResource resource = new CountryResource(entity);
                if (expand != null)
                    resource.setCreatorItems(
                        Arrays.stream(creatorRepository.selectByCountryId(entity.getId()))
                            .map(e -> new CreatorResource(e))
                            .toArray(CreatorResource[]::new)
                    );
                return resource;
            })
            .toArray(CountryResource[]::new);
    }
}

