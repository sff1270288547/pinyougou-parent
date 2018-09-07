package entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

	private static final long serialVersionUID = 2740111006651432734L;

	private Long total;
	
	private List rows;

	public Long getTotal() {
		return total;
	}

	public PageResult() {
		super();
	}

	public PageResult(Long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
