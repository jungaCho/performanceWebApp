package controller;

import java.util.HashMap;

public class CommandFactory {
	//�̱��� ����
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	//�˻��ӵ��� ���� map ���
	
	private CommandFactory() {
		//Ŭ���̾�Ʈ ��û�� ó���ϱ� ����, Ŭ���̾�Ʈ url�� ��û�� ó���� command Ŭ���� ����

		map.put("/loginForm.do","controller.member.LoginFormCommand");

		map.put("/loginForm.do","controller.member.loginFormCommand");

		map.put("/SignUp.do","controller.member.SignUpCommand");

		map.put("/modifyForm.do", "controller.member.ModifyMemberFormCommand");

		map.put("/modifyForm.do", "controller.member.ModifyMemberFormCommand");
		
		//����
		map.put("/member_r_reservationStart.do","controller.reservation.ReservationStartCommand");
		map.put("/member_r_reservationStart2.do","controller.reservation.ReservationSeatCommand");
		
	}
	
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public Command createCommand(String uri) { //��û�� uri�� command Ŭ���� ����
		String commandClass = map.get(uri);
		if(commandClass == null) {
			return null;
		}
		
		try {
			Class obj = Class.forName(commandClass); //��û���� uri�� ���� command ��ȯ
			Command command = (Command) obj.newInstance(); // map value�� �ִ� Ÿ���� �ν��Ͻ�������
			return command;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
