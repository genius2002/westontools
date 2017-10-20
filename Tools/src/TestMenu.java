import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.weston.cmd.menu.Command;
import com.weston.cmd.menu.IAction;
import com.weston.cmd.menu.Menu;
import com.weston.cmd.menu.Parameter;
import com.weston.tools.util.MySaxHandler;

public class TestMenu {

	public void makeMenu(Menu base) {
		SAXParserFactory saxpf = SAXParserFactory.newInstance();
		SAXParser sp;
		try {
			sp = saxpf.newSAXParser();
			sp.parse(
					this.getClass().getClassLoader()
							.getResourceAsStream("menu.xml"), new MySaxHandler(
							base));
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
	}

	public static void main(String[] args) {
		Menu base = new Menu();
		TestMenu ba = new TestMenu();
		ba.makeMenu(base);
		System.out.println(base);
		base.printMenu();
		Scanner sc = new Scanner(System.in);
		String currentMenuId = base.getId();
		Menu currentMenu = base;
		while (sc.hasNext()) {
			String command = sc.nextLine();
			if (command.equalsIgnoreCase("exit")) {
				break;
			} else if (command.equalsIgnoreCase("back")) {
				if (null != currentMenu && null != currentMenu.getParent()) {
					Menu next = (Menu) currentMenu.getParent();
					currentMenuId=next.getId();
					currentMenu = next;
					currentMenu.printMenu();
				}
			} else {
				IAction act = findCommand(currentMenu, command);
				if (act instanceof Command) {
					((Command) act).fireCommand(command);
				} else if (act instanceof Menu) {
					currentMenu = (Menu) act;
					((Menu) act).printMenu();
				}
			}
		}
		sc.close();
	}
	
	private static IAction findCommand(Menu menu,String code){
		IAction result = null ;
		List<IAction> commands = menu.getChildren();
		for (IAction cmd : commands) {
			if (cmd.getCode().equalsIgnoreCase(code)) {
				result= cmd;
				break;
			}
		}
		return result;
	}
}
