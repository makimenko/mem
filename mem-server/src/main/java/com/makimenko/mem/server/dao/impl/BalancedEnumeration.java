package com.makimenko.mem.server.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class BalancedEnumeration<T> implements Enumeration<T> {

	private final List<T> list;
	private int currentIndex = -1;
	private List<Integer> indexList;

	public BalancedEnumeration(List<T> list) {
		this.list = list;
	}

	@Override
	public boolean hasMoreElements() {
		throw new IllegalAccessError();
	}

	@Override
	public T nextElement() {
		if (indexList == null || currentIndex >= indexList.size()) {
			reload();
		}
		T result = list.get(indexList.get(currentIndex));
		currentIndex++;
		return result;
	}

	private void reload() {
		this.indexList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			indexList.add(i);
		}
		Collections.shuffle(indexList);
		currentIndex = 0;
	}

}
