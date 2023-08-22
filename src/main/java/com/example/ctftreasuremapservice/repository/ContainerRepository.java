package com.example.ctftreasuremapservice.repository;

import com.example.ctftreasuremapservice.model.entity.ContainerEntity;
import com.example.ctftreasuremapservice.model.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface ContainerRepository extends JpaRepository<ContainerEntity, String> {


    @Override
    ContainerEntity save(ContainerEntity containerEntity);


    @Query("select container from ContainerEntity container where container.locationEntity.nameOfLocation=:locationName")
    List<ContainerEntity> getContainerByLocationName(String locationName);

    @Query("select container from ContainerEntity container")
    List<ContainerEntity> getAll();
}
