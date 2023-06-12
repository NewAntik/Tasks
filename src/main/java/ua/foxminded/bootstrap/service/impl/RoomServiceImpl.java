package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.bootstrap.dao.RoomRepository;
import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.service.RoomService;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRep;
    
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRep = roomRepository;
    }
    
    @Override
    public List<Room> saveAll(List<Room> rooms) throws SQLException {
        return roomRep.saveAll(rooms);
    }

    @Override
    public Room add(String name) throws SQLException {
        return roomRep.save(new Room(name));
    }

    @Override
    public void delete(Long id) throws SQLException {
        roomRep.deleteById(id);
    }

    @Override
    public List<Room> findAll() throws SQLException {
        return roomRep.findAll();
    }
}
