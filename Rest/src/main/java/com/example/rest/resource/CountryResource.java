package com.example.rest.resource;


import com.example.rest.entity.Country;
import com.fasterxml.jackson.annotation.JsonInclude;


public class CountryResource extends BaseResource
{
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatorResource[] creatorItems;

    public CountryResource()
    {
    }

    public CountryResource(Country country)
    {
        this.id = country.getId();
        this.name = country.getName();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public CreatorResource[] getCreatorItems()
    {
        return creatorItems;
    }

    public void setCreatorItems(CreatorResource[] creatorItems)
    {
        this.creatorItems = creatorItems;
    }

    public Country toEntity()
    {
        return new Country(
                this.id,
                this.name);
    }
}

