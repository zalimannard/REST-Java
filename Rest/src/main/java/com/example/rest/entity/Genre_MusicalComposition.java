package com.example.rest.entity;


public class Genre_MusicalComposition extends BaseEntity
{
    private Integer genreId;
    private Integer musicalCompositionId;

    public Genre_MusicalComposition(Integer id, Integer genreId, Integer musicalCompositionId)
    {
        super(id);
        this.genreId = genreId;
        this.musicalCompositionId = musicalCompositionId;
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
}

