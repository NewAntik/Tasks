package ua.foxminded.bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.foxminded.bootstrap.models.Room;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long> {
    
    @Query("SELECT r FROM Room r WHERE r.name = :name")
    Room findByName(String name);
}
