package rca.soap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rca.soap.api.bean.Supplier;

@Repository
public interface IItemRepository extends JpaRepository<Supplier, Integer>{

}
