package ru.vkarikov.jsense.service.impl;

import ru.vkarikov.jsense.service.SensorService;
import ru.vkarikov.jsense.domain.Sensor;
import ru.vkarikov.jsense.repository.SensorRepository;
import ru.vkarikov.jsense.repository.search.SensorSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Sensor.
 */
@Service
@Transactional
public class SensorServiceImpl implements SensorService{

    private final Logger log = LoggerFactory.getLogger(SensorServiceImpl.class);

    private final SensorRepository sensorRepository;

    private final SensorSearchRepository sensorSearchRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, SensorSearchRepository sensorSearchRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorSearchRepository = sensorSearchRepository;
    }

    /**
     * Save a sensor.
     *
     * @param sensor the entity to save
     * @return the persisted entity
     */
    @Override
    public Sensor save(Sensor sensor) {
        log.debug("Request to save Sensor : {}", sensor);
        Sensor result = sensorRepository.save(sensor);
        sensorSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the sensors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Sensor> findAll(Pageable pageable) {
        log.debug("Request to get all Sensors");
        return sensorRepository.findAll(pageable);
    }

    /**
     * Get one sensor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Sensor findOne(Long id) {
        log.debug("Request to get Sensor : {}", id);
        return sensorRepository.findOne(id);
    }

    /**
     * Delete the sensor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sensor : {}", id);
        sensorRepository.delete(id);
        sensorSearchRepository.delete(id);
    }

    /**
     * Search for the sensor corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Sensor> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Sensors for query {}", query);
        Page<Sensor> result = sensorSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
