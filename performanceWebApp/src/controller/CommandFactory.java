package controller;

import java.util.HashMap;

public class CommandFactory {
	//�̱��� ����
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	//�˻��ӵ��� ���� map ���
	
	private CommandFactory() {
		//Ŭ���̾�Ʈ ��û�� ó���ϱ� ����, Ŭ���̾�Ʈ url�� ��û�� ó���� command Ŭ���� ����

		//ȸ��
		map.put("/loginForm.do", "controller.member.LoginFormCommand");
		
		map.put("/login.do","controller.member.LoginCommand");
		
		map.put("/SignUp.do","controller.member.SignUpCommand");
		
		map.put("/signUpForm.do","controller.member.SignUpFormCommand");
		
		map.put("/checkMember.do","controller.member.CheckMemberCommand");
		
		map.put("/checkMemberForm.do", "controller.member.CheckMemberFormCommand");
		
		map.put("/myPageForm.do", "controller.member.MyPageFormCommand");
		
		map.put("/modifyForm.do", "controller.member.ModifyMemberFormCommand");
		
		map.put("/modifyMember.do", "controller.member.ModifyMemberCommand");
		
		map.put("/modifyPwd.do", "controller.member.ModifyPwdFormCommand");
		
		map.put("/retrieveMember.do", "controller.member.RetrieveMemberCommand");

		map.put("/findId.do", "controller.member.FindIdCommand");
		
		map.put("/findIdForm.do", "controller.member.FindIdFormCommand");
		
		map.put("/withdrawalForm.do", "controller.member.WithdrawalFormCommand");
		
		map.put("/withdrawal.do", "controller.member.WithdrawalCommand");
	
		map.put("/adminLogin.do", "controller.member.AdminLoginCommand");

		map.put("/findPwd.do", "controller.member.FindPwdCommand");
		
		map.put("/findPwdForm.do", "controller.member.FindPwdFormCommand");

		map.put("/logout.do", "controller.member.LogoutCommand");
		
		map.put("/processMemberList.do", "controller.member.AdminSelectMember");
		
		map.put("/IdOverlapCheck.do", "controller.member.IdOverlapCommand");
		
		map.put("/SendEmail.do", "controller.member.SendEmailCommand");

		
		//����
		map.put("/member_r_reservationStart.do","controller.reservation.ReservationStartCommand");

		map.put("/member_r_reservationStart2.do","controller.reservation.ReservationSeatCommand");
		
		map.put("/member_r_reservationStart3.do","controller.reservation.ListCardCommand");
		
		map.put("/order.do","controller.reservation.OrderCommand");
		
		map.put("/member_r_layout.do","controller.reservation.ListReservationCommand");
		

		
		//����
		map.put("/admin_p_selectPerformanceList.do","controller.performance.ListPerformanceByAdminCommand");
		
		map.put("/admin_p_detailPerformance.do","controller.performance.DetailPerformanceCommand");	
		
		map.put("/admin_p_removePerformance.do", "controller.performance.RemovePerformanceCommand");
		
		map.put("/admin_p_modifyPerformanceForm.do", "controller.performance.ModifyPerformanceFormCommand");
		
		map.put("/admin_p_findPerformance.do", "controller.performance.FindPerformanceCommand");
		
		map.put("/admin_p_removePoster.do","controller.performance.RemovePosterCommand" );
		
		map.put("/admin_p_removeDetailFile.do","controller.performance.RemoveDetailFileCommand" );
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
