package org.example.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.multibindings.Multibinder;
import org.example.dao.PokemonCsvDAOImpl;
import org.example.dao.PokemonDAO;
import org.example.dao.PokemonMySQLDAOImpl;
import org.example.repository.PokemonRepository;
import org.example.router.PokemonHandler;
import org.example.router.PokemonRouterTCP;
import org.example.service.PokemonService;
import org.example.service.PokemonServiceImpl;
import java.io.IOException;
import java.net.ServerSocket;

public class ComponentInjectorConfig extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<PokemonDAO> binderSet = Multibinder.newSetBinder(binder(), PokemonDAO.class);
        binderSet.addBinding().to(PokemonCsvDAOImpl.class);
        binderSet.addBinding().to(PokemonMySQLDAOImpl.class);

        bind(PokemonRepository.class);
        bind(PokemonService.class).to(PokemonServiceImpl.class);
        bind(ObjectMapper.class);
        bind(PokemonRouterTCP.class);
        bind(ServerSocket.class).toProvider(ServerSocketProvider.class);
        bind(PokemonHandler.class);
    }

    static class ServerSocketProvider implements Provider<ServerSocket> {
        private final PropertiesReader propertiesReader;

        @Inject
        public ServerSocketProvider(PropertiesReader propertiesReader) {
            this.propertiesReader = propertiesReader;
        }

        @Override
        public ServerSocket get() {
            int port = Integer.parseInt(propertiesReader.getProperty("application.port"));
            try {
//                log.info(GREEN + BOLD + "Application started on port " + port + RESET);
                return new ServerSocket(port);
            } catch (IOException exception) {
                throw new RuntimeException("Error creating socket: " + exception.getMessage(), exception);
            }
        }
    }
}
