package com.weston.cmd.menu;

import java.util.List;

public class Menu extends BaseAction implements IMenu {

	@Override
	public void printMenu() {
		List<IAction> actions = this.getChildren();
		System.out.println("### " + this.getName() + " ###");
		for (IAction action : actions) {
			if(action instanceof Menu){
				System.out.println("   +[" + action.getCode() + "]--" + action.getName());
			}else{
				System.out.println("    [" + action.getCode() + "]--" + action.getName());
			}
		}
	}

	public void exeCmd(String command) {
		System.out.println("Command["+command+"] fired @"+this.getId());
	}
}
