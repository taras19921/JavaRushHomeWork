package com.javarush.test.volpis;

import de.micromata.opengis.kml.v_2_2_0.*;

import java.io.File;
import java.util.List;

public class KMLTest
{

    public static void main(String[] args)
    {
        final Kml kml = Kml.unmarshal(new File("D:\\volpis\\roadhunter\\TEST_cb_2014_us_county_20m_TEST.kml"));
        Feature feature = kml.getFeature();
        if (feature != null)
        {
            if (feature instanceof Document)
            {
                Document document = (Document) feature;
                List<Feature> featureList = document.getFeature();
                for (Feature feature1 : featureList)
                {
                    if (feature1 instanceof Folder)
                    {
                        Folder folder = (Folder) feature1;
                        List<Feature> featureList1 = folder.getFeature();
                        for (Feature feature2 : featureList1)
                        {
                            if (feature2 instanceof Placemark)
                            {
                                Placemark placemark = (Placemark) feature2;
                                String name = placemark.getName();
                                Geometry geometry = placemark.getGeometry();
                                if (geometry != null)
                                {
                                    if (geometry instanceof Polygon)
                                    {
                                        Polygon polygon = (Polygon) geometry;
                                        Boundary outerBoundaryIs = polygon.getOuterBoundaryIs();
                                        if (outerBoundaryIs != null)
                                        {
                                            LinearRing linearRing = outerBoundaryIs.getLinearRing();
                                            if (linearRing != null)
                                            {
                                                List<Coordinate> coordinates = linearRing.getCoordinates();
                                                if (coordinates != null)
                                                {
                                                    for (Coordinate coordinate : coordinates)
                                                    {
                                                        if (coordinate != null)
                                                        {
                                                            System.out.println("Longitude: " + coordinate.getLongitude());
                                                            System.out.println("Latitude : " + coordinate.getLatitude());
                                                            System.out.println("Altitude : " + coordinate.getAltitude());
                                                            System.out.println("");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}