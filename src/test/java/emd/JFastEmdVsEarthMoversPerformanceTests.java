package emd;

import com.crtomirmajer.wmd4j.emd.EarthMovers;
import org.junit.Test;

/**
 * Created by Majer on 22. 09. 2016.
 */
public class JFastEmdVsEarthMoversPerformanceTests {
    
    EarthMovers earthMovers = new EarthMovers();
    
    @Test
    public void performanceComparison() {
        
        int size = 20;
        double[] a = EarthMoversUtils.randomVector(size);
        double[] b = EarthMoversUtils.randomVector(size);
        double[][] m = EarthMoversUtils.matrix(a, b);
        
        int repeats = 10000;
        
        //warm up
        for(int i = 0 ; i < repeats ; i++) {
            earthMovers.distance(a, b, m, 1);
            EarthMoversUtils.jfastemd(a, b, m, 1);
        }
        
        long start = System.nanoTime();
        
        for(int i = 0 ; i < repeats ; i++) {
            EarthMoversUtils.jfastemd(a, b, m, 1);
        }
        
        System.out.println((System.nanoTime() - start) + " = jfastemd");
        
        start = System.nanoTime();
        for(int i = 0 ; i < repeats ; i++) {
            earthMovers.distance(a, b, m, 1);
        }
        
        System.out.println((System.nanoTime() - start) + " = optimized-jfastemd");
        
    }
}
