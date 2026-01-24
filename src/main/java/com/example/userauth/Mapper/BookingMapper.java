package com.example.userauth.Mapper;

import com.example.userauth.DTOs.BookingDtos.BookingResponseDto;
import com.example.userauth.Models.Booking;
import com.example.userauth.Models.ShowSeat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "bookingId" , source = "id")
    @Mapping(target = "movieTitle" , source = "show.movies.title")
    @Mapping(target = "theatreName" , source = "show.theatre.name")
    @Mapping(target = "status", source = "EStatus")
    @Mapping(target = "seatNumbers" , expression = "java(mapSeats(booking.getSeats()))")
    BookingResponseDto toDto(Booking booking);

    default List<String> mapSeats(Set<ShowSeat> seats) {
        if (seats == null) return null;

        return seats.stream()
                .map(showSeat -> {
                    int rowNo = showSeat.getSeat().getRowNo();
                    int seatNo = showSeat.getSeat().getSeatNo();

                    char rowLetter = (char) (64 + rowNo);
                    return rowLetter + String.format("%02d", seatNo);
                }).toList();
    }

}


