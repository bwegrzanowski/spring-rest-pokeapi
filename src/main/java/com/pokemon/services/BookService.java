package com.pokemon.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.BookDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookService {

    public JsonNode returnParsedBookContent(BookDto content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(content.getContent());
        JsonNode actualObj = mapper.readTree(parser);
        return actualObj;
    }

}
