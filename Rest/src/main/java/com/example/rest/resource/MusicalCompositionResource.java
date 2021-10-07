package com.example.rest.resource;


import com.example.rest.entity.MusicalComposition;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;


public class MusicalCompositionResource extends BaseResource
{
    private Integer id;
    private Integer languageId;
    private Integer composerId;
    private Integer singerId;
    private String name;
    private Timestamp year;

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

    public MusicalCompositionResource()
    {
    }

    public MusicalCompositionResource(MusicalComposition musicalComposition)
    {
        this.id = musicalComposition.getId();
        this.languageId = musicalComposition.getLanguageId();
        this.composerId = musicalComposition.getComposerId();
        this.singerId = musicalComposition.getSingerId();
        this.name = musicalComposition.getName();
        this.year = musicalComposition.getYear();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getLanguageId()
    {
        return languageId;
    }

    public void setLanguageId(Integer languageId)
    {
        this.languageId = languageId;
    }

    public Integer getComposerId()
    {
        return composerId;
    }

    public void setComposerId(Integer composerId)
    {
        this.composerId = composerId;
    }

    public Integer getSingerId()
    {
        return singerId;
    }

    public void setSingerId(Integer singerId)
    {
        this.singerId = singerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Timestamp getYear()
    {
        return year;
    }

    public void setYear(Timestamp year)
    {
        this.year = year;
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

    public MusicalComposition toEntity()
    {
        return new MusicalComposition(
                this.id,
                this.languageId,
                this.composerId,
                this.singerId,
                this.name,
                this.year);
    }
}

