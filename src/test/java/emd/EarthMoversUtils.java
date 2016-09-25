package emd;

import com.telmomenezes.jfastemd.JFastEMD;

import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by Majer on 22. 09. 2016.
 */
public class EarthMoversUtils {
    
    static Random random = new Random();
    
    static double[] randomVector(int size) {
        
        double[] vector = new double[size];
        
        for(int i = 0 ; i < size ; i++) {
            vector[i] = random.nextDouble() % 10;
        }
        
        return vector;
    }
    
    static double[][] matrix(double[] a, double[] b) {
        
        int size = a.length >= b.length ? a.length : b.length;
        
        double matrix[][] = new double[size][size];
        
        for(int i = 0 ; i < size ; i++) {
            for(int j = 0 ; j < size ; j++) {
                matrix[i][j] = random.nextDouble() % 10;
            }
        }
        
        return matrix;
    }
    
    static Vector<Double> makeVector(double[] a) {
        return new Vector<>(DoubleStream.of(a).boxed().collect(Collectors.toList()));
    }
    
    static double jfastemd(double[] a, double[] b, double[][] matrix, double mass) {
        Vector<Double> vecA = makeVector(a);
        Vector<Double> vecB = makeVector(b);
        Vector<Vector<Double>> vecMatrix = new Vector<>();
        
        for(double[] array : matrix) {
            vecMatrix.add(makeVector(array));
        }
        
        return JFastEMD.emdHat(vecA, vecB, vecMatrix, mass);
    }
    
}
