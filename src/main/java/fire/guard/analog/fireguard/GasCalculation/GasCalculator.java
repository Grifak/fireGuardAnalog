package fire.guard.analog.fireguard.GasCalculation;

public class GasCalculator {
    private static final double V0 = 22.4;
    private static final double PI = Math.PI;

    //1 Method to calculate Va
    public static double calculateVa(double P, double V) {
        return 0.01 * P * V;
    }

    //2 Method to calculate V1m
    public static double calculateV1m(double q, double tau) {
        return q * tau;
    }

    //3 Method to calculate V2m
    public static double calculateV2m(double P2, double lPodv, double dPodv, double lOtv, double dOtv) {
        return 0.01 * PI * P2 * (((lPodv * Math.pow(dPodv, 2))/4) + ((lOtv * Math.pow(dOtv, 2))/4));
    }

    //4 Method to calculate ρg
    public static double calculateRhoG(double M, double T) {
        return M / (V0 * (1 + 0.00367 * T));
    }

    //5 Method to calculate m
    public static double calculateM(double Va, double Vm, double rho) {
        return (Va + Vm) * rho;
    }

    //6 Method to calculate m*
    public static double calculateMStar(double m, double A, double tau) {
        return m / (1 + A * tau);
    }
    //8 Method to calculate Vсв
    public static double calculateVsv(double L, double B, double H) {
        double Vpom = L * B * H;
        return 0.8 * Vpom;
    }

    //9 Method to calculate Сстех
    public static double calculateCsteh(double nC, double nH, double nX, double nO) {
        double beta = nC + ((nH - nX) / 4) - (nO / 2);
        return 100 / (1 + (4.84 * beta));
    }
    //11 Method to calculate ΔP
    public static double calculateDeltaP(double Pmax, double P0, double m, double Z, double Vsv, double rhoG, double Csteh, double Kn) {
        return (Pmax - P0) * ((m * Z) / (Vsv * rhoG)) * (100 / Csteh) * (1 / Kn);
    }
}
