package com.maoz.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.maoz.dashboard.entity.Path;

@Transactional
public interface PathRepository extends PagingAndSortingRepository<Path, Long>{

}
