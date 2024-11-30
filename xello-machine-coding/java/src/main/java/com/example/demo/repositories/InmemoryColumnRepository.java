package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.Column;

public class InmemoryColumnRepository implements ColumnRepository{
    private Map<Long, Column> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Column save(Column entity) {
        Column column = new Column(idCounter.getAndIncrement(), entity);
        storage.putIfAbsent(column.getId(), column);
        return column;
    }

    @Override
    public Optional<Column> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Column> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
