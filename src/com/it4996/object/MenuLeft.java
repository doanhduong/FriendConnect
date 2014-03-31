package com.it4996.object;

public class MenuLeft {
	private int icon;
	private String name;
	private String notification = "0";

	private boolean isNotificationVisible = false;

	public MenuLeft(int icon, String name) {
		this.icon = icon;
		this.name = name;
	}

	public MenuLeft(int icon, String name, String notification,
			boolean isNotification) {
		this.icon = icon;
		this.name = name;
		this.notification = notification;
		this.isNotificationVisible = isNotification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public boolean isNotificationVisible() {
		return isNotificationVisible;
	}

	public void setNotificationVisible(boolean isNotificationVisible) {
		this.isNotificationVisible = isNotificationVisible;
	}

}
