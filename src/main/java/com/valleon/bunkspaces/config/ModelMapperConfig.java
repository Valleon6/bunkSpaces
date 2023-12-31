package com.valleon.bunkspaces.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

@Getter
public class ModelMapperConfig {

    private static final ModelMapper mapper = new ModelMapper();

    public static ModelMapper getMapper(){
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return mapper;
    }
}
