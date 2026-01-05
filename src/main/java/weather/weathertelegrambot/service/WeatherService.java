package weather.weathertelegrambot.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.token}")
    private String weatherToken;

    private final static String URL
            = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={token}&units=metric&lang=ru";

    public String getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(URL, String.class, city, weatherToken);
            JSONObject json = new JSONObject(response);

            JSONObject main = json.getJSONObject("main");
            double temp = main.getDouble("temp");
            double feelsLike = main.getDouble("feels_like");
            int pressureHpa = main.getInt("pressure");
            int humidity = main.getInt("humidity");

            JSONObject wind = json.getJSONObject("wind");
            double windSpeed = wind.getDouble("speed");

            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            String cityName = json.getString("name");

            double pressureMm = pressureHpa * 0.750062;

            return String.format(
                    "🌍 Погода в г. %s\n" +
                            "🌡 Температура: %.1f°C (ощущается как %.1f°C)\n" +
                            "☁️ %s\n" +
                            "💨 Ветер: %.1f м/с\n" +
                            "💎 Давление: %.0f мм рт. ст.\n" +
                            "💧 Влажность: %d%%",
                    cityName, temp, feelsLike, description, windSpeed, pressureMm, humidity
            );
        } catch (Exception e) {
            return "Город не найден. Попробуй еще раз.";
        }
    }
}
