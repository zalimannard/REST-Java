package com.example.rest.resource;


import com.example.rest.entity.Language;
import com.fasterxml.jackson.annotation.JsonInclude;


public class LanguageResource extends BaseResource
{
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryResource[] countryItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatorResource[] creatorItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreResource[] genreResourcesItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MusicalCompositionResource[] musicalCompositionItems;

    public LanguageResource()
    {
    }

    public LanguageResource(Language genre)
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

    public CountryResource[] getCountryItems()
    {
        return countryItems;
    }

    public void setCountryItems(CountryResource[] countryItems)
    {
        this.countryItems = countryItems;
    }

    public CreatorResource[] getCreatorItems()
    {
        return creatorItems;
    }

    public void setCreatorItems(CreatorResource[] creatorItems)
    {
        this.creatorItems = creatorItems;
    }

    public GenreResource[] getGenreResourcesItems()
    {
        return genreResourcesItems;
    }

    public void setGenreResourcesItems(GenreResource[] genreResourcesItems)
    {
        this.genreResourcesItems = genreResourcesItems;
    }

    public Genre_MusicalCompositionResource[] getGenre_MusicalCompositionResourcesItems()
    {
        return genre_MusicalCompositionResourcesItems;
    }

    public void setGenre_MusicalCompositionResourcesItems(Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems)
    {
        this.genre_MusicalCompositionResourcesItems = genre_MusicalCompositionResourcesItems;
    }

    public MusicalCompositionResource[] getMusicalCompositionItems()
    {
        return musicalCompositionItems;
    }

    public void setMusicalCompositionItems(MusicalCompositionResource[] musicalCompositionItems)
    {
        this.musicalCompositionItems = musicalCompositionItems;
    }

    public Language toEntity()
    {
        return new Language(
                this.id,
                this.name);
    }
}

