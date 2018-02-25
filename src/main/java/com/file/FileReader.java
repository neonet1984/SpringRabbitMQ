package com.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This service reads the file
 */
@Service
public class FileReader implements IReader<String> {
    private static final Logger Log = LoggerFactory.getLogger(FileReader.class);
    private String PATH_FILE;

    /**
     * This method reads from the file text
     *
     * @return List<String>  contains a list filled with lines from a file
     */
    public List<String> read() {
        try (Stream<String> stream = Files.lines(Paths.get(PATH_FILE))) {
            return stream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Log.error("Error reading file", e);
        }
        return null;
    }

    /**
     * This method sets the path for reading the file
     */
    public void setPath(String path) {
        PATH_FILE = path;
    }
}
