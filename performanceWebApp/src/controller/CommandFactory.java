package controller;

import java.util.HashMap;

public class CommandFactory {
	//싱글턴 패턴
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	//검색속도가 빠른 map 사용
	
	private CommandFactory() {
		//클라이언트 요청을 처리하기 위해, 클라이언트 url과 요청을 처리할 command 클래스 매핑
		map.put("/loginForm.do","controller.member.LoginFormCommand");
		map.put("/modifyForm.do", "controller.member.ModifyMemberFormCommand");
		
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
