package com.graphhopper.routing;

import java.util.HashMap;
import java.util.Map;

public class PathStatistics {
    public SurfaceInformation surfaceInformation;

    public PathStatistics(SurfaceInformation surfaceInformation) {
        this.surfaceInformation = surfaceInformation;
    }

    public Map<String, Object> createJSON() {

        Map<String, Object> jsonObject = new HashMap<>();

        if (surfaceInformation == null) {
            return jsonObject;
        }

        jsonObject.put("surface", surfaceInformation.surfaceMap);
        jsonObject.put("street_type", surfaceInformation.streetTypeMap);
        return jsonObject;
    }
}
