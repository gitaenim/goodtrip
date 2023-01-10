package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import project.domain.entity.EmployeesEntity;
import project.domain.repository.EmployeesEntityRepository;
import project.service.loginInfoService;

@Service
public class loginInfoServiceProc implements loginInfoService {

	@Autowired
	EmployeesEntityRepository employeesRepository;
	
	@Override
	public void findByID(long info, ModelAndView modelAndView) {
		
		EmployeesEntity emp = employeesRepository.findById(info).orElseThrow();
		 
		 modelAndView.addObject("emp", emp);
	}

}
