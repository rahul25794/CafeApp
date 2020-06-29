package com.cafe.app.Controller;

import java.util.List;

import com.cafe.app.objects.CoffeeMachine;
import com.cafe.app.service.MachineService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CafeController.BASE_URL)
public class CafeController {
    public static final String BASE_URL = "";

    @PostMapping()
    public CoffeeMachine addMachine(@RequestParam(required = true) String name,
            @RequestParam(required = true) Integer numberOfOutlets) throws Exception {
        return MachineService.addMachine(name, numberOfOutlets);
    }

    /*
     * @GetMapping(value = "/{id}") public Bet get(@PathVariable(value = "id",
     * required = true) Integer id) throws Exception { return betRepo.get(id); }
     */
    @GetMapping()
    public List<CoffeeMachine> getAllMachine() throws Exception {
        return MachineService.getAllMachine();
    }
}