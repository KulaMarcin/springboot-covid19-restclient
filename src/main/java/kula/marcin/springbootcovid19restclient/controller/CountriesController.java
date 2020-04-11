package kula.marcin.springbootcovid19restclient.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kula.marcin.springbootcovid19restclient.model.Country;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CountriesController {

    @GetMapping("/countries")
    public String name() throws IOException {

        URL url = new URL("https://api.covid19api.com/countries");
        InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        Type countryListType = new TypeToken<ArrayList<Country>>(){}.getType();
        List<Country> countries = new Gson().fromJson(inputStreamReader, countryListType);

        for(Country c : countries) {
            System.out.println(c.getCountry());
        }

        return "test";
    }

}
