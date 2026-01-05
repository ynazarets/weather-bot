package weather.weathertelegrambot.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.token}")
    private String weatherToken;

    private final String url
            = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={token}&units=metric&lang=ru";

    public String getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(url, String.class, city, weatherToken);
            JSONObject json = new JSONObject(response);
            double temp = json.getJSONObject("main").getDouble("temp");
            String description = json
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");
            String cityName = json.getString("name");
            return String.format("Погода в г. %s: %.1f°C, %s", cityName, temp, description);
        } catch (Exception e) {
            return "Город не найден или ошибка API. Попробуй еще раз.";
        }
    }
}
