package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print(" ������ �Է��ϼ��� > ");
		title = sc.next().trim();
		
		if (list.isDuplicate(title)) {
			System.out.println(" -> ������ �ߺ��Ǿ����ϴ�.");
			return;
		}
		
		System.out.print(" ī�װ��� �Է��ϼ��� > ");
		category = sc.next().trim();
		
		sc.nextLine();
		System.out.print(" ������ �Է��ϼ��� > ");
		desc = sc.nextLine().trim();
		
		//sc.nextLine();
		System.out.print(" ������¥�� �Է��ϼ��� > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println(" -> ����Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(" ������ ������ �Է��ϼ��� > ");
		
		String title = sc.next();
		
		boolean flag = false;
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println(" -> �����Ǿ����ϴ�.");
				flag = true;
				break;
			}
		}
		if(flag == false) {
				System.out.println(" -> �ش� ������ �������� �ʽ��ϴ�.");
		}
	}

	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.print(" ������ ������ �Է��ϼ��� > ");
		String title = sc.next().trim();
		
		if (!l.isDuplicate(title)) {
			System.out.println(" -> �ش� ������ �������� �ʽ��ϴ�.");
			return;
		}

		System.out.print(" ���ο� ������ �Է��ϼ��� > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println(" -> ������ �ߺ��Ǿ����ϴ�.");
			return;
		}
		
		System.out.print(" ������ ī�װ��� �Է��ϼ��� > ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.print(" ���ο� ������ �Է��ϼ��� > ");
		String new_description = sc.nextLine().trim();
		
		sc.nextLine();
		System.out.print(" ���ο� ������¥�� �Է��ϼ��� > ");
		String new_due_date = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println(" -> �����Ǿ����ϴ�.");
			}
		}
	}

	public static void listAll(TodoList l) {
		//System.out.println("[��ü ���]");
		int index = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(" " + index + ". "+ item.toString());
			index++;
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		//BufferedReader, FileReader, StringTokenizer ���
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			int count = 0;
			while((oneline = br.readLine()) != null) {
				count++;
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, current_date, category, due_date);
				l.addItem(t);
			}
			br.close();
			System.out.println(count + "���� �׸��� �о����ϴ�.");
			
		} catch(FileNotFoundException e) {
			System.out.println(filename + " ������ �����ϴ�.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		//FileWriter ���
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println(" -> ��� �����Ͱ� ����Ǿ����ϴ�.");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
}
