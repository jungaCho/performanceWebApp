package domain.performance;

import java.util.List;

public class PerformanceVO {
	private String pNo;	//������ȣ
	private String title; //������
	private String video;//������
	private String startDate; //������
	private String endDate;//������
	private String production; //���ۻ�
	private String contackName; //���� �����
	private String contactNumber; //���� ����� ����ó
	private int runningTime; //����Ÿ��
	private String note; //���
	private int price; //����
	private String viewNo;//������޹�ȣ 
	private String viewClass;//�������
	private String genreNo;//�帣��ȣ
	private String genre;//�帣
	private String tName;//������ �̸�
	private List<ScheduleVO> schedules;//���� ���� ���
	private List<PosterVO> posters;//������ ���� ���
	private List<DetailFileVO> detaileFiles;//�󼼼��� �̹��� ���
	
	
}
