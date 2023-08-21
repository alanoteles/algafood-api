package com.algaworks.algafoodapi.api.model;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


@JacksonXmlRootElement(localName = "kitchens")
@Data
public class KitchenXmlWrapper {

    @JsonProperty("kitchen")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Kitchen> kitchens ;
}
