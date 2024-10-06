package com.mpt.journal.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {

    @GetMapping("/calculate")
    public String showCalculator() {
        return "main";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam(name = "num1", required = true) Double num1,
            @RequestParam(name = "num2", required = true) Double num2,
            @RequestParam(name = "operation", required = true) String operation,
            Model model) {

        if (num1 == null || num2 == null || operation == null) {
            model.addAttribute("error", "Все поля обязательны для заполнения.");
            return "main";
        }

        double result = 0;
        try {
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        model.addAttribute("error", "Ошибка: Деление на ноль невозможно.");
                        return "main";
                    }
                    break;
                default:
                    model.addAttribute("error", "Ошибка: Неизвестная операция.");
                    return "main";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Произошла ошибка во время вычислений.");
            return "main";
        }

        model.addAttribute("result", result);
        return "main";
    }

    @GetMapping("/converter")
    public String converter(
            @RequestParam(name = "val1", required = false) String val1,
            @RequestParam(name = "val2", required = false) String val2,
            @RequestParam(name = "amount", required = false) Double amount,
            Model model) {

        if (amount != null && val1 != null && val2 != null) {
            double conversionRate = getConversionRate(val1, val2);
            double result = amount * conversionRate;

            String formattedResult = String.format("%.2f", result);

            model.addAttribute("amount", amount);
            model.addAttribute("val1", val1);
            model.addAttribute("val2", val2);
            model.addAttribute("result", formattedResult);
        } else {
            model.addAttribute("error", "Пожалуйста, введите сумму и выберите валюты.");
        }
        return "converter";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    private double getConversionRate(String val1, String val2) {
        if (val1.equals("USD") && val2.equals("EUR")) {
            return 0.85;
        } else if (val1.equals("EUR") && val2.equals("USD")) {
            return 1.18;
        } else if (val1.equals("USD") && val2.equals("RUB")) {
            return 75;
        } else if (val1.equals("RUB") && val2.equals("USD")) {
            return 0.013;
        } else if (val1.equals("EUR") && val2.equals("RUB")) {
            return 88;
        } else if (val1.equals("RUB") && val2.equals("EUR")) {
            return 0.011;
        } else {
            return 1;
        }
    }
}
