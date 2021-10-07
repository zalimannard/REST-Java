package com.example.rest.resource;


import com.example.rest.entity.Genre_MusicalComposition;
import com.fasterxml.jackson.annotation.JsonInclude;


public class Genre_MusicalCompositionResource extends BaseResource
{
    private Integer id;
    private Integer genreId;
    private Integer musicalCompositionId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryResource[] countryItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatorResource[] creatorItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreResource[] genreResourcesItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LanguageResource[] languageItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MusicalCompositionResource[] musicalCompositionItems;

    public Genre_MusicalCompositionResource()
    {
    }

    public Genre_MusicalCompositionResource(Genre_MusicalComposition genre)
    {
        this.id = genre.getId();
        this.genreId = genre.getGenreId();
        this.musicalCompositionId = genre.getMusicalCompositionId();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getGenreId()
    {
        return genreId;
    }

    public void setGenreId(Integer genreId)
    {
        this.genreId = genreId;
    }

    public Integer getMusicalCompositionId()
    {
        return musicalCompositionId;
    }

    public void setMusicalCompositionId(Integer musicalCompositionId)
    {
        this.musicalCompositionId = musicalCompositionId;
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

    public Genre_MusicalComposition toEntity()
    {
        return new Genre_MusicalComposition(
                this.id,
                this.genreId,
                this.musicalCompositionId);
    }
}

