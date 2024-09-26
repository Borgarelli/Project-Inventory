package com.as2group.crm.dto;

import java.time.LocalDate;
import java.util.List;

import com.as2group.crm.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;


public record ComputerResponse(
Long id,
Status status,
String patrimony,
String sn,
@JsonInclude(JsonInclude.Include.NON_NULL)
EmployeeResponse employee,
String model,
String brand,
String soCurrent,
String soOriginal,
LocalDate entryDate,
LocalDate departureDate,
LocalDate modificationDate,
List<ComponentsResponse> computerComponents
)
{


}
