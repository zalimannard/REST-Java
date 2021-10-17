package com.example.rest.resource;


import com.example.rest.entity.Genre_MusicalComposition;
import com.fasterxml.jackson.annotation.JsonInclude;


public class Genre_MusicalCompositionResource extends BaseResource
{
    private Integer id;
    private Integer genreId;
    private Integer musicalCompositionId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreResource genreResources;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MusicalCompositionResource musicalCompositionResource;

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

    public GenreResource getGenreResources()
    {
        return genreResources;
    }

    public void setGenreResources(GenreResource genreResources)
    {
        this.genreResources = genreResources;
    }

    public MusicalCompositionResource getMusicalCompositionResource()
    {
        return musicalCompositionResource;
    }

    public void setMusicalCompositionResource(MusicalCompositionResource musicalCompositionResource)
    {
        this.musicalCompositionResource = musicalCompositionResource;
    }

    public Genre_MusicalComposition toEntity()
    {
        return new Genre_MusicalComposition(
                this.id,
                this.genreId,
                this.musicalCompositionId);
    }
}

