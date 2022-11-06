package com.rental.app.rental.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSourceNamingConvention(NamingConventions.NONE)
                .setSourceNameTransformer((name, nameableType) -> name)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setDestinationNameTransformer((name, nameableType) -> name)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

}