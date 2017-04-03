package com.water.repository;

import com.water.domain.Archive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 周美华 on 2017/3/30.
 */
public interface ArchiveRepository extends JpaRepository<Archive, Integer> {

    Page<Archive> findByHallId(Integer hallId, Pageable pageable);
}
