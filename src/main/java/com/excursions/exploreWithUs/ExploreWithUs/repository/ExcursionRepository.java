package com.excursions.exploreWithUs.ExploreWithUs.repository;

import com.excursions.exploreWithUs.ExploreWithUs.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
