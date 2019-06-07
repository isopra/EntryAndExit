package jp.co.isopra.entryandexit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.isopra.entryandexit.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	public Optional<Location> findById(int name);
	@Query("SELECT l FROM Location l ORDER BY l.id")
	Iterable<Location> findAllOrderById();
}
