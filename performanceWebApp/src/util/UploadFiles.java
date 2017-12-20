package util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import domain.performance.DetailFileVO;
import domain.performance.PosterVO;

public class UploadFiles {

	// 사용자가 파일을 업로드했을때 그 파일을 관리하는 클래스

//	private static String path = "C:" + File.separator + "upload"; // C:/upload 경로
	
	private static int count = 1;

	// 사용자가 파일을 업로드했을때 그 요청을 처리해 c:upload에 파일을 쓰는 메소드

	public static PosterVO uploadPosterFile(Part part, ServletContext sc)  throws IOException {

		String originalFileName = getOriginalFileName(part);
		String systemFileName = "";
		
		String path=sc.getRealPath("/upload");
		System.out.println("path : "+path);
		File file = new File(path + File.separator + originalFileName);

		if (file.exists()) { // 파일유무체크 - 동일한 파일이름이 있으면 참, 없으면 거짓

			// 동일파일이름이 있는 경우 - _1,_2등을 붙여 이름을 붙여준다

			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + count
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			count++; // 동일 이름일 경우 _1, _2등으로 일련번호를 넣어서 저장해준다
		} else {

			// 파일이 없는 경우에도 무조건 systemFileName을 originalFileName과 같게 넣어준다
			systemFileName = originalFileName;
		}

		// 메모리또는 c:/tempUpload에 존재하는 전송된 파일을 c:upload에 systemFileName으로 써준다(모든 정보들을
		// write로 복사저장할수있다)

		part.write(path + File.separator + systemFileName);

		// 업로드를 성공완료하면(이 구문을 빠져나가면) 모든 정보는 사라지니까, 그 전에 write()로 디스크에 써줘야하는것이다.

		// 써준파일들을 ArticleFileVO 객체에 데이터 저장시킨다
		PosterVO articleFile = new PosterVO();
		articleFile.setOriginalFileName(originalFileName); // 파일명들 다 set으로 넣어주고
		articleFile.setSystemFileName(systemFileName);
		articleFile.setFileSize(part.getSize()); // 파일의 사이즈도 넣어준다

		return articleFile; // 그리고 articleFile타입 레퍼런스를 반환시킨다.
	}

	public static DetailFileVO uploadDetailFile(Part part, ServletContext context) throws IOException {

		String originalFileName = getOriginalFileName(part);
		String systemFileName = "";
		
		String path=context.getRealPath("/upload");
		System.out.println("path : "+path);
		File file = new File(path + File.separator + originalFileName);

		if (file.exists()) { // 파일유무체크 - 동일한 파일이름이 있으면 참, 없으면 거짓

			// 동일파일이름이 있는 경우 - _1,_2등을 붙여 이름을 붙여준다

			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + count
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			count++; // 동일 이름일 경우 _1, _2등으로 일련번호를 넣어서 저장해준다
		} else {

			// 파일이 없는 경우에도 무조건 systemFileName을 originalFileName과 같게 넣어준다
			systemFileName = originalFileName;
		}

		// 메모리또는 c:/tempUpload에 존재하는 전송된 파일을 c:upload에 systemFileName으로 써준다(모든 정보들을
		// write로 복사저장할수있다)

		part.write(path + File.separator + systemFileName);

		// 업로드를 성공완료하면(이 구문을 빠져나가면) 모든 정보는 사라지니까, 그 전에 write()로 디스크에 써줘야하는것이다.

		// 써준파일들을 ArticleFileVO 객체에 데이터 저장시킨다
		DetailFileVO articleFile = new DetailFileVO();
		articleFile.setOriginalFileName(originalFileName); // 파일명들 다 set으로 넣어주고
		articleFile.setSystemFileName(systemFileName);
		articleFile.setFileSize(part.getSize()); // 파일의 사이즈도 넣어준다

		return articleFile; // 그리고 articleFile타입 레퍼런스를 반환시킨다.
	}
	
	
	
	// 요청헤더에 있는 정보들을 가져와 사용자가 지정한 파일이름을 구하는 메소드

	private static String getOriginalFileName(Part part) throws IOException {

		// Content-Disposition이라는 속성이름을 인자로 넣어줘 요청헤더 정보를 String형 변수에 담아줌
		String ContentDisposition = part.getHeader("Content-Disposition");
		String[] str = ContentDisposition.split(";");// 문자열중 ;라는구분자로 String을 나눠준것을 string[] 에 넣어줌

		for (String temp : str) {
			if (temp.trim().startsWith("filename")) {// "filename"으로 시작되는 문자열의 공백을 제거함 trim()
				return temp.trim().substring(temp.trim().lastIndexOf("=") + 1).replaceAll("\"", "");

				// temp.trim() => filename="Koala.jpg"
				// temp.trim().indexOf("=") -> index:8
				// temp.trim().substring( temp.trim().indexOf("=") + 1) ->index 9부터 추출 =>
				// "Koala.jpg"
				// temp.trim().substring( temp.trim().indexOf("=") + 1).replaceAll("\"", "") =>
				// Koala.jpg
				// \" -> 쌍따옴표 특수문자를 문자화하기 위해 \ 기호를 앞에 붙여준거(//""로 되어있는것을 빈걸로 냅두기위해서 \"로 인자를 넣어준다
				// )

			}
		}
		return null;
	}



	
}