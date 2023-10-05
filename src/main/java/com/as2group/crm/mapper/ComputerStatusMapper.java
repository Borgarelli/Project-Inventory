package com.as2group.crm.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.as2group.crm.dto.ComputerStatusResponse;
import com.as2group.crm.enumeration.ComputerStatus;

@Component
public class ComputerStatusMapper {

	public ComputerStatusResponse map(ComputerStatus computerStatus) {
		return ComputerStatusResponse.of(computerStatus);
	}
	
	public List<ComputerStatusResponse> map(List<ComputerStatus> computerStatusList){
		return computerStatusList.stream().map(computerStatus -> this.map(computerStatus)).toList();
	}
}

