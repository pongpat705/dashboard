package com.maoz.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maoz.dashboard.entity.Station;

@Transactional
public interface StationRepository extends PagingAndSortingRepository<Station, Long>{

}
