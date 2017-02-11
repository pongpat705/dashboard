package com.maoz.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maoz.dashboard.entity.Stations;

@Transactional
public interface StationRepository extends PagingAndSortingRepository<Stations, Long>{

}
