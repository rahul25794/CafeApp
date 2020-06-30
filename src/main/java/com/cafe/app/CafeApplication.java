package com.cafe.app;

import java.util.ArrayList;
import java.util.List;

import com.cafe.app.objects.Coffee;
import com.cafe.app.objects.CoffeeMachine;
import com.cafe.app.objects.Ingrediant;
import com.cafe.app.service.MachineService;
import com.cafe.app.service.RecipeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class CafeApplication {
	public static void main(String[] args) {
		SpringApplication.run(CafeApplication.class, args);
		populateDemoData();
	}

	private static void populateDemoData() {
		try {
			CoffeeMachine machine = new CoffeeMachine("Cafe-1", 1);
			MachineService.addMachine(machine);

			Coffee coffee = new Coffee("Cappuccino");
			List<Ingrediant> ingrediants = new ArrayList<>();
			ingrediants.add(new Ingrediant("Milk", 1d));
			ingrediants.add(new Ingrediant("Sugar", 1.5d));
			coffee.setIngrediants(ingrediants);

			MachineService.addRecipe(machine.getName(), coffee);
			MachineService.addIngrediant(machine.getName(), new Ingrediant("Milk", 10d));
			MachineService.addIngrediant(machine.getName(), new Ingrediant("Sugar", 20d));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
