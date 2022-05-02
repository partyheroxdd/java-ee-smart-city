package kz.javaee.project.madiyevmirasitis1908.Aop;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.Produces;
import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerProduce {
    Logger logger;
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        String lConf = "D:\\javaProjects\\PorjectMiras\\src\\main\\java\\kz\\javaee\\project\\madiyevmirasitis1908\\Aop\\log.config";
        try (FileInputStream ins = new FileInputStream(lConf)) {
            LogManager.getLogManager().readConfiguration(ins);
            logger =  Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return  logger;
    }

}
