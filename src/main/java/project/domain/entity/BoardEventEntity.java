package project.domain.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board_events")
@Entity
@Getter
//230104 안나 생성
public class BoardEventEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_no", unique = true, nullable = false)
	private long eventNo; //글번호
	
	@JoinColumn(name = "regist_no", nullable = false)
	@ManyToOne
	private EmployeesEntity registNo; //작성자: 사원번호
	
	@Column(nullable = false)
	private String title; //제목
	
	@Column(nullable = false)
	private String content; //내용
	
	@CreationTimestamp
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate; //작성일
	
	@Column(name = "event_date", nullable = false)
	private LocalDateTime eventDate; //경조사일

}
