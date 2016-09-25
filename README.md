# wmd4j

**wmd4j** is a Java library for computing [Word Mover's Distance](https://github.com/mkusner/wmd) (WMD) between 2 text documents. It provides same functionality as [Word2Vec.wmdistance](https://radimrehurek.com/gensim/models/word2vec.html#gensim.models.word2vec.Word2Vec.wmdistance) in Gensim.

wmd4j depends on [deeplearning4j](https://github.com/deeplearning4j/deeplearning4j) [WordVectors interface](http://deeplearning4j.org/doc/org/deeplearning4j/models/embeddings/wordvectors/WordVectors.html) for word vectors manipulation and uses optimized version of [JFastEMD](https://github.com/telmomenezes/JFastEMD) (Earth Mover's Distance transportaion problem) underneath, which is about 1.8x faster.

# Usage

```java

WordVectors vectors = WordVectorSerializer.loadGoogleModel(new File(word2vecPath), false);
WordMovers wm = WordMovers.Builder().wordVectors(vectors).build();

wm.distance("obama speaks to the media in illinois", "the president greets the press in chicago");
```

# Validation

wmd4j is validated against Gensim's wmdistance results on custom word2vec model.






