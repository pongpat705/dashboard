package com.krungsri.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.krungsri.dashboard.entity.Stations;

@Transactional
public interface StationRepository extends PagingAndSortingRepository<Stations, Long>{

}
