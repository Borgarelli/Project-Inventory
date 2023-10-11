package com.as2group.crm.dto;

import java.time.LocalDate;
import java.util.List;

import com.as2group.crm.enumeration.ComputerStatus;


public record ComputerResponse(
Long id,
ComputerStatus status,
String patrimony,
String sn,
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
