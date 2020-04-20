package by.jwd.lemesheuski.xml_parser.service;

import by.jwd.lemesheuski.xml_parser.service.impl.FileServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return instance;
    }

    private final FileService fileService = new FileServiceImpl();

    public FileService getFileService(){
        return fileService;
    }
}
