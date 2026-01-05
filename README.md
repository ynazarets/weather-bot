# Weather Telegram Bot 🌦️

A Spring Boot-based Telegram bot that provides real-time weather information for any city worldwide using the OpenWeatherMap API.

## 🚀 Features
* **Global Coverage**: Get current weather data for any city by its name.
* **Detailed Metrics**: Includes temperature (°C), weather description, wind speed (m/s), and atmospheric pressure (mmHg).
* **Localization**: Weather descriptions are provided in Russian by default.
* **Spring Boot Integration**: Built with production-ready standards using Spring Boot 3.

## 🛠 Tech Stack
* **Java 23**
* **Spring Boot 3.4.1**
* **Telegram Bots Spring Boot Starter**: For seamless Telegram API interaction.
* **JSON (org.json)**: For parsing OpenWeatherMap API responses.
* **Maven**: For dependency management and project build.
* **Lombok**: To reduce boilerplate code.

## ⚙️ Configuration & Setup

### 1. Obtain API Credentials
* **Telegram Bot Token**: Create a bot and get your token from [@BotFather](https://t.me/BotFather).
* **OpenWeatherMap API Key**: Sign up and get a free key at [OpenWeatherMap](https://openweathermap.org/api).

### 2. Environment Setup
Clone the repository:
```bash
git clone https://github.com/ynazarets/weather-bot.git
```
Navigate to the project directory:
```bash
cd weather-bot
```
Create a `.env` file in the project root (use `.env.template` as a guide) or configure the variables in `src/main/resources/application.properties`:

```properties
bot.name=YourBotName
bot.token=YourTelegramToken
weather.token=YourOpenWeatherKey