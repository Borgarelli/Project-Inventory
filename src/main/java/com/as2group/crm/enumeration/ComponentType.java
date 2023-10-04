package com.as2group.crm.enumeration;

public enum ComponentType {
	PROCESSOR("Processador"), RAM("Memoria RAM"), HD("Disco Rigido"), SSD(""), GRAPHICSCARD("Placa de Video");

	private String description;

	private ComponentType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
