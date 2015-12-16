package de.schmitzhermes.simpleclitool.configuration;

import java.util.List;

public abstract class SelectionContent<T> {
	private List<T> items;

	public SelectionContent() {
		setItems(possibleSelectionItems());
	}

	public abstract List<T> possibleSelectionItems();

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}
