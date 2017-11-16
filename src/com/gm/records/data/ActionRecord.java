package com.gm.records.data;

public class ActionRecord implements Records {

	private char action[] = new char[6];
	private char order_num[] = new char[6];
	private char source[] = new char[6];
	private char comment[] = new char[29];
	private char active[] = new char[1];

	public char[] getAction() {
		return action;
	}

	public char[] getOrder_num() {
		return order_num;
	}

	public char[] getSource() {
		return source;
	}

	public char[] getComment() {
		return comment;
	}

	public char[] getActive() {
		return active;
	}

	public void setAction(char[] _action) {
		this.action = _action;
	}

	public void setOrder_num(char[] _order_num) {
		this.order_num = _order_num;
	}

	public void setSource(char[] _source) {
		this.source = _source;
	}

	public void setComment(char[] _comment) {
		this.comment = _comment;
	}

	public void setActive(char[] _active) {
		this.active = _active;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getAction());
		builder.append(",");
		builder.append(getOrder_num());
		builder.append(",");
		builder.append(getSource());
		builder.append(",");
		builder.append(getComment());
		builder.append(",");
		builder.append(getActive());
		builder.append(",");
		return builder.toString();
	}


	@Override
	public int getType() {
		return Records.ACTION;
	}
}
