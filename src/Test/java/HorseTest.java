import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void nullNameException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t\t", "\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name ,1,1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void negativeSpeedException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("test-name",-3,1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void negativeDistanceException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("test-name",2,-3));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName(){
        String horsename = "Flash";
        Horse horse = new Horse(horsename, 11,2);

        assertEquals(horsename, horse.getName());

    }

    @Test
    public void getSpeed(){
        double expectedSpeed = 50;
        Horse horse = new Horse("Flash", expectedSpeed, 1);

        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void getDistance(){
        double expectedDistance = 10;
        Horse horse = new Horse("Flash", 10, expectedDistance);

        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    public void zeroDistanceByDefault(){
        Horse horse = new Horse("Flash", 10);
        double distance = horse.getDistance();
        assertEquals(0, distance, 1);
    }

    @Test
    public void testGetRandomDoubleMethodCalledInMoveMethod(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse thunder = new Horse("Thunder", 30,111);
            thunder.move();

            mockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    public void move(double random) {
        // Arrange: Configurar la velocidad, la distancia inicial y la distancia esperada
        double speed = 31;
        double initialDistance = 281;
        double expectedDistance = initialDistance + speed * random;

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Flash", speed, initialDistance);

            mockedStatic.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(random);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}
