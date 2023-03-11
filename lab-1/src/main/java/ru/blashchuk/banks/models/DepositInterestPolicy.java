package ru.blashchuk.banks.models;

import java.util.List;

public class DepositInterestPolicy {
    private List<Double> pointsOfInterestRaise;
    private List<Double> interestValues;

    public DepositInterestPolicy(List<Double> pointsOfInterestRaise, List<Double> interestValues) {
        if (interestValues.size() == 0) {
            throw new RuntimeException();
        }

        if (!pointsOfInterestRaise.equals(pointsOfInterestRaise.stream().sorted().toList())) {
            throw new RuntimeException();
        }

        if (pointsOfInterestRaise.size() != interestValues.size() - 1) {
            throw new RuntimeException();
        }

        this.interestValues = List.copyOf(interestValues);
        this.pointsOfInterestRaise = List.copyOf(pointsOfInterestRaise);
    }

    public List<Double> getPointsOfInterestRaise() {
        return pointsOfInterestRaise;
    }

    public List<Double> getInterestValues() {
        return interestValues;
    }

    public Double evaluateInterestValue(Double funds) {
        int index = -1;
        for (Double point : this.pointsOfInterestRaise) {
            if (funds.compareTo(point) > 0) {
                index += 1;
            }
        }
        return this.interestValues.get(index + 1);
    }
}