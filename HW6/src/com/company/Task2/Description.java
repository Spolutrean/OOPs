package com.company.Task2;

import java.util.ArrayList;
import java.util.List;

public class Description {
    private List<String> description = new ArrayList<>();

    public void addDescriptionPart(String descriptionPart) {
        description.add(descriptionPart);
    }

    public String getDescription() {
        StringBuilder result = new StringBuilder();
        for(String part : description) {
            result.append(part).append("\n");
        }
        return result.toString();
    }
}
