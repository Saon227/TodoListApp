package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		//System.out.println("\n[�׸� �߰�]");
		System.out.print("������ �Է��ϼ��� > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.println("������ �ߺ��Ǿ����ϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.print("������ �Է��ϼ��� > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("-> ����Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		//System.out.println("\n[�׸� ����]");
		System.out.print("������ ������ �Է��ϼ��� > ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("-> �����Ǿ����ϴ�.");
				break;
			} else {
				System.out.println("�ش� ������ �������� �ʽ��ϴ�.");
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		//System.out.println("\n[�׸� ����]");
		System.out.print("������ ������ �Է��ϼ��� > ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�ش� ������ �������� �ʽ��ϴ�.");
			return;
		}

		System.out.print("���ο� ������ �Է��ϼ��� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��Ǿ����ϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.print("���ο� ������ �Է��ϼ��� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("-> �����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		//System.out.println("[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
}
