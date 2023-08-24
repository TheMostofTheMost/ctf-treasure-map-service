package com.example.ctftreasuremapservice.manager;

import com.example.ctftreasuremapservice.model.entity.ContainerEntity;
import com.example.ctftreasuremapservice.repository.ContainerRepository;
import com.example.ctftreasuremapservice.repository.LocationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContainerManager {

    private final ContainerRepository containerRepository;
    private final LocationRepository locationRepository;

    public ContainerManager(ContainerRepository containerRepository, LocationRepository locationRepository) {
        this.containerRepository = containerRepository;
        this.locationRepository = locationRepository;
    }

    public void saveContainer(String treasure, String nameOfLocation) {
        String author = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ContainerEntity newContainer = new ContainerEntity(
                treasure,
                author,
                locationRepository.getByName(nameOfLocation));
        containerRepository.save(newContainer);

    }
    public List<ContainerEntity> getContainerByLocationName(String locationName) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("admin")) {
            return containerRepository.getContainerByLocationName(locationName);
        } else {
            return containerRepository.getContainerByLocationName(locationName)
                    .stream()
                    .filter(containerEntity -> !containerEntity.getAuthor().equals("admin"))
                    .collect(Collectors.toList());

        }
    }

}
