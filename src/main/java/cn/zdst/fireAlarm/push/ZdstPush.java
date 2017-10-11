package cn.zdst.fireAlarm.push;

import java.util.HashMap;
import java.util.Map;

public class ZdstPush {
  private String host;
  private String port;
  
  public ZdstPush(String host,String port){
	  this.host = host;
	  this.port = port;
  }
  /*public ZdstPush(String userName,String systemType){
	  this.userName = userName;
	  this.systemType = systemType;
  }*/
  public void pushMessage(String userName,String systemType,String content) throws Exception{
	  String url = "";
	  if(host==null||"".equals(host.trim())){
		  throw new RuntimeException("host is not set !!!");
	  }
	  if(port==null||"".equals(port.trim())){
		  throw new RuntimeException("port is not set !!!");
	  }
	  if(userName==null&&systemType==null){
		  url = "http://"+host+":"+port+"/pushMsgToUsers";
	  }else{
		  url = "http://"+host+":"+port+"/pushMsgToUser";
	  }
	  Map<String,Object> param = new HashMap<String,Object>();
	  param.put("userName", userName);
	  param.put("systemType", systemType);
	  param.put("content", content);
	  String sendPost = HttpRequest.sendPost(url, param);
      System.out.println("返回结果 = "+sendPost);
  }
  public void sendMsg(Map<String,Object> pushMsgDto){
	  String url = "";
	  if(host==null||"".equals(host.trim())){
		  throw new RuntimeException("host is not set !!!");
	  }
	  if(port==null||"".equals(port.trim())){
		  throw new RuntimeException("port is not set !!!");
	  }
	  url = "http://"+host+":"+port+"/receiveMsg";
	  String result = HttpRequest.sendPost(url, pushMsgDto);
  }
  public static void main(String[] args) throws Exception {
		ZdstPush zdstPush = new ZdstPush("localhost","8124");
		Map<String, Object> pushMsgDto = new HashMap<String, Object>();
		pushMsgDto.put("userName", "ss");
		pushMsgDto.put("systemType", "ss");
		/*zdstPush.pushMessage("userName","systemType","收的到吗");*/
		zdstPush.sendMsg(pushMsgDto );
	}	
}
