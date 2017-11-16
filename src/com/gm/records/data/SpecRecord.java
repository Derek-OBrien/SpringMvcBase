package com.gm.records.data;

public class SpecRecord implements Records {

	private char statementID[] = new char[6];
	private char startDate[] = new char[6];
	private char endDate[] = new char[6];
	private char cpl[] = new char[3];
	private char eModel[] = new char[37];
	private char source[] = new char[37];
	private char option[] = new char[37];
	private char comment[] = new char[37];

	public char[] getStatementID() {
		return statementID;
	}

	public char[] getStartDate() {
		return startDate;
	}

	public char[] getEndDate() {
		return endDate;
	}

	public char[] getCpl() {
		return cpl;
	}

	public char[] getE_model() {
		return eModel;
	}

	public char[] getSource() {
		return source;
	}

	public char[] getOption() {
		return option;
	}

	public char[] getComment() {
		return comment;
	}

	public void setStatementID(char[] _statementID) {
		this.statementID = _statementID;
	}

	public void setStartDate(char[] _startDate) {
		this.startDate = _startDate;
	}

	public void setEndDate(char[] _endDate) {
		this.endDate = _endDate;
	}

	public void setCpl(char[] _cpl) {
		this.cpl = _cpl;
	}

	public void setE_model(char[] _eModel) {
		this.eModel = _eModel;
	}

	public void setSource(char[] _source) {
		this.source = _source;
	}

	public void setOption(char[] _option) {
		this.option = _option;
	}

	public void setComment(char[] _comment) {
		this.comment = _comment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getStatementID());
		builder.append(",");
		builder.append(getStartDate());
		builder.append(",");
		builder.append(getEndDate());
		builder.append(",");
		builder.append(getCpl());
		builder.append(",");
		builder.append(getE_model());
		builder.append(",");
		builder.append(getSource());
		builder.append(",");
		builder.append(getOption());
		builder.append(",");
		builder.append(getComment());
		builder.append(",");
		return builder.toString();
	}



	@Override
	public int getType() {		
		return Records.SPEC;
	}

}
