package com.example.userauth.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public class BookingMapper {

    @Mapping(target = "id" , ignore = true)
}
