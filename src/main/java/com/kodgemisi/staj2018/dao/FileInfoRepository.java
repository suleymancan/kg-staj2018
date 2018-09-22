package com.kodgemisi.staj2018.dao;

import com.kodgemisi.staj2018.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo,Long> {

}
