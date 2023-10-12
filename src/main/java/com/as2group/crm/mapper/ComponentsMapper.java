package com.as2group.crm.mapper;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.as2group.crm.dto.ComponentsRequest;
import com.as2group.crm.dto.ComponentsResponse;
import com.as2group.crm.model.Components;

@Component
public class ComponentsMapper {
    
    public Components map(ComponentsRequest componentsRequest) {
        Components components = new Components();
        components.setPatrimony(componentsRequest.patrimony());
        components.setSpecifications(componentsRequest.specifications());
        return components;
    }

    public ComponentsResponse map(Components component) {
        return new ComponentsResponse(component.getId(), component.getPatrimony(), component.getSpecifications());
    }

    public List<ComponentsResponse> map(List<Components> components){
        List<ComponentsResponse> response = new ArrayList<>();
        for(Components component : components) {
            response.add(new ComponentsResponse(component.getId(), component.getPatrimony(), component.getSpecifications()));
        }
        return response;
    }
}
