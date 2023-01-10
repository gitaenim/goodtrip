package project.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class MyFileUtils {
	/* 230106 한아 작성 */
	
	//upload 폴더로 이동
	public static void moveUploadLocationFromTemp(String newName, String url) {
		ClassPathResource cpr=new ClassPathResource("static"+url+"temp/");
		
			try {
				File file = new File(cpr.getFile(), newName);
				file.renameTo(new File(cpr.getFile().getParent(), newName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}
	

	//temp 폴더에 파일 업로드
	public static Map<String, String> fileUpload(MultipartFile gimg, String location) {
		
		ClassPathResource cpr = new ClassPathResource("static"+location); 
		//이 위치에 파일을 업로드 할거예요
		File folder=null;
		String fileName=null;
		String oldName=null;
		try {
			folder = cpr.getFile();
			oldName=gimg.getOriginalFilename();
			
			int idx = oldName.lastIndexOf("."); //인덱스번호찾기 - 파일 이름 중에서 마지막(.)의 인덱스번호
			String extension = oldName.substring(idx); // .확장자
			fileName=oldName.substring(0, idx)
					+"_"+(System.nanoTime()/1000000)
					+extension;
			//fileName = UUID.randomUUID().toString()+extension; //파일이름 랜덤으로 생성
			
			//원본.이름_3515315315.jpg
			gimg.transferTo(new File(folder, fileName)); //업로드
			System.out.println("임시폴더에 파일업로드 : "+location+fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, String> tempfile = new HashMap<>();
		//tempfile.put("location", location);
		tempfile.put("newName", fileName);
		tempfile.put("oldName", oldName);
		tempfile.put("url", location+fileName);
		//System.out.println(">>>>>>>>>>>>>>"+tempfile);
		return tempfile;
	}
	
	private MyFileUtils() {} //생성자 막아버리기

}
