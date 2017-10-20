package com.weston.cmd.menu.commands;

public class CmdCommand {
	public void exec(String cmd){
		Runtime rt = Runtime.getRuntime();
		Process p;
		try {
			p = rt.exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
