package com.graphhopper.routing;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.graphhopper.routing.util.DataFlagEncoder;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.EdgeIteratorState;

public class SurfaceInformation {
    public Map<String, Double> surfaceMap = new HashMap<>();
    public Map<String, Double> streetTypeMap = new HashMap<>();

    private DataFlagEncoder dataFlagEncoder = new DataFlagEncoder();

    public SurfaceInformation(List<EdgeIteratorState> edges) {

        new EncodingManager(Arrays.asList(dataFlagEncoder), 8);

        for (EdgeIteratorState edge : edges) {

            String surfaceType = dataFlagEncoder.getSurfaceAsString(edge);
            double distanceOnSurface = surfaceMap.containsKey(surfaceType) ? surfaceMap.get(surfaceType) : 0;
            distanceOnSurface += edge.getDistance();
            surfaceMap.put(surfaceType, distanceOnSurface);

            String highwayType = dataFlagEncoder.getHighwayAsString(edge);
            double distanceOnHighway = streetTypeMap.containsKey(highwayType) ? streetTypeMap.get(highwayType) : 0;
            distanceOnHighway += edge.getDistance();
            streetTypeMap.put(highwayType, distanceOnHighway);
        }
    }
}
