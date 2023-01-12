package project.service.proc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import project.domain.entity.EmployeesEntity;
import project.service.CalendarService;
import project.service.EmployeesService;

@Service
public class CalendarServiceProc implements CalendarService {

	/* 20230111 문대현 생성 */
	
	@Autowired
	EmployeesService employeesService;

	//캘린더에 출력할 이벤트 데이터를 처리하는 기능
	@Override
	public String findCalendarEventData() {
		JSONArray jsonArr = new JSONArray();

		//사원 정보중 생일 날짜를 조회해서 ajax로 전송해 주는 기능
		List<EmployeesEntity> list = employeesService.findAll();

		for (int i = 0; i < list.size(); i++) {
			LocalDate now = LocalDate.now();
			int year = now.getYear();
			HashMap<String, Object> hash = new HashMap<>();
			JSONObject jsonObj = new JSONObject();
			
			String date = DateTimeFormatter.ofPattern("MM-dd").format(list.get(i).getBirthDate());
			hash.put("start", year + "-" + date);
			hash.put("title", list.get(i).getName() + " 생일날");

			jsonObj = new JSONObject(hash);
			jsonArr.put(jsonObj);
		}
		
		//사원 휴가 정보 조회해서 ajax로 전송해 주는 기능 넣을 곳
		
		
		String result = jsonArr.toString();

		return result;
	}

}
