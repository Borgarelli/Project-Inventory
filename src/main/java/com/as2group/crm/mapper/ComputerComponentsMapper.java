package com.as2group.crm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.as2group.crm.dto.ComputerComponentsRequest;
import com.as2group.crm.dto.ComputerComponentsResponse;
import com.as2group.crm.dto.ComputerResponse;
import com.as2group.crm.model.ComputerComponent;

public class ComputerComponentsMapper {

    @Autowired
    ComputerMapper computerMapper;
    
    public ComputerComponent map(ComputerComponentsRequest computerComponentsRequest) {
        ComputerComponent computerComponent = new ComputerComponent();
        computerComponent.setComputer(computerComponentsRequest.computer());
        computerComponent.setComponent(computerComponentsRequest.components());
        return computerComponent;
    }

    public List<ComputerComponentsResponse> map(List<ComputerComponent> computerComponents) {
        List<ComputerComponentsResponse> response = new ArrayList<>();
        for(ComputerComponent computerComponent : computerComponents) {

            ComputerResponse computer = this.computerMapper.map(computerComponent.getComputer());

            response.add(new ComputerComponentsResponse(computer, computerComponent.getReceived(), computerComponent.getReturned()));
        }
        return response;
    }


}
