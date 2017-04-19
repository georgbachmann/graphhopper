package com.graphhopper.routing;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.graphhopper.routing.util.DataFlagEncoder;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.EdgeIteratorState;

public class SurfaceInformation {
    public Map<Integer, Double> surfaceMap = new HashMap<>();
    public Map<Integer, Double> highwayMap = new HashMap<>();

    private DataFlagEncoder dataFlagEncoder = new DataFlagEncoder();

    public SurfaceInformation(List<EdgeIteratorState> edges) {

        new EncodingManager(Arrays.asList(dataFlagEncoder), 8);

        for (EdgeIteratorState edge : edges) {
            int surfaceType = dataFlagEncoder.getSurface(edge);
            double distanceOnSurface = surfaceMap.containsKey(surfaceType) ? surfaceMap.get(surfaceType) : 0;
            distanceOnSurface += edge.getDistance();
            surfaceMap.put(surfaceType, distanceOnSurface);

            int highwayType = dataFlagEncoder.getHighway(edge);
            double distanceOnHighway = highwayMap.containsKey(highwayType) ? highwayMap.get(highwayType) : 0;
            distanceOnHighway += edge.getDistance();
            highwayMap.put(highwayType, distanceOnHighway);
        }
    }
}
