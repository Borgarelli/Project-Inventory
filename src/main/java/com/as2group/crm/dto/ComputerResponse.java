package com.as2group.crm.dto;

import java.time.LocalDate;
import java.util.List;

import com.as2group.crm.model.Components;
import com.as2group.crm.model.Employee;

public record ComputerResponse(
Long id,
ComputerStatusResponse status,
String patrimony,
String sn,
Employee employee,
String model,
String brand,
String soCurrent,
String soOriginal,
LocalDate entryDate,
LocalDate departureDate,
LocalDate modificationDate,
List<Components> computerComponents
)
{

}
