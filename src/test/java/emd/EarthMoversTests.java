package emd;

import com.crtomirmajer.wmd4j.emd.EarthMovers;
import com.telmomenezes.jfastemd.JFastEMD;
import org.junit.Test;

import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Telmo Menezes (telmo@telmomenezes.com)
 */
public class EarthMoversTests {
    
    EarthMovers earthMovers = new EarthMovers();
    Random      random      = new Random();
    
    double[] randomVector(int size) {
        
        double[] vector = new double[size];
        
        for(int i = 0 ; i < size ; i++) {
            vector[i] = random.nextDouble() % 10;
        }
        
        return vector;
    }
    
    double[][] matrix(double[] a, double[] b) {
        
        int size = a.length >= b.length ? a.length : b.length;
        
        double matrix[][] = new double[size][size];
        
        for(int i = 0 ; i < size ; i++) {
            for(int j = 0 ; j < size ; j++) {
                matrix[i][j] = random.nextDouble() % 10;
            }
        }
        
        return matrix;
    }
    
    Vector<Double> makeVector(double[] a) {
        return new Vector<>(DoubleStream.of(a).boxed().collect(Collectors.toList()));
    }
    
    double jfastemd(double[] a, double[] b, double[][] matrix, double mass) {
        Vector<Double> vecA = makeVector(a);
        Vector<Double> vecB = makeVector(b);
        Vector<Vector<Double>> vecMatrix = new Vector<>();
        
        for(double[] array : matrix) {
            vecMatrix.add(makeVector(array));
        }
        
        return JFastEMD.emdHat(vecA, vecB, vecMatrix, mass);
    }
    
    @Test
    public void distance() {
        
        for(int i = 0 ; i < 10000 ; i++) {
            
            int size = random.nextInt(10) + 1;
            
            double[] a = randomVector(size);
            double[] b = randomVector(size);
            double[][] m = matrix(a, b);
            
            assertEquals(jfastemd(a, b, m, 1), earthMovers.distance(a, b, m, 1));
        }
        
    }
    
    @Test
    public void performanceComparison() {
        
        int size = 20;
        double[] a = randomVector(size);
        double[] b = randomVector(size);
        double[][] m = matrix(a, b);
        
        int repeats = 10000;
    
        //warmup
        for(int i = 0 ; i < repeats ; i++) {
            earthMovers.distance(a, b, m, 1);
            jfastemd(a, b, m, 1);
        }
        
        
        long start = System.nanoTime();
        
        for(int i = 0 ; i < repeats ; i++) {
            jfastemd(a, b, m, 1);
        }
        
        System.out.println((System.nanoTime() - start) + " = jfast");
        
        start = System.nanoTime();
        for(int i = 0 ; i < repeats ; i++) {
            earthMovers.distance(a, b, m, 1);
        }
        
        System.out.println((System.nanoTime() - start) + " = em");
        
    }
    
}
