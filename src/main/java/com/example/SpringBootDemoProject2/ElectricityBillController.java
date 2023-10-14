package com.example.SpringBootDemoProject2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ElectricityBillController {

    @GetMapping("/")
    public String showElectricityBillForm() {
        return "electricity-bill-form";
    }

    @PostMapping("/")
    public String calculateElectricityBill(@RequestParam("units") int consumedUnits, Model model) {
        double unitRate1 = 3.50;
        double unitRate2 = 4.00;
        double unitRate3 = 5.20;
        double unitRate4 = 6.50;
        double billAmount;

        if (consumedUnits <= 50) {
            billAmount = consumedUnits * unitRate1;
        } else if (consumedUnits <= 150) {
            billAmount = (50 * unitRate1) + ((consumedUnits - 50) * unitRate2);
        } else if (consumedUnits <= 250) {
            billAmount = (50 * unitRate1) + (100 * unitRate2) + ((consumedUnits - 150) * unitRate3);
        } else {
            billAmount = (50 * unitRate1) + (100 * unitRate2) + (100 * unitRate3) + ((consumedUnits - 250) * unitRate4);
        }

        model.addAttribute("units", consumedUnits);
        model.addAttribute("billAmount", billAmount);

        return "electricity-bill-result";
    }
}