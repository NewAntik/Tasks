package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.RoomRepository;
import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    
    @Override
    public List<Room> saveAll(List<Room> rooms) throws SQLException {
        return roomRepository.saveAll(rooms);
    }

    @Override
    public Room add(String name) throws SQLException {
        return roomRepository.save(new Room(name));
    }

    @Override
    public void delete(Long id) throws SQLException {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> findAll() throws SQLException {
        return roomRepository.findAll();
    }
}
