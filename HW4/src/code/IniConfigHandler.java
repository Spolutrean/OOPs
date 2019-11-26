package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class IniConfigHandler {
    private String filePath;
    Map<String, Map<String, String>> sections = new TreeMap<>();

    public IniConfigHandler(String filePath) throws ConfigHandlerException {
        this.filePath = filePath;
        readAndParseFile();
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String sectionName, String key, Class<T> valueClass) throws ConfigHandlerException {
        if(!sections.containsKey(sectionName)) {
            throw new ConfigHandlerException("Section name is not exists: " + sectionName);
        }
        if(!sections.get(sectionName).containsKey(key)) {
            throw new ConfigHandlerException("There is no key: " + key);
        }
        String value = sections.get(sectionName).get(key);

        if (valueClass.isAssignableFrom(String.class)) {
            return (T) value;
        } else if (valueClass.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(value);
        } else if (valueClass.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(value);
        } else {
            throw new ConfigHandlerException("Can not cast value class into " + valueClass.toString());
        }
    }

    private void readAndParseFile() throws ConfigHandlerException {
        if(!filePath.endsWith(".ini")) {
            throw new ConfigHandlerException("Wrong file extension, should be .ini");
        }

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine();
            while(line != null) {
                if(line.length() != 0) {
                    lines.add(line);
                }
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new ConfigHandlerException("File not found, is it exist?");
        } catch (IOException e) {
            throw new ConfigHandlerException("Can not read file, check permissions");
        }

        lines = lines.stream().map(s -> (s + ";").split(";")[0].trim()).collect(Collectors.toList());

        String currentSection = null;
        for (String line : lines) {
            char[] lineArr = line.toCharArray();
            if(lineArr.length == 0) {
                continue;
            }
            if(lineArr[0] == '[') {
                int i = 1;
                StringBuilder newSection = new StringBuilder();

                while(i < lineArr.length && (Character.isAlphabetic(lineArr[i]) || lineArr[i] == '_')) {
                    newSection.append(lineArr[i++]);
                }

                String newSectionString = newSection.toString();
                if(i == lineArr.length - 1 && lineArr[i] == ']') {
                    if(sections.containsKey(newSectionString)) {
                        throw new ConfigHandlerException("Section describes twice: " + newSectionString);
                    }
                    currentSection = newSectionString;
                    sections.put(currentSection, new TreeMap<>());
                } else {
                    throw new ConfigHandlerException("Found invalid section name: " + newSectionString);
                }
            } else {
                if(!line.contains("=")) {
                    throw new ConfigHandlerException("Found bad key-value string: " + line);
                }

                String key = line.split("=")[0].trim(), value = line.split("=")[1].trim();
                for(char c : key.toCharArray()) {
                    if(!Character.isAlphabetic(c) && c != '_') {
                        throw new ConfigHandlerException("Invalid key found: " + key);
                    }
                }

                if(currentSection != null) {
                    if(sections.get(currentSection).containsKey(key)) {
                        throw new ConfigHandlerException("Rewrite value by existed key: " + key);
                    }
                    sections.get(currentSection).put(key, value);
                } else {
                    throw new ConfigHandlerException("Found key-value line without section: " + line);
                }
            }
        }
    }
}
