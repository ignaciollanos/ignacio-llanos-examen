package com.examen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la clase Calculadora.
 * Estas pruebas verifican el comportamiento de cada método de forma aislada.
 */
class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        // Se ejecuta antes de cada prueba
        calculadora = new Calculadora();
    }

    @Test
    @DisplayName("Debe sumar dos números correctamente")
    void testSumar() {
        assertEquals(8, calculadora.sumar(3, 5));
        assertEquals(0, calculadora.sumar(-2, 2));
        assertEquals(-7, calculadora.sumar(-3, -4));
    }

    @Test
    @DisplayName("Debe restar dos números correctamente")
    void testRestar() {
        assertEquals(2, calculadora.restar(5, 3));
        assertEquals(-5, calculadora.restar(0, 5));
    }

    @Test
    @DisplayName("Debe multiplicar dos números correctamente")
    void testMultiplicar() {
        assertEquals(15, calculadora.multiplicar(3, 5));
        assertEquals(0, calculadora.multiplicar(7, 0));
    }

    @Test
    @DisplayName("Debe dividir dos números correctamente")
    void testDividir() {
        assertEquals(2.5, calculadora.dividir(5, 2), 0.001);
        assertEquals(3.0, calculadora.dividir(9, 3), 0.001);
    }

    @Test
    @DisplayName("Debe lanzar excepción al dividir por cero")
    void testDividirPorCero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.dividir(10, 0);
        });
        assertEquals("No se puede dividir por cero", exception.getMessage());
    }
}
