package com.weston.cmd.menu;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAction implements IAction {
	private String id;
	private String name;
	private String code;
	private String idx;
	private IAction parent;
	private List<IAction> children = new ArrayList<IAction>();

	@Override
	public String toString() {
		return "BaseAction [id=" + id + ", name=" + name + ", code=" + code
				+ ", idx=" + idx + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public IAction getParent() {
		return parent;
	}

	public void setParent(IAction parent) {
		this.parent = parent;
	}

	public List<IAction> getChildren() {
		return children;
	}

	public void setChildren(List<IAction> children) {
		this.children = children;
	}

}
