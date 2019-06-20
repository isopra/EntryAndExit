package jp.co.isopra.entryandexit.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.repositories.LocationRepository;

@Service
@Transactional
public class LocationService {

	@Autowired
	LocationRepository repository;

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Location> getAll(){
		return (List<Location>) entityManager
				.createQuery("from Location order by location_id").getResultList();
	}

	public Location get(int id) {
		return (Location)entityManager
				.createQuery("from Location where id = " + id)
				.getSingleResult();
	}

	public int delete(int id) {
			return entityManager
					.createQuery("delete from Location where id = " + id)
					.executeUpdate();
		}

	public Location registerLocation(Location entity) {

		entity = repository.save(entity);

		return entity;
	}
}
