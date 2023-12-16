package fire.guard.analog.fireguard.calculator;

import javafx.scene.control.TextField;

public class Task2Calculator {
    public Double calcPipeLiquidMass(TextField density,
                                            TextField capacityVol,
                                            TextField diameterPipelinePodv,
                                            TextField diameterPipelineOtv,
                                            TextField freeSpace,
                                            TextField pipeLenPodv,
                                            TextField pipeLenOtv){

        return getDubleFromField(density) *
                ((getDubleFromField(capacityVol) * getDubleFromField(freeSpace)) +
                        ((getDubleFromField(pipeLenPodv) * (Math.PI * Math.pow(getDubleFromField(diameterPipelinePodv), 2))) / 4) +
                        ((getDubleFromField(pipeLenOtv) * (Math.PI * Math.pow(getDubleFromField(diameterPipelineOtv), 2))) / 4));
    }
    public Double calcPumpLiquidMass(TextField density, TextField pumpFeed, TextField shutOffTime){
        return getDubleFromField(density) * getDubleFromField(pumpFeed) * getDubleFromField(shutOffTime);
    }

    public Double calcTechLiquidMass(TextField pipeLiquidMass, TextField pumpLiquidMass){
        return getDubleFromField(pipeLiquidMass) + getDubleFromField(pumpLiquidMass);
    }

    public Double calcLiquidSpill(TextField densityField, TextField techLiquidMass){
        return getDubleFromField(techLiquidMass)/getDubleFromField(densityField);
    }

    public Double calcLiquidEvap(TextField sRoom, TextField sOkr, TextField sMirror){
        return getDubleFromField(sMirror) + getDubleFromField(sOkr) + getDubleFromField(sMirror);
    }

    private static Double getDubleFromField(TextField textField){
        try {
            return Double.valueOf(textField.getText());
        }catch (NumberFormatException ex){
            return Double.valueOf(textField.getText().concat(".0"));
        }
    }
}
