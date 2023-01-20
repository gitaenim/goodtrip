package project.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.EmployeesEntity;

@Repository
public interface EmployeesEntityRepository extends JpaRepository<EmployeesEntity, Long>{


	EmployeesEntity findByEmail(String username);

	EmployeesEntity findAllByNo(long employeeNo);

	List<EmployeesEntity> findAllByDepartmentNoDepartmentNoAndDeleteStatusOrderBySalaryDesc(Long department, boolean b); //한아 작성

	List<EmployeesEntity> findAllByDeleteStatus(boolean b); //한아 작성

	List<EmployeesEntity> findAllByDeleteStatusOrderByPositionRank(boolean b); //한아 작성

	Object findAllByDepartmentNoDepartmentNo(Long department);

	List<EmployeesEntity> findAllByDeleteStatusOrderByDepartmentNoDesc(boolean b);

	Page<EmployeesEntity> findAllByDepartmentNoDepartmentNoAndDeleteStatus(Long department, boolean b,
			Pageable pageable);

	Page<EmployeesEntity> findAllByDeleteStatusAndNameContaining(boolean b, String keyword, Pageable pageable);

	Page<EmployeesEntity> findAllByDeleteStatus(boolean b, Pageable pageable);



}

