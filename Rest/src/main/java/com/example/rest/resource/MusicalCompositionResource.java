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
    private LanguageResource languageResource;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatorResource composerResource;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatorResource singerResource;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Genre_MusicalCompositionResource[] genre_MusicalCompositionResource;

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

    public LanguageResource getLanguageResource()
    {
        return languageResource;
    }

    public void setLanguageResource(LanguageResource languageResource)
    {
        this.languageResource = languageResource;
    }

    public CreatorResource getComposerResource()
    {
        return composerResource;
    }

    public void setComposerResource(CreatorResource composerResource)
    {
        this.composerResource = composerResource;
    }

    public CreatorResource getSingerResource()
    {
        return singerResource;
    }

    public void setSingerResource(CreatorResource singerResource)
    {
        this.singerResource = singerResource;
    }

    public Genre_MusicalCompositionResource[] getGenre_MusicalCompositionResource()
    {
        return genre_MusicalCompositionResource;
    }

    public void setGenre_MusicalCompositionResource(Genre_MusicalCompositionResource[] genre_MusicalCompositionResource)
    {
        this.genre_MusicalCompositionResource = genre_MusicalCompositionResource;
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

