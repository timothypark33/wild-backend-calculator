package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.CalculationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
    private final Map<String, Operator> operators = new HashMap<>();
    //    private final Map<String,Operator> operators = new HashMap<>();
    private final CalculationRepository calculationRepository = CalculationRepository.getInstance();

    public Calculator() {
        operators.put("+", new OperatorPlus());
        operators.put("/", new OperatorDivide());
        operators.put("-", new OperatorMinus());
        operators.put("*", new OperatorMultiply());
    }

    public Calculation calculate(
            int number1, int number2, String operator) {
        Operator op = operators.get(operator);
        int result = op.calculate(number1, number2);

        Calculation calculation = new Calculation(number1, number2, operator, result);
        calculationRepository.add(calculation);

        return calculation;
    }

    public List<Calculation> getCalculations() {
        return calculationRepository.getCalculations();
    }
}
