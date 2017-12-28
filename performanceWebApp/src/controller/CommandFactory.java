package controller;

import java.util.HashMap;

public class CommandFactory {
	//싱글턴 패턴
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	//검색속도가 빠른 map 사용
	
	private CommandFactory() {
		//클라이언트 요청을 처리하기 위해, 클라이언트 url과 요청을 처리할 command 클래스 매핑
		//회원
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
		
		map.put("/retrieveMemberDetail.do", "controller.member.retrieveMemberDetailCommand");

		map.put("/findId.do", "controller.member.FindIdCommand");
		
		map.put("/findIdForm.do", "controller.member.FindIdFormCommand");
		
		map.put("/findIdRetrieve.do", "controller.member.FindIdRetrieveCommand");
		
		map.put("/withdrawalForm.do", "controller.member.WithdrawalFormCommand");
		
		map.put("/withdrawal.do", "controller.member.WithdrawalCommand");
	
		map.put("/adminLogin.do", "controller.member.AdminLoginCommand");

		map.put("/findPwd.do", "controller.member.FindPwdCommand");
		
		map.put("/findPwdForm.do", "controller.member.FindPwdFormCommand");

		map.put("/logout.do", "controller.member.LogoutCommand");
		
		map.put("/processMemberList.do", "controller.member.AdminSelectMember");
		
		map.put("/IdOverlapCheck.do", "controller.member.IdOverlapCommand");
		
		map.put("/sendEmail.do", "controller.member.SendEmailCommand");
		
		map.put("/authEmail.do", "controller.member.AuthEmailProcessCommand");
			
		map.put("/authNumberForm.do", "controller.member.AuthNumberFormCommand");
		
		map.put("/sendTempPwd.do", "controller.member.sendTempPwdCommand");
		
		map.put("/selectMemberList.do", "controller.member.SelectMemberListCommand");
		
		map.put("/searchMember.do", "controller.member.SearchMemberCommand");
		
		map.put("/adminLogout.do", "controller.member.AdminLogoutCommand");
		
		map.put("/selectMenu.do", "controller.member.SelectMenuCommand");
		
		
		
		

		
		//예매
		map.put("/member_r_reservationStart.do","controller.reservation.ReservationStartCommand");

		map.put("/member_r_reservationStart2.do","controller.reservation.ReservationSeatCommand");
		
		map.put("/member_r_reservationStart3.do","controller.reservation.ListCardCommand");
		
		map.put("/member_r_reservationStart4.do","controller.reservation.UploadCardCommand");
		
		map.put("/order.do","controller.reservation.OrderCommand");
		
		
		
		map.put("/member_r_layout2.do","controller.reservation.UploadReservationCommand");
		
		map.put("/totalInfoRetrieveList.do", "controller.reservation.TotalInfoRetrieveListCommand");
		
		map.put("/admin_r_layout.do", "controller.reservation.ListReservationByAdminCommand");
		
		
		//공연
		map.put("/admin_p_selectPerformanceList.do","controller.performance.ListPerformanceByAdminCommand");
		
		map.put("/admin_p_detailPerformance.do","controller.performance.DetailPerformanceCommand");	
		
		map.put("/admin_p_removePerformance.do", "controller.performance.RemovePerformanceCommand");
		
		map.put("/admin_p_modifyPerformanceForm.do", "controller.performance.ModifyPerformanceFormCommand");
		
		map.put("/admin_p_findPerformance.do", "controller.performance.FindPerformanceCommand");
		
		map.put("/admin_p_removePoster.do","controller.performance.RemovePosterCommand" );
		
		map.put("/admin_p_removeDetailFile.do","controller.performance.RemoveDetailFileCommand" );
		
		map.put("/admin_p_insertScheduleForm.do","controller.performance.InsertScheduleFormCommand" );
		
		map.put("/admin_p_insertSchedule.do","controller.performance.InsertScheduleCommand" );
		
		map.put("/admin_p_removePerformanceList.do", "controller.performance.RemovePerformanceListCommand" );
		
		map.put("/member_p_selectPerformance.do","controller.performance.ListPerformanceByMemberCommand" );
		
		map.put("/member_p_detailPerformance.do", "controller.performance.DetailPerformanceByMemberCommand" );
		
		map.put("/member_p_selectPerformance_text.do", "controller.performance.ListPerformanceByTextByMemberCommand" );
		
		map.put("/member_r_layout.do","controller.performance.ListReservationCommand");


	}
	
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public Command createCommand(String uri) { //요청한 uri로 command 클래스 생성
		String commandClass = map.get(uri);
		if(commandClass == null) {
			return null;
		}
		
		try {
			Class obj = Class.forName(commandClass); //요청받은 uri에 대한 command 반환
			Command command = (Command) obj.newInstance(); // map value에 있는 타입의 인스턴스생성됨
			return command;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
