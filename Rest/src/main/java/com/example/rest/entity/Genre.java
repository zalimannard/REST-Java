package com.example.rest.entity;


public class Genre extends BaseEntity
{
    private String name;

    public Genre(Integer id, String name)
    {
        super(id);
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

