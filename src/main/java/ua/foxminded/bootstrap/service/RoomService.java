package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Room;

public interface RoomService {

    @Transactional
    List<Room> saveAll(List<Room> rooms) throws SQLException;
    
    @Transactional
    Room add(String name) throws SQLException;
    
    @Transactional
    void delete(Long id) throws SQLException;
    
    @Transactional(readOnly = true) 
    List<Room> findAll() throws SQLException;
}
