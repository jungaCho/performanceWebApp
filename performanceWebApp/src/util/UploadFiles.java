package util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import domain.performance.DetailFileVO;
import domain.performance.PosterVO;

public class UploadFiles {

	// ����ڰ� ������ ���ε������� �� ������ �����ϴ� Ŭ����

//	private static String path = "C:" + File.separator + "upload"; // C:/upload ���
	
	private static int count = 1;

	// ����ڰ� ������ ���ε������� �� ��û�� ó���� c:upload�� ������ ���� �޼ҵ�

	public static PosterVO uploadPosterFile(Part part, ServletContext sc)  throws IOException {

		String originalFileName = getOriginalFileName(part);
		String systemFileName = "";
		
		String path=sc.getRealPath("/upload");
		System.out.println("path : "+path);
		File file = new File(path + File.separator + originalFileName);

		if (file.exists()) { // ��������üũ - ������ �����̸��� ������ ��, ������ ����

			// ���������̸��� �ִ� ��� - _1,_2���� �ٿ� �̸��� �ٿ��ش�

			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + count
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			count++; // ���� �̸��� ��� _1, _2������ �Ϸù�ȣ�� �־ �������ش�
		} else {

			// ������ ���� ��쿡�� ������ systemFileName�� originalFileName�� ���� �־��ش�
			systemFileName = originalFileName;
		}

		// �޸𸮶Ǵ� c:/tempUpload�� �����ϴ� ���۵� ������ c:upload�� systemFileName���� ���ش�(��� ��������
		// write�� ���������Ҽ��ִ�)

		part.write(path + File.separator + systemFileName);

		// ���ε带 �����Ϸ��ϸ�(�� ������ ����������) ��� ������ ������ϱ�, �� ���� write()�� ��ũ�� ������ϴ°��̴�.

		// �������ϵ��� ArticleFileVO ��ü�� ������ �����Ų��
		PosterVO articleFile = new PosterVO();
		articleFile.setOriginalFileName(originalFileName); // ���ϸ�� �� set���� �־��ְ�
		articleFile.setSystemFileName(systemFileName);
		articleFile.setFileSize(part.getSize()); // ������ ����� �־��ش�

		return articleFile; // �׸��� articleFileŸ�� ���۷����� ��ȯ��Ų��.
	}

	public static DetailFileVO uploadDetailFile(Part part, ServletContext context) throws IOException {

		String originalFileName = getOriginalFileName(part);
		String systemFileName = "";
		
		String path=context.getRealPath("/upload");
		System.out.println("path : "+path);
		File file = new File(path + File.separator + originalFileName);

		if (file.exists()) { // ��������üũ - ������ �����̸��� ������ ��, ������ ����

			// ���������̸��� �ִ� ��� - _1,_2���� �ٿ� �̸��� �ٿ��ش�

			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + count
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			count++; // ���� �̸��� ��� _1, _2������ �Ϸù�ȣ�� �־ �������ش�
		} else {

			// ������ ���� ��쿡�� ������ systemFileName�� originalFileName�� ���� �־��ش�
			systemFileName = originalFileName;
		}

		// �޸𸮶Ǵ� c:/tempUpload�� �����ϴ� ���۵� ������ c:upload�� systemFileName���� ���ش�(��� ��������
		// write�� ���������Ҽ��ִ�)

		part.write(path + File.separator + systemFileName);

		// ���ε带 �����Ϸ��ϸ�(�� ������ ����������) ��� ������ ������ϱ�, �� ���� write()�� ��ũ�� ������ϴ°��̴�.

		// �������ϵ��� ArticleFileVO ��ü�� ������ �����Ų��
		DetailFileVO articleFile = new DetailFileVO();
		articleFile.setOriginalFileName(originalFileName); // ���ϸ�� �� set���� �־��ְ�
		articleFile.setSystemFileName(systemFileName);
		articleFile.setFileSize(part.getSize()); // ������ ����� �־��ش�

		return articleFile; // �׸��� articleFileŸ�� ���۷����� ��ȯ��Ų��.
	}
	
	
	
	// ��û����� �ִ� �������� ������ ����ڰ� ������ �����̸��� ���ϴ� �޼ҵ�

	private static String getOriginalFileName(Part part) throws IOException {

		// Content-Disposition�̶�� �Ӽ��̸��� ���ڷ� �־��� ��û��� ������ String�� ������ �����
		String ContentDisposition = part.getHeader("Content-Disposition");
		String[] str = ContentDisposition.split(";");// ���ڿ��� ;��±����ڷ� String�� �����ذ��� string[] �� �־���

		for (String temp : str) {
			if (temp.trim().startsWith("filename")) {// "filename"���� ���۵Ǵ� ���ڿ��� ������ ������ trim()
				return temp.trim().substring(temp.trim().lastIndexOf("=") + 1).replaceAll("\"", "");

				// temp.trim() => filename="Koala.jpg"
				// temp.trim().indexOf("=") -> index:8
				// temp.trim().substring( temp.trim().indexOf("=") + 1) ->index 9���� ���� =>
				// "Koala.jpg"
				// temp.trim().substring( temp.trim().indexOf("=") + 1).replaceAll("\"", "") =>
				// Koala.jpg
				// \" -> �ֵ���ǥ Ư�����ڸ� ����ȭ�ϱ� ���� \ ��ȣ�� �տ� �ٿ��ذ�(//""�� �Ǿ��ִ°��� ��ɷ� ���α����ؼ� \"�� ���ڸ� �־��ش�
				// )

			}
		}
		return null;
	}



	
}