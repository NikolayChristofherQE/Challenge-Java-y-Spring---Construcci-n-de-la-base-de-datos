package com.MSAlura.literAluraChallenge1.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();


        @Override
        public <T > T obtenerDatos(String json, Class < T > clase) {

            try {
                return objectMapper.readValue(json, clase);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error al convertir JSON a " + clase.getName() + ": " + e.getMessage(), e);
            }
        }
    }


