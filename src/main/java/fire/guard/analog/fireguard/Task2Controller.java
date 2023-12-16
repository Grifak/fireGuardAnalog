package fire.guard.analog.fireguard;

import fire.guard.analog.fireguard.calculator.Task2Calculator;
import fire.guard.analog.fireguard.common.ApplicationUtils;
import fire.guard.analog.fireguard.common.NumFormatter;
import fire.guard.analog.fireguard.enums.Task2Substance;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Task2Controller implements Initializable {
    private Map<String, Double> cache;
    private Task2Calculator calculator;
    private ApplicationUtils appUtils;

    @FXML
    private ChoiceBox<String> listTask2;
    @FXML
    private TextField molarMassField;
    @FXML
    private TextField densityField;
    //ПУНКТ 1
    @FXML
    private TextField capacityVol;
    @FXML
    private TextField pipeLenPodv;
    @FXML
    private TextField pipeLenOtv;
    @FXML
    private TextField freeSpace;
    @FXML
    private TextField diameterPipelinePodv;
    @FXML
    private TextField diameterPipelineOtv;
    @FXML
    private TextField pipeLiquidMass;

    //ПУНКТ 2
    @FXML
    private TextField pumpFeed;
    @FXML
    private TextField shutOffTime;
    @FXML
    private TextField pumpLiquidMass;

    //ПУНКТ 3
    @FXML
    private TextField techLiquidMass;

    //ПУНКТ 4
    @FXML
    private TextField liquidSpill;

    //ПУНКТ 5
    @FXML
    private TextField sRoom;
    @FXML
    private TextField sMirror;
    @FXML
    private TextField sOkr;
    @FXML
    private TextField liquidEvap;

    //ПУНКТ 6
    @FXML
    private TextField airSpeed;
    @FXML
    private TextField airExchange;
    @FXML
    private TextField lenRoom;
    @FXML
    private TextField formulaField;
    @FXML
    private ChoiceBox<Integer> tempList;
    @FXML
    private TextField antuanA;
    @FXML
    private TextField antuanB;
    @FXML
    private TextField antuanC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cache = new HashMap<>();
        calculator = new Task2Calculator();
        appUtils = new ApplicationUtils();

        capacityVol.setTextFormatter(NumFormatter.getFormatter());
        pipeLenPodv.setTextFormatter(NumFormatter.getFormatter());
        pipeLenOtv.setTextFormatter(NumFormatter.getFormatter());
        freeSpace.setTextFormatter(NumFormatter.getFormatter());
        diameterPipelinePodv.setTextFormatter(NumFormatter.getFormatter());
        diameterPipelineOtv.setTextFormatter(NumFormatter.getFormatter());

        listTask2.getItems().addAll(Task2Substance.getNames());
        listTask2.setOnAction(this::getSubstanceValue);

        tempList.getItems().addAll(10, 15, 20, 30, 35);
    }

    public void getSubstanceValue(ActionEvent event){
        Task2Substance substance = Task2Substance.getByName(listTask2.getValue());
        molarMassField.setText(substance.getMolarMass().toString());
        formulaField.setText(substance.getFormula());
        antuanA.setText(substance.getAntuanA().toString());
        antuanB.setText(substance.getAntuanB().toString());
        antuanC.setText(substance.getAntuanC().toString());
        densityField.setText(substance.getDensity().toString());
    }

    @FXML
    private void calcFirstStep(ActionEvent event){
        Boolean fieldNotIsEmpty = appUtils.validateFields(capacityVol,
                pipeLenOtv, pipeLenPodv,
                freeSpace, diameterPipelinePodv,
                diameterPipelineOtv, densityField,
                molarMassField);

        if(fieldNotIsEmpty){
            Double pipeLiquidMassCalc = calculator.calcPipeLiquidMass(densityField,
                    capacityVol, diameterPipelinePodv,
                    diameterPipelineOtv, freeSpace,
                    pipeLenPodv, pipeLenOtv);
            cache.put("pipeLiquidMass", pipeLiquidMassCalc);
            pipeLiquidMass.setText(pipeLiquidMassCalc.toString());
        }else {
            pipeLiquidMass.setText(null);
        }
    }

    @FXML
    private void calcSecondStep(ActionEvent event){
        Boolean fieldIsNotEmpty = appUtils.validateFields(pumpFeed, shutOffTime);
        if(fieldIsNotEmpty){
            Double calcPumpLiquidMass = calculator.calcPumpLiquidMass(densityField, pumpFeed, shutOffTime);
            cache.put("pumpLiquidMass", calcPumpLiquidMass);
            pumpLiquidMass.setText(calcPumpLiquidMass.toString());

            Double calcTechLiquidMass = calculator.calcTechLiquidMass(pipeLiquidMass, pumpLiquidMass);
            cache.put("techLiquidMass", calcTechLiquidMass);
            techLiquidMass.setText(calcTechLiquidMass.toString());

            Double calcLiquidSpill = calculator.calcLiquidSpill(densityField, techLiquidMass);
            cache.put("liquidSpill", calcLiquidSpill);
            liquidSpill.setText(calcLiquidSpill.toString());
        }else {
            pumpLiquidMass.setText(null);
        }
    }

    @FXML
    private void calFifthStep(ActionEvent event){
        Boolean fieldIsNotEmpty = appUtils.validateFields(sMirror, sOkr, sRoom);
        if (fieldIsNotEmpty){
            Double calcLiquidEvap = calculator.calcLiquidEvap(sRoom, sOkr, sRoom);
            cache.put("calcLiquidEvap", calcLiquidEvap);
            liquidEvap.setText(calcLiquidEvap.toString());
        }else {
            liquidEvap.setText(null);
        }
    }

    @FXML
    private void calcSixthStep(ActionEvent event){

    }

}
