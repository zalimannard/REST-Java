package com.example.rest.entity;


import java.sql.Timestamp;


public class MusicalComposition extends BaseEntity
{
    private Integer languageId;
    private Integer composerId;
    private Integer singerId;
    private String name;
    private Timestamp year;

    public MusicalComposition(Integer id, Integer languageId, Integer composerId, Integer singerId, String name, Timestamp year)
    {
        super(id);
        this.languageId = languageId;
        this.composerId = composerId;
        this.singerId = singerId;
        this.name = name;
        this.year = year;
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
}

