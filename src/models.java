import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;

public class models {
    private static double[] stableDiffusion(double mu, double sigma, int timesteps, int nSamples, double noiseScale) {
        Random rand = new Random();
        double[] samples = new double[nSamples];
        for (int i = 0; i < nSamples; i++) {
            samples[i] = mu + sigma * rand.nextGaussian();
        }
        for (int t = 0; t < timesteps; t++) {
            for (int i = 0; i < nSamples; i++) {
                samples[i] += noiseScale * rand.nextGaussian();
            }
        }
        return samples;
    }

    public static void main(String[] args) {
        double mu = 0, sigma = 1;
        int timesteps = 100, nSamples = 1000;
        double noiseScale = 0.1;

        double[] samples = stableDiffusion(mu, sigma, timesteps, nSamples, noiseScale);

        for (double sample : samples) {
            System.out.println(sample);
        }
    }
}
