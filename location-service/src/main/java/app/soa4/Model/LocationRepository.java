package app.soa4.Model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boeing on 4/3/2017.
 */
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Location findByUsername(String username);
}
