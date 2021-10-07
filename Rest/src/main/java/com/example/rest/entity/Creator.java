package com.example.rest.entity;


public class Creator extends BaseEntity
{
    private String name;
    private Integer countryId;

    public Creator(Integer id, Integer countryId, String name)
    {
        super(id);
        this.name = name;
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

    public Integer getCountryId()
    {
        return countryId;
    }

    public void setCountryId(Integer countryId)
    {
        this.countryId = countryId;
    }
}

