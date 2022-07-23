package UseCases.Command;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Dto.Tripulante.TripulanteDto;
import UsesCases.Command.Tripulante.Crear.CrearTripulanteCommand;
import UsesCases.Command.Tripulante.Crear.CrearTripulanteHandler;
import UsesCases.Command.Tripulante.Editar.EditarTripulanteCommand;
import UsesCases.Command.Tripulante.Editar.EditarTripulanteHandler;
import Event.PersonalRegistrado;
import Factories.ITripulanteFactory;
import Model.Tripulante.Cargo;
import Model.Tripulante.Tripulante;
import Repositories.ITripulanteRepository;
import Repositories.IUnitOfWork;
import fourteam.http.Exception.HttpException;

public class EditarTripulanteHandler_Test {

    ITripulanteFactory tripulanteFactory = Mockito.mock(ITripulanteFactory.class);
    ITripulanteRepository tripulanteRepository = Mockito.mock(ITripulanteRepository.class);
    IUnitOfWork unitOfWork = Mockito.mock(IUnitOfWork.class);
   

    @Before
    public void setup() {
       
    }

    @Test
    public void HandleEditar_Ok() throws HttpException {

        // creando tripulante
        UUID keyTest = UUID.randomUUID();
        String nombreTest = "Maria";
        String apellidoTest = "Suarez";
        String emailAddressTest = "maria@gmail.com";
        Cargo cargoTest = new Cargo( 2500.0, "piloto");

        TripulanteDto tripulanteDtoTest = new TripulanteDto();
        tripulanteDtoTest.setKey(UUID.randomUUID());
        tripulanteDtoTest.setNombre(nombreTest);
        tripulanteDtoTest.setApellido(apellidoTest);
        tripulanteDtoTest.setEmailAddress(emailAddressTest);
        tripulanteDtoTest.setCargo(cargoTest);

        Tripulante tripulanteTest = new Tripulante(nombreTest, apellidoTest, emailAddressTest, cargoTest);
        
        CrearTripulanteCommand command = new CrearTripulanteCommand(tripulanteDtoTest);
        when(tripulanteFactory.Create(anyString(),anyString(),anyString(),any(Cargo.class))).thenReturn(tripulanteTest);
        CrearTripulanteHandler handler = new CrearTripulanteHandler(tripulanteFactory, tripulanteRepository,
        unitOfWork);

        Tripulante respuesta = handler.handle(command);

        verify(tripulanteRepository).Create(respuesta);
        verify(unitOfWork).commit();

        Assert.assertEquals(PersonalRegistrado.class,
                respuesta.domainEvents.get(0).getClass());
        Assert.assertEquals(nombreTest, respuesta.getNombre());
        Assert.assertEquals(apellidoTest, respuesta.getApellido());
        Assert.assertEquals(emailAddressTest, respuesta.getEmailAddress());
        Assert.assertEquals(cargoTest, respuesta.getCargo());

        //EDITAR_TRIPULANTE_HANDLER_TEST
        when(tripulanteRepository.FindByKey(any())).thenReturn(tripulanteTest);

        EditarTripulanteHandler handlerEdit = new EditarTripulanteHandler(tripulanteFactory, tripulanteRepository, unitOfWork);

        EditarTripulanteCommand commandEdit= new EditarTripulanteCommand(tripulanteDtoTest.getKey());
        // when(tripulanteFactory.Create(anyString(),anyString(),anyString(),any(Cargo.class))).thenReturn(tripulanteTest);
        commandEdit.tripulanteDto.Key = tripulanteDtoTest.getKey();
        Tripulante respuestaEdit = handlerEdit.handle(commandEdit);
        Assert.assertNotNull(respuestaEdit);

    }
}
