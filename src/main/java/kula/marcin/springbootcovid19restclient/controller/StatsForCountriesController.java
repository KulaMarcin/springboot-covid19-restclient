package kula.marcin.springbootcovid19restclient.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kula.marcin.springbootcovid19restclient.model.StatsForCountry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatsForCountriesController {

    @GetMapping("/stats-for-country")
    public String name() throws IOException {

        URL url = new URL("https://api.covid19api.com/dayone/country/poland/status/confirmed");
        InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        Type countryListType = new TypeToken<ArrayList<StatsForCountry>>(){}.getType();
        List<StatsForCountry> statsForCountries = new Gson().fromJson(inputStreamReader, countryListType);

        for(StatsForCountry c : statsForCountries) {
            System.out.println(c.getCountry());
            System.out.println(c.getCases());
            System.out.println(c.getDate());
            System.out.println();
        }

        return "test";
    }

}
