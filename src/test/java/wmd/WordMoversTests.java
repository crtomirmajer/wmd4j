package wmd;

import com.crtomirmajer.wmd4j.WordMovers;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Majer on 22. 09. 2016.
 */
public class WordMoversTests {
    
    @Test
    public void distance() throws IOException {
        WordVectors vectors = WordVectorSerializer.loadGoogleModel(new File("src/util/resources/tinyw2v.model"), false);
        WordMovers wm = WordMovers.Builder().wordVectors(vectors).build();
        
        try(Stream<String> stream = Files.lines(Paths.get("src/test/resources/gensim_distances.csv"))) {
            
            stream.forEach(x -> {
                String[] triple = x.split(",");
                
                double distance = wm.distance(triple[0].trim(), triple[1].trim());
                double gensimDistance = Double.parseDouble(triple[2].trim());
                
                assertEquals(String.format("%.5f", gensimDistance), String.format("%.5f", distance));
            });
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
