package com.Application;

import com.file.IReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * This service sends a message to rabbitMQ
 */
@Service
public class ApplicationImpl implements Application {
    private static final Logger Log = LoggerFactory.getLogger(ApplicationImpl.class);

    @Autowired
    IReader fileReader;

    @Autowired
    AmqpTemplate template;

    @Autowired
    Properties properties;

    private void init() {
        Log.info("initialization");
        fileReader.setPath(properties.getProperty("input.file"));
    }

    /**
     * This method sends a message to the rabbitMQ
     */
    @Override
    public void startup() {
        init();
        Log.info("sending a message");
        template.convertAndSend("queue", fileReader.read());
        Log.info("message sent");
    }

}
