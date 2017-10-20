package com.weston.cmd.menu;
import java.util.List;

public interface IAction {

	IAction getParent();

	void setParent(IAction parent);

	List<IAction> getChildren();

	String getCode();

	String getName();

	String getId();
}
