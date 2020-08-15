package org.midas.analytics.cassandraconnectivity.repositories;

import org.midas.analytics.cassandraconnectivity.model.LoginDetails;
import org.springframework.data.repository.CrudRepository;

public interface CassandraRepository extends CrudRepository<LoginDetails, String>{

}
