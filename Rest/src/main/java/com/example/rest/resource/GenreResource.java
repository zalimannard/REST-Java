package com.example.rest.resource;


import com.example.rest.entity.Genre;
import com.fasterxml.jackson.annotation.JsonInclude;


public class GenreResource extends BaseResource
{
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems;

    public GenreResource()
    {
    }

    public GenreResource(Genre genre)
    {
        this.id = genre.getId();
        this.name = genre.getName();
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

    public Genre_MusicalCompositionResource[] getGenre_MusicalCompositionResourcesItems()
    {
        return genre_MusicalCompositionResourcesItems;
    }

    public void setGenre_MusicalCompositionResourcesItems(Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems)
    {
        this.genre_MusicalCompositionResourcesItems = genre_MusicalCompositionResourcesItems;
    }

    public Genre toEntity()
    {
        return new Genre(
                this.id,
                this.name);
    }
}

