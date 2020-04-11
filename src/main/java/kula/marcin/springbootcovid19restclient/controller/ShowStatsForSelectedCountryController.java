package kula.marcin.springbootcovid19restclient.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kula.marcin.springbootcovid19restclient.model.StatsForCountry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ShowStatsForSelectedCountryController {

    @GetMapping("/show-stats-for-country")
    public String showStatsForCountr(@RequestParam("country-code") String countryCode, @RequestParam("country-name") String countryName, Model model) throws IOException {

        URL url = new URL("https://api.covid19api.com/dayone/country/"+countryCode+"/status/confirmed");
        InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        Type countryListType = new TypeToken<ArrayList<StatsForCountry>>(){}.getType();
        List<StatsForCountry> statsForCountry = new Gson().fromJson(inputStreamReader, countryListType);

        StatsForCountry firstCase = statsForCountry.get(0);
        StatsForCountry lastCase = statsForCountry.get(statsForCountry.size()-1);

        String firstCaseDate = firstCase.getDate().substring(0, 10);
        Collections.sort(statsForCountry);

        for(StatsForCountry s : statsForCountry) {
            s.setDate(s.getDate().substring(0, 10));
        }

        model.addAttribute("countryName", countryName);
        model.addAttribute("firstCaseDate", firstCaseDate);
        model.addAttribute("lastCase", lastCase);
        model.addAttribute("statsForCountry", statsForCountry);

        return "stats-for-selected-country";
    }

}
