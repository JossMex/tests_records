import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nullParamException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null." , exception.getMessage());
    }
    @Test
    public void emptyListException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty." , exception.getMessage());
    }

    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++){
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horses, hippodrome.getHorses());
       assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 50; i++){
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse : horses ) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        Horse horse1 = new Horse("Flash",1,2.999);
        Horse horse2 = new Horse("Ranger", 1, 2);
        Horse horse3 = new Horse("Comanche", 1, 3);
        Horse horse4 = new Horse("Shelby", 1,1.5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3,horse4));

        assertEquals(horse3, hippodrome.getWinner());
    }
}
