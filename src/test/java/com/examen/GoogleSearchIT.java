package com.examen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de integración con Selenium.
 * Usa el sitio "The Internet" (herokuapp), que es estable y pensado para automatización.
 */
class GoogleSearchIT {   // Puedes renombrarlo a LoginIT si quieres

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Debe realizar login correctamente en The Internet")
    void testLoginExitoso() {
        // 1. Abrir la página de login
        driver.get("https://the-internet.herokuapp.com/login");

        // 2. Escribir usuario y contraseña
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");

        // 3. Hacer clic en el botón Login
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 4. Verificar que el login fue exitoso
        WebElement mensajeExito = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("flash"))
        );

        String texto = mensajeExito.getText();
        assertTrue(texto.contains("You logged into a secure area!"),
                "Debería aparecer el mensaje de login exitoso");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}