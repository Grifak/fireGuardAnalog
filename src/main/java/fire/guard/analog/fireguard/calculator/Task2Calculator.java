package fire.guard.analog.fireguard.calculator;

import fire.guard.analog.fireguard.common.ApplicationUtils;
import fire.guard.analog.fireguard.enums.Task2Substance;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TextField;

public class Task2Calculator {
    private ApplicationUtils appUtils;
    private List<Double> airSpeedList;
    private Map<Double, List<Double>> tempTable;

    public Task2Calculator(ApplicationUtils appUtils) {
        this.appUtils = appUtils;
        airSpeedList = List.of((double) 0, 0.1, 0.2, 0.5, 1.0);
        tempTable = Map.of(10.0 , List.of(1.0, 3.0, 4.6, 6.6, 10.0),
                15.0, List.of(1.0, 2.6, 3.8, 5.7, 8.7),
                20.0, List.of(1.0, 2.4, 3.5, 5.4, 7.7),
                30.0, List.of(1.0, 1.8, 2.4, 3.6, 5.6),
                35.0, List.of(1.0, 1.6, 2.3, 3.2, 4.6)
        );
    }

    public Double calcPipeLiquidMass(TextField density,
                                     TextField capacityVol,
                                     TextField diameterPipelinePodv,
                                     TextField diameterPipelineOtv,
                                     TextField freeSpace,
                                     TextField pipeLenPodv,
                                     TextField pipeLenOtv){

        return appUtils.getDoubleFromField(density) *
                ((appUtils.getDoubleFromField(capacityVol) * appUtils.getDoubleFromField(freeSpace)) +
                        ((appUtils.getDoubleFromField(pipeLenPodv) * (Math.PI * Math.pow(appUtils.getDoubleFromField(diameterPipelinePodv), 2))) / 4) +
                        ((appUtils.getDoubleFromField(pipeLenOtv) * (Math.PI * Math.pow(appUtils.getDoubleFromField(diameterPipelineOtv), 2))) / 4));
    }
    public Double calcPumpLiquidMass(TextField density, TextField pumpFeed, TextField shutOffTime){
        return appUtils.getDoubleFromField(density) * appUtils.getDoubleFromField(pumpFeed) * appUtils.getDoubleFromField(shutOffTime);
    }

    public Double calcTechLiquidMass(TextField pipeLiquidMass, TextField pumpLiquidMass){
        return appUtils.getDoubleFromField(pipeLiquidMass) + appUtils.getDoubleFromField(pumpLiquidMass);
    }

    public Double calcLiquidSpill(TextField densityField, TextField techLiquidMass){
        return appUtils.getDoubleFromField(techLiquidMass)/appUtils.getDoubleFromField(densityField) * 1000;
    }

    public Double calcSRoom(TextField lenRoom, TextField wedRoom){
        return appUtils.getDoubleFromField(lenRoom) * appUtils.getDoubleFromField(wedRoom);
    }

    public Double calcVRoom(Double sRoom, TextField heiRoom){
        return sRoom * appUtils.getDoubleFromField(heiRoom);
    }

    public Double calcLiquidEvap(Double sRoom, TextField sOkr, TextField sMirror){
        return appUtils.getDoubleFromField(sMirror) + appUtils.getDoubleFromField(sOkr) + sRoom;
    }

    public Double caclAirSpeed(TextField lenRoom, TextField airExchange){
        return appUtils.getDoubleFromField(lenRoom) * appUtils.getDoubleFromField(airExchange)/3600;
    }

    public Double calcStreamPress(TextField antuanA, TextField antuanB, TextField antuanC, Double temp){
        return Math.pow(10, appUtils.getDoubleFromField(antuanA) - (appUtils.getDoubleFromField(antuanB)/(appUtils.getDoubleFromField(antuanC) + temp)));
    }

    public Double interpolationAirSpeed(Double airSpeed, Double temp){
        Double xMin = null;
        Double xMax = null;
        int i = 0;
        for (; i < airSpeedList.size(); i++){
            Double curSpeed = airSpeedList.get(i);
            if(airSpeed < curSpeed){
                xMax = curSpeed;
                xMin = airSpeedList.get(i - 1);
                break;
            }
        }

        List<Double> tempList = tempTable.get(temp);
        Double yMax = tempList.get(i);
        Double yMin = tempList.get(i-1);

        return yMin + (airSpeed - xMin)*(yMax - yMin)/(xMax - xMin);
    }

    public Double calcEvapRate(Double molarMass, Double streamPress, Double airSpeed){
        return Math.pow(10, -6) * airSpeed * streamPress * Math.sqrt(molarMass);
    }

    public Double calcEvapTime(Double techLiquidMass, Double liquidEvap, Double evopRate){
        return techLiquidMass/(liquidEvap * evopRate);
    }

    public Double calcMassEvap(Double liquidEvap, Double evapRate){
        return liquidEvap * evapRate * 3600;
    }

    public Double calcVapourMass(Double massEvap, Double evapTime, TextField airExchange){
        if(evapTime > 3600) evapTime = 3600.0;
        return massEvap/(1 + appUtils.getDoubleFromField(airExchange)/3600 * evapTime);
    }

    public Double calcFreeSpace(Double vRoom){
        return 0.8 * vRoom;
    }

    public Double calcVapourDensity(TextField molarMass){
        return appUtils.getDoubleFromField(molarMass)/(22.4 * (1 + (0.00367*61)));
    }

    public Double calcStechCoef(Task2Substance substance){
        Double betta = substance.getnC() + ((substance.getnH() - substance.getnX())/4.0)-(substance.getnO()/2.0);

        return 100/(1 + (4.84*betta));
    }

    public Double calcExcesPress(Double vapourMass, Double freeSpace, Double vapourDensity, Double stechCoef){
        return (900 - 101) * ((vapourMass * 0.3)/(freeSpace * vapourDensity)) * (100 / stechCoef) * (1/3.0);
    }

}
