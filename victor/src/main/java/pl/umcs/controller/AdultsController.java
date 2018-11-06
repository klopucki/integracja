package pl.umcs.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umcs.model.AdultsPeople;
import pl.umcs.repository.AdultsPeopleRepository;

import java.util.List;

@RestController
@RequestMapping("/adults")
public class AdultsController {

    private final AdultsPeopleRepository adultsPeopleRepository;

    @Autowired
    public AdultsController(AdultsPeopleRepository adultsPeopleRepository) {
        this.adultsPeopleRepository = adultsPeopleRepository;
    }

    @GetMapping
    public List<AdultsPeople> listPeople() {
        return Lists.newArrayList(adultsPeopleRepository.findAll());
    }
}
