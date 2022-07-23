package Model.Tripulante;


import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import fourteam.http.Exception.HttpException;


public class Tripulante_Test {

    @Before
    public void setup() {
       
    }

    @Test
    public void HandleTripulante_Ok() throws HttpException {

        // creando tripulante
        UUID keyTest = UUID.randomUUID();
        String nombreTest = "Jose";
        String apellidoTest = "Perez";
        String emailAddressTest = "jose@gmail.com";
        Cargo cargoTest = new Cargo( 2500.0, "asistente");


        Tripulante tripulanteTest = new Tripulante();

        tripulanteTest.setNombre(nombreTest);
        tripulanteTest.setApellido(apellidoTest);
        tripulanteTest.setEmailAddress(emailAddressTest);
        tripulanteTest.setCargo(cargoTest);

        Assert.assertEquals(nombreTest, tripulanteTest.getNombre());
        Assert.assertEquals(apellidoTest, tripulanteTest.getApellido());
        Assert.assertEquals(emailAddressTest, tripulanteTest.getEmailAddress());
        Assert.assertEquals(cargoTest, tripulanteTest.getCargo());

        tripulanteTest.eventCreado();


    }
}
