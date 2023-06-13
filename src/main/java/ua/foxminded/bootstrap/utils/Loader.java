package ua.foxminded.bootstrap.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Loader {
    
    public static String getFileLikeString(String fileName) throws SQLException {
        String str; 
        
        try {
            URL resourceUrl = Loader.class.getResource(fileName);
            if (resourceUrl == null) {
                throw new IllegalArgumentException("File " + fileName + " not found in resources");
            }

            File file = new File(resourceUrl.toURI());
            
            try (Stream<String> stream = Files.lines(file.toPath())) {
               str = stream.collect(Collectors.joining("\n"));
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        
        return str;
    }
}
