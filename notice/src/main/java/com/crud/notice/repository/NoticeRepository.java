package com.crud.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.notice.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> { }
