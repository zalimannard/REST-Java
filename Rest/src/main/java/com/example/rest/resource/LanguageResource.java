package com.example.rest.resource;


import com.example.rest.entity.Language;
import com.fasterxml.jackson.annotation.JsonInclude;


public class LanguageResource extends BaseResource
{
    private Integer id;
    private String name;

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

