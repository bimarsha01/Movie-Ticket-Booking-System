package com.example.userauth.Repo;

import com.example.userauth.Models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ShowSeatRepo extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShow_Id(Long showId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select ss from ShowSeat ss where ss.id in :ids")
    Set<ShowSeat> findAllByIdWithLock(@Param("ids") List<Long> ids);

    List<ShowSeat> findByBooking_Id(Long bookingId);
}
