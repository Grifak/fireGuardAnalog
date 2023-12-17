package fire.guard.analog.fireguard;

import fire.guard.analog.fireguard.calculator.Task2Calculator;
import fire.guard.analog.fireguard.common.ApplicationUtils;
import fire.guard.analog.fireguard.common.NumFormatter;
import static fire.guard.analog.fireguard.enums.CacheConstants.*;
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
    private Task2Substance substance;

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
    private TextField coefFreeSpace;
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
    private TextField lenRoom;
    @FXML
    private TextField wedRoom;
    @FXML
    private TextField heiRoom;
    @FXML
    private TextField sMirror;
    @FXML
    private TextField sOkr;
    @FXML
    private TextField liquidEvap;

    //ПУНКТ 6
    @FXML
    private TextField airExchange;
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
    @FXML
    private TextField evapRate;

    //ПУНКТ 7
    @FXML
    private TextField evaporTime;

    //ПУНКТ 8
    @FXML
    private TextField vapourMass;

    //ПУНКТ 9
    @FXML
    private TextField freeSpace;

    //ПУНКТ 10
    @FXML
    private TextField vapourDensity;

    //ПУНКТ 11
    @FXML
    private TextField stechCoef;

    //ПУНКТ 12
    @FXML
    private TextField excesPress;

    //ПУНКТ 13
    @FXML
    private TextField category;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cache = new HashMap<>();
        appUtils = new ApplicationUtils();
        calculator = new Task2Calculator(appUtils);


        capacityVol.setTextFormatter(NumFormatter.getFormatter());
        pipeLenPodv.setTextFormatter(NumFormatter.getFormatter());
        pipeLenOtv.setTextFormatter(NumFormatter.getFormatter());
        coefFreeSpace.setTextFormatter(NumFormatter.getFormatter());
        diameterPipelinePodv.setTextFormatter(NumFormatter.getFormatter());
        diameterPipelineOtv.setTextFormatter(NumFormatter.getFormatter());

        listTask2.getItems().addAll(Task2Substance.getNames());
        listTask2.setOnAction(this::getSubstanceValue);
        tempList.setOnAction(this::calcSixthStep);

        tempList.getItems().addAll(10, 15, 20, 30, 35);
    }

    public void getSubstanceValue(ActionEvent event){
        Task2Substance chooseSubstace = Task2Substance.getByName(listTask2.getValue());
        molarMassField.setText(chooseSubstace.getMolarMass().toString());
        formulaField.setText(chooseSubstace.getFormula());
        antuanA.setText(chooseSubstace.getAntuanA().toString());
        antuanB.setText(chooseSubstace.getAntuanB().toString());
        antuanC.setText(chooseSubstace.getAntuanC().toString());
        densityField.setText(chooseSubstace.getDensity().toString());

        substance = chooseSubstace;
    }

    @FXML
    private void calcFirstStep(ActionEvent event){
        Boolean fieldNotIsEmpty = appUtils.validateFields(capacityVol,
                pipeLenOtv, pipeLenPodv,
                coefFreeSpace, diameterPipelinePodv,
                diameterPipelineOtv, densityField,
                molarMassField);

        if(fieldNotIsEmpty){
            Double pipeLiquidMassCalc = calculator.calcPipeLiquidMass(densityField,
                    capacityVol, diameterPipelinePodv,
                    diameterPipelineOtv, coefFreeSpace,
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
            cache.put(PUMP_LIQUID_MASS.getName(), calcPumpLiquidMass);
            pumpLiquidMass.setText(calcPumpLiquidMass.toString());

            Double calcTechLiquidMass = calculator.calcTechLiquidMass(pipeLiquidMass, pumpLiquidMass);
            cache.put(TECH_LIQUID_MASS.getName(), calcTechLiquidMass);
            techLiquidMass.setText(calcTechLiquidMass.toString());

            Double calcLiquidSpill = calculator.calcLiquidSpill(densityField, techLiquidMass);
            cache.put(LIQUID_SPILL.getName(), calcLiquidSpill);
            liquidSpill.setText(calcLiquidSpill.toString());
        }else {
            pumpLiquidMass.setText(null);
        }
    }

    @FXML
    private void calFifthStep(ActionEvent event){
        Boolean fieldIsNotEmpty = appUtils.validateFields(sMirror, sOkr, lenRoom, heiRoom, wedRoom);
        if (fieldIsNotEmpty){
            Double sRoom = calculator.calcSRoom(lenRoom, wedRoom);
            Double vRoom = calculator.calcVRoom(sRoom, heiRoom);
            cache.put(S_ROOM.getName(), sRoom);
            cache.put(V_ROOM.getName(), vRoom);

            Double calcLiquidEvap = calculator.calcLiquidEvap(sRoom, sOkr, sMirror);
            cache.put(LIQUID_EVAP.getName(), calcLiquidEvap);
            liquidEvap.setText(calcLiquidEvap.toString());
        }else {
            liquidEvap.setText(null);
        }
    }

    @FXML
    private void calcSixthStep(ActionEvent event){
        Boolean fieldIsNotEmpty = appUtils.validateFields(airExchange, lenRoom, antuanA, antuanB, antuanC);
        Double tempVal = tempList.getValue().doubleValue();
        fieldIsNotEmpty = fieldIsNotEmpty && (tempVal != null);

        if(fieldIsNotEmpty) {
            Double airSpeed = calculator.caclAirSpeed(lenRoom, airExchange);
            airSpeed = calculator.interpolationAirSpeed(airSpeed, tempVal);
            cache.put(AIR_SPEED.getName(), airSpeed);

            Double streamPress = calculator.calcStreamPress(antuanA, antuanB, antuanC, tempVal);
            cache.put(STREEM_PRESS.getName(), streamPress);

            Double molarMass = appUtils.getDoubleFromField(molarMassField);
            Double evapRateVal = calculator.calcEvapRate(molarMass, streamPress, airSpeed);

            cache.put(EVAP_RATE.getName(), evapRateVal);
            evapRate.setText(evapRateVal.toString());

            Double evapTimeVal = calculator.calcEvapTime(cache.get(TECH_LIQUID_MASS.getName()), cache.get(LIQUID_EVAP.getName()), evapRateVal);
            cache.put(EVAP_TIME.getName(), evapTimeVal);
            evaporTime.setText(evapTimeVal.toString());

            Double massEvap = calculator.calcMassEvap(cache.get(LIQUID_EVAP.getName()), evapRateVal);
            cache.put(MASS_EVAP.getName(), massEvap);

            Double vapourMassVal = calculator.calcVapourMass(massEvap, evapTimeVal, airExchange);
            cache.put(VAPOUR_MASS.getName(), vapourMassVal);
            vapourMass.setText(vapourMassVal.toString());

            Double freeSpaceVal = calculator.calcFreeSpace(cache.get(V_ROOM.getName()));
            cache.put(FREE_SPACE.getName(), freeSpaceVal);
            freeSpace.setText(freeSpaceVal.toString());


            Double vapourDensityVal = calculator.calcVapourDensity(molarMassField);
            cache.put(VAPOUR_DENSITY.getName(), vapourDensityVal);
            vapourDensity.setText(vapourDensityVal.toString());

            Double stechCoefVal = calculator.calcStechCoef(substance);
            cache.put(STECH_COEF.getName(), stechCoefVal);
            stechCoef.setText(stechCoefVal.toString());

            Double excesPressVal = calculator.calcExcesPress(vapourMassVal, freeSpaceVal, vapourDensityVal, stechCoefVal);
            cache.put(EXCES_PRESS.getName(), excesPressVal);
            excesPress.setText(excesPressVal.toString());

        }else {
            evapRate.setText(null);
        }

    }

}
