package com.priyakdey.app.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priyakdey.app.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

/**
 * @author Priyak Dey
 */
@Slf4j
public class UserDeserializer implements Deserializer<User> {

    @Override
    public User deserialize(String topic, byte[] data) {
        if (data == null) {
            log.error("Cannot deserialize a null object");
            return null;
        }

        try {
            return new ObjectMapper().readValue(data, User.class);
        } catch (IOException e) {
            log.error("Cannot parse input array to User object");
            throw new SerializationException("Cannot parse input array to User object");
        }
    }

}
