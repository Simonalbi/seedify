package com.unisa.seedify.control.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public class JsonUtils {
    public static JsonArray filterJsonArray(JsonArray jsonArray, List<String> fields) {
        JsonArray filteredData = new JsonArray();
        for (JsonElement record : jsonArray) {
            JsonObject filteredRecord = new JsonObject();
            for (String field : fields) {
                JsonObject object = record.getAsJsonObject();
                String[] path = field.split("\\.");
                for (String pathElement : path) {
                    if (pathElement.equals(path[path.length - 1])) {
                        try {
                            filteredRecord.addProperty(pathElement, object.get(pathElement).getAsString());
                        } catch (NullPointerException e) {
                            filteredRecord.addProperty(pathElement, "N/A");
                        }
                    } else {
                        object = object.getAsJsonObject(pathElement);
                    }
                }
            }
            filteredData.add(filteredRecord);
        }

        return filteredData;
    }

    public static JsonObject filterJsonArray(JsonArray jsonArray, List<String> fields, String filter) {
        JsonObject filtered = new JsonObject();

        boolean addedFilter = false;
        if (!fields.contains(filter)) {
            fields.add(filter);
            addedFilter = true;
        }
        JsonArray filteredArray = filterJsonArray(jsonArray, fields);

        for (JsonElement record : filteredArray) {
            JsonObject object = record.getAsJsonObject();

            String filteredGroupName = object.get(filter).getAsString();
            if (!filtered.has(filteredGroupName)) {
                filtered.add(filteredGroupName, new JsonArray());
            }

            if (addedFilter) {
                object.remove(filter);
            }

            filtered.getAsJsonArray(filteredGroupName).add(object);
        }

        return filtered;
    }
}
