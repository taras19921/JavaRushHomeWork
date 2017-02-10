package com.javarush.test.freelance.parser.project_manager_amazon;

import java.util.List;

public class Product
{
    private String subCategory;
    private String productTitle;
    private String brandTitle;
    private String priceTitle;
    private List<String> descriptionTitle;
    private List<String> imageUrls;
    private List<String> bulletPoints;

    public Product()
    {
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory(String subCategory)
    {
        this.subCategory = subCategory;
    }

    public String getProductTitle()
    {
        return productTitle;
    }

    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }

    public String getBrandTitle()
    {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle)
    {
        this.brandTitle = brandTitle;
    }

    public String getPriceTitle()
    {
        return priceTitle;
    }

    public void setPriceTitle(String priceTitle)
    {
        this.priceTitle = priceTitle;
    }

    public List<String> getDescriptionTitle()
    {
        return descriptionTitle;
    }

    public void setDescriptionTitle(List<String> descriptionTitle)
    {
        this.descriptionTitle = descriptionTitle;
    }

    public List<String> getImageUrls()
    {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls)
    {
        this.imageUrls = imageUrls;
    }

    public List<String> getBulletPoints()
    {
        return bulletPoints;
    }

    public void setBulletPoints(List<String> bulletPoints)
    {
        this.bulletPoints = bulletPoints;
    }
}
