package ru.vkarikov.jsense.repository.search;

import ru.vkarikov.jsense.domain.Sensor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Sensor entity.
 */
public interface SensorSearchRepository extends ElasticsearchRepository<Sensor, Long> {
}
