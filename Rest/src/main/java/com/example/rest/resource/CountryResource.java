package com.example.rest.resource;


import com.example.rest.entity.Country;
import com.fasterxml.jackson.annotation.JsonInclude;


public class CountryResource extends BaseResource
{
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatorResource[] creatorItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreResource[] genreItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LanguageResource[] languageItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MusicalCompositionResource[] musicalCompositionItems;

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

    public GenreResource[] getGenreItems()
    {
        return genreItems;
    }

    public void setGenreItems(GenreResource[] genreItems)
    {
        this.genreItems = genreItems;
    }

    public Genre_MusicalCompositionResource[] getGenre_MusicalCompositionResourcesItems()
    {
        return genre_MusicalCompositionResourcesItems;
    }

    public void setGenre_MusicalCompositionResourcesItems(Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems)
    {
        this.genre_MusicalCompositionResourcesItems = genre_MusicalCompositionResourcesItems;
    }

    public LanguageResource[] getLanguageItems()
    {
        return languageItems;
    }

    public void setLanguageItems(LanguageResource[] languageItems)
    {
        this.languageItems = languageItems;
    }

    public MusicalCompositionResource[] getMusicalCompositionItems()
    {
        return musicalCompositionItems;
    }

    public void setMusicalCompositionItems(MusicalCompositionResource[] musicalCompositionItems)
    {
        this.musicalCompositionItems = musicalCompositionItems;
    }

    public Country toEntity()
    {
        return new Country(
                this.id,
                this.name);
    }
}

