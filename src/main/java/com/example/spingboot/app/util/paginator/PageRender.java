package com.example.spingboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int countElementsPage;
	private int currentPage;
	private List<PageItem> pages;
	
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		
		countElementsPage = page.getSize();
		totalPages = page.getTotalPages();
		currentPage = page.getNumber() + 1;
		
		int to,from;
		
		if(totalPages <= countElementsPage) {
			to = 1;
			from = totalPages;
		}else {
			if(currentPage <= countElementsPage/2) {
				to = 1;
				from = countElementsPage;
			}else if(currentPage >=  totalPages - countElementsPage/2) {
				to = totalPages - countElementsPage + 1;
				from = countElementsPage;
			}else {
				to = currentPage - countElementsPage/2;
				from = countElementsPage;
			}
		}
		
		for (int i = 0; i < from; i++) {
			pages.add(new PageItem(to + i, currentPage == to + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
