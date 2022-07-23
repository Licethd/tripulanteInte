
import Repositories.*;
import fourteam.extensions.IServiceCollection;
import Context.IWriteDbContext;
import Context.MongoDB.WriteDbContext;
import Repository.*;

public class Infraestructure {
    public static void AddInfraestructure() {
        
        IServiceCollection.AddMediator();

        //IServiceCollection.AddSingleton(MemoryDatabase.class);
        IServiceCollection.AddScoped(IWriteDbContext.class, WriteDbContext.class);
        IServiceCollection.AddScoped(IUnitOfWork.class, UnitOfWork.class);
        //IServiceCollection.AddScoped(ITripulanteRepository.class, MemoryTripulanteRepository.class);
        IServiceCollection.AddScoped(ITripulanteRepository.class, TripulanteRepository.class);
        Application.AddApplication();
    }
}
