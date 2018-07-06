package com.pokemon.app.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.app.dto.BookDto;
import com.pokemon.app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class BookClientRest {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    BookService bookService;

    @RequestMapping("/test")
    public String getBook() throws IOException {

        BookDto book = new BookDto();
        book.setContent(restTemplate.getForObject(
                "http://bnb.data.bl.uk/doc/resource/007446989.json", String.class));
        JsonNode jsonNode = bookService.returnParsedBookContent(book);

        return prettyJsonString(jsonNode);
    }

    public String prettyJsonString(JsonNode jsonNode) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(jsonNode.toString(), Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            return "cannot parse";
        }
    }
}
