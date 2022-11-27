package utilities;

public class Calculation {

    private final double compressiveStrength;
    private final double water_cementRatio;
    private final double rubberSize;
    private final double percentageLevel;

    private double upperLimitWaterCement;
    private double lowerLimitWaterCement;

    private double upperLimitRubber;
    private double lowerLimitRubber;

    private double upperLimit;
    private double lowerLimit;

    private double upperValue;
    private double lowerValue;

    private int waterCementGrade;
    private int rubberGrade;

    public Calculation(double compressiveStrength,double water_cementRatio,double rubberSize,double percentageLevel) {
        this.compressiveStrength = compressiveStrength;
        this.water_cementRatio = water_cementRatio;
        this.rubberSize = rubberSize;
        this.percentageLevel = percentageLevel;
        assignGrades();
        calculationWaterCementRatio();
        calculationRubber();
        calculateOverallLimits();
        calculateValues();
    }

    public void assignGrades(){
        if (compressiveStrength >= 37.5){
            waterCementGrade = 40;
            if (compressiveStrength > 37.5){
                rubberGrade = 40;
            }else {
                rubberGrade = 35;
            }
        }else if (compressiveStrength >= 32.5){
            waterCementGrade = 35;
            if (compressiveStrength > 32.5){
                rubberGrade = 35;
            }else {
                rubberGrade = 30;
            }
        }else {
            waterCementGrade = 30;
            rubberGrade = 30;
        }
    }

    public void calculationWaterCementRatio(){
        switch (waterCementGrade){
            case 30:
                upperLimitWaterCement = 45.58+(0.554*percentageLevel)-(207*water_cementRatio)+(0.033*Math.pow(percentageLevel,2))
                        +(0.368*percentageLevel*water_cementRatio)+(288.1*Math.pow(water_cementRatio,2));
                lowerLimitWaterCement = 99.11+(0.947*percentageLevel)-(380.2*water_cementRatio)-(0.007*Math.pow(percentageLevel,2))
                        +(2.201*percentageLevel*water_cementRatio)+(324.3*Math.pow(water_cementRatio,2));
                break;

            case 35:
                upperLimitWaterCement = -285.7+(2.344*percentageLevel)+(1064*water_cementRatio)+(0.057*Math.pow(percentageLevel,2))
                        -(4.287*percentageLevel*water_cementRatio)-(861.8*Math.pow(water_cementRatio,2));
                lowerLimitWaterCement = -182.5+(7.742*percentageLevel)+(536.6*water_cementRatio)-(0.046*Math.pow(percentageLevel,2))
                        -(9.268*percentageLevel*water_cementRatio)+(324.3*Math.pow(water_cementRatio,2));
                break;

            case 40:
                upperLimitWaterCement = 173+(2.176*percentageLevel)-(523.8*water_cementRatio)+(0.001*Math.pow(percentageLevel,2))
                        -(0.548*percentageLevel*water_cementRatio)+(407.3*Math.pow(water_cementRatio,2));
                lowerLimitWaterCement = 4.49+(4.061*percentageLevel)-(41.1*water_cementRatio)+(0.021*Math.pow(percentageLevel,2))
                        -(3.764*percentageLevel*water_cementRatio)+(48.83*Math.pow(water_cementRatio,2));
                break;
        }
        upperLimitWaterCement = Math.round(upperLimitWaterCement*100.0)/100.0;
        lowerLimitWaterCement = Math.round(lowerLimitWaterCement*100.0)/100.0;
    }

    public void calculationRubber(){
        switch (rubberGrade){
            case 30:
                upperLimitRubber = 42.11+(1.776*percentageLevel)-(12.67*rubberSize)+(0.001*Math.pow(percentageLevel,2))
                        +(0.036*percentageLevel*rubberSize)+(1.004*Math.pow(rubberSize,2));
                lowerLimitRubber = -10.24+(2.993*percentageLevel)+(5.017*rubberSize)-(0.06*Math.pow(percentageLevel,2))
                        +(0.043*percentageLevel*rubberSize)-(1.087*Math.pow(rubberSize,2));
                break;

            case 35:
                upperLimitRubber = 6.044+(3.639*percentageLevel)+(0.956*rubberSize)-(0.049*Math.pow(percentageLevel,2))
                        +(0.045*percentageLevel*rubberSize)-(0.464*Math.pow(rubberSize,2));
                lowerLimitRubber = -19.75+(3.242*percentageLevel)+(14.76*rubberSize)+(0.035*Math.pow(percentageLevel,2))
                        -(0.861*percentageLevel*rubberSize)-(1.316*Math.pow(rubberSize,2));
                break;

            case 40:
                upperLimitRubber = 38.04+(2.123*percentageLevel)-(24.83*rubberSize)-(0.003*Math.pow(percentageLevel,2))
                        +(0.135*percentageLevel*rubberSize)+(3.571*Math.pow(rubberSize,2));
                lowerLimitRubber = 25.15+(3.671*percentageLevel)-(22.44*rubberSize)-(0.093*Math.pow(percentageLevel,2))
                        +(0.077*percentageLevel*rubberSize)+(3.104*Math.pow(rubberSize,2));
                break;
        }
        upperLimitRubber = Math.round(upperLimitRubber*100.0)/100.0;
        lowerLimitRubber = Math.round(lowerLimitRubber*100)/100.0;
    }

    public void calculateOverallLimits(){
        if (upperLimitWaterCement > upperLimitRubber){
            upperLimit = upperLimitWaterCement;
        }else {
            upperLimit = upperLimitRubber;
        }

        if (lowerLimitWaterCement > lowerLimitRubber){
            lowerLimit = lowerLimitRubber;
        }else {
            lowerLimit = lowerLimitWaterCement;
        }
    }

    public void calculateValues(){
        lowerValue = compressiveStrength * ((100-upperLimit)/100);
        lowerValue = Math.round(lowerValue*100.0)/100.0;
        if (lowerLimit < 0)
            upperValue = compressiveStrength;
        else
            upperValue = compressiveStrength * ((100-lowerLimit)/100);
        upperValue = Math.round(upperValue*100.0)/100.0;
    }

    public String[] getValues(){
        Double[] arr;
        arr = new Double[]{upperLimit, lowerLimit, upperValue, lowerValue};
        String[] values = new String[arr.length];
        for (int i=0; i<values.length; i++){
            values[i] = arr[i].toString();
        }
        return  values;
    }

}
