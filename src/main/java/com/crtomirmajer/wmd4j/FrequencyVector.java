package com.crtomirmajer.wmd4j;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Created by Majer on 21.9.2016.
 */
public class FrequencyVector {
    
    private volatile long     frequency;
    private          INDArray vector;
    
    public FrequencyVector(INDArray vector) {
        this(1, vector);
    }
    
    public FrequencyVector(long frequency, INDArray vector) {
        this.frequency = frequency;
        this.vector = vector;
    }
    
    public void incrementFrequency() {
        frequency += 1;
    }
    
    public long getFrequency() {
        return frequency;
    }
    
    public INDArray getVector() {
        return vector;
    }
}
