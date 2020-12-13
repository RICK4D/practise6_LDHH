import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import es.ull.flights.Flight;
import es.ull.passengers.*;

@DisplayName("Probando el proyecto Aiport")
@Nested
class MyTestFlight {
	private Flight myFlight_;
	private Passenger mike_;
	private Passenger james_;

	
	@BeforeEach
    void setUp() {
        myFlight_ = new Flight("BA2490", 75);
        mike_ = new Passenger("1", "Mike", "0:AD");
        james_ = new Passenger("2", "James", "1:AE");
    }
	
	@Nested
    @DisplayName("Probando los métodos de la clase Flight")
    class OnlyFlight { 
		 @DisplayName("Comprobando los getters de la clase")
		 @Test
	     public void testGetters() {
	         assertAll("Deben funcionar los getters de la clase Flight",
	                 () -> assertEquals("BA2490", myFlight_.getFlightNumber()),
	                 () -> assertEquals(75, myFlight_.getSeats()),
	                 () -> assertEquals("^[A-Z]{2}\\d{3,4}$", Flight.getFlightNumberRegex()),
                     () -> assertEquals(0, myFlight_.getPassengers().size())
	         );
	     }
		 
		 @DisplayName("Comprobando que se pueden añadir varios pasajeros")
		 @Test
         public void testAddingFewPassengers() {
                 myFlight_.addPassenger(james_);
                 myFlight_.addPassenger(mike_);
                 assertAll("Los pasajeros han de estar contenidos y que haya cambiado el tamaño de los pasajeros a bordo",
                         () -> assertTrue(myFlight_.getPassengers().contains(james_)),
                         () -> assertTrue(myFlight_.getPassengers().contains(mike_)),
                         () -> assertEquals(2, myFlight_.getPassengers().size())
                 );
         }
		 
		 @DisplayName("Comprobando que se pueden eliminar varios pasajeros")
		 @Test
         public void testRemovingFewPassengers() {
                 myFlight_.removePassenger(james_);
                 myFlight_.removePassenger(mike_);
                 assertAll("Los pasajeros han de estar contenidos y que haya cambiado el tamaño de los pasajeros a bordo",
                         () -> assertFalse(myFlight_.getPassengers().contains(james_)),
                         () -> assertFalse(myFlight_.getPassengers().contains(mike_)),
                         () -> assertEquals(0, myFlight_.getPassengers().size())
                 );
         }
	}
	
	@Nested
    @DisplayName("Probando los métodos de la clase Passenger")
	class OnlyPassenger {
		
		 @DisplayName("Comprobando los getters de la clase")
		 @Test
	     public void testGetters() {
	         assertAll("Deben funcionar los getters de la clase Passenger",
	                 () -> assertEquals("1", mike_.getIdentifier()),
	                 () -> assertEquals("Mike", mike_.getName()),
	                 () -> assertEquals("0:AD", mike_.getCountryCode()),
	                 () -> assertEquals("2", james_.getIdentifier()),
	                 () -> assertEquals("James", james_.getName()),
	                 () -> assertEquals("1:AE", james_.getCountryCode())
	         );
	     }
		 
		 @DisplayName("Comprobando que se pueden unir pasajeros a un vuelo")
		 @Test
         public void testAddingFewPassengers() {
			 mike_.joinFlight(myFlight_);
             james_.joinFlight(myFlight_);
             
             assertAll("Los pasajeros han de estar contenidos y que haya cambiado el tamaño de los pasajeros a bordo",
                     () -> assertEquals("BA2490", mike_.getFlight().getFlightNumber()),
            		 () -> assertTrue(mike_.getFlight().getPassengers().contains(james_)),
                     () -> assertTrue(james_.getFlight().getPassengers().contains(mike_)),
                     () -> assertEquals(2, myFlight_.getPassengers().size())
             );
         }
		 
		 @DisplayName("Comprobando que se pueden eliminar pasajeros de un vuelo")
		 @Test
         public void testRemovingFewPassengers() {
                 myFlight_.removePassenger(james_);
                 myFlight_.removePassenger(mike_);
                 assertAll("Los pasajeros han de estar contenidos y que haya cambiado el tamaño de los pasajeros a bordo",
                         () -> assertFalse(myFlight_.getPassengers().contains(james_)),
                         () -> assertFalse(myFlight_.getPassengers().contains(mike_)),
                         () -> assertEquals(0, myFlight_.getPassengers().size())
                 );
         }
		 
		 @DisplayName("Comprobando que el método toString funciona para varios pasajeros")
		 @Test
         public void testToString() {
             
             assertAll("Los pasajeros han de estar contenidos y que haya cambiado el tamaño de los pasajeros a bordo",
                     () -> assertEquals("Passenger Mike with identifier: 1 from 0:AD", mike_.toString())
             );
         }
		
	}
	
	


}
