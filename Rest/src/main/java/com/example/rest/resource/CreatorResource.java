package com.example.rest.resource;


import com.example.rest.entity.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;


public class CreatorResource extends BaseResource
{
    private Integer id;
    private Integer countryId;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryResource[] countryItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreResource[] genreItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Genre_MusicalCompositionResource[] genre_MusicalCompositionResourcesItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LanguageResource[] languageItems;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MusicalCompositionResource[] musicalCompositionItems;

    public CreatorResource()
    {
    }

    public CreatorResource(Creator creator)
    {
        this.id = creator.getId();
        this.countryId = creator.getCountryId();
        this.name = creator.getName();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCountryId()
    {
        return countryId;
    }

    public void setCountryId(Integer countryId)
    {
        this.countryId = countryId;
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

    public Creator toEntity()
    {
        return new Creator(
                this.id,
                this.countryId,
                this.name);
    }
}

