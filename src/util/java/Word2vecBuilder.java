import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Majer on 23. 09. 2016.
 */
public class Word2vecBuilder {


    public static void main(String[] args) throws IOException {

        SentenceIterator iterator = new LineSentenceIterator(new File("src/util/resources/text.txt"));

        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        Word2Vec vec = new Word2Vec.Builder()
                             .minWordFrequency(1)
                             .iterations(20)
                             .layerSize(50)
                             .seed(42)
                             .windowSize(5)
                             .iterate(iterator)
                             .tokenizerFactory(t)
                             .build();
        vec.fit();

        File modelPath = new File("src/util/resources/tinyw2v.model");
        WordVectorSerializer.writeWordVectors(vec, modelPath);
//        WordVectorSerializer.writeFullModel(vec, modelPath.getAbsolutePath());

        WordVectors vectors = WordVectorSerializer.loadGoogleModel(modelPath, false);
        List<String> nearby = vectors.similarWordsInVocabTo("wordvec", 10);

        assert nearby.size() == 10;
    }

}
