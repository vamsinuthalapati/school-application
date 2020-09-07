package com.application.domain;

import java.util.List;

public class ListOfFilesList {

	private List<FilesListFromDrive> filesList1;
	private List<FilesListFromDrive> filesList2;
	private List<FilesListFromDrive> filesList3;
	private List<FilesListFromDrive> filesList4;
	private List<FilesListFromDrive> filesList5;
	private List<FilesListFromDrive> filesList6;
	private List<FilesListFromDrive> filesList7;
	private List<FilesListFromDrive> filesList8;
	private List<FilesListFromDrive> filesList9;
	private List<FilesListFromDrive> filesList10;

	public List<FilesListFromDrive> getFilesList1() {
		return filesList1;
	}

	public void setFilesList1(List<FilesListFromDrive> filesList1) {
		this.filesList1 = filesList1;
	}

	public List<FilesListFromDrive> getFilesList2() {
		return filesList2;
	}

	public void setFilesList2(List<FilesListFromDrive> filesList2) {
		this.filesList2 = filesList2;
	}

	public List<FilesListFromDrive> getFilesList3() {
		return filesList3;
	}

	public void setFilesList3(List<FilesListFromDrive> filesList3) {
		this.filesList3 = filesList3;
	}

	public List<FilesListFromDrive> getFilesList4() {
		return filesList4;
	}

	public void setFilesList4(List<FilesListFromDrive> filesList4) {
		this.filesList4 = filesList4;
	}

	public List<FilesListFromDrive> getFilesList5() {
		return filesList5;
	}

	public void setFilesList5(List<FilesListFromDrive> filesList5) {
		this.filesList5 = filesList5;
	}

	public List<FilesListFromDrive> getFilesList6() {
		return filesList6;
	}

	public void setFilesList6(List<FilesListFromDrive> filesList6) {
		this.filesList6 = filesList6;
	}

	public List<FilesListFromDrive> getFilesList7() {
		return filesList7;
	}

	public void setFilesList7(List<FilesListFromDrive> filesList7) {
		this.filesList7 = filesList7;
	}

	public List<FilesListFromDrive> getFilesList8() {
		return filesList8;
	}

	public void setFilesList8(List<FilesListFromDrive> filesList8) {
		this.filesList8 = filesList8;
	}

	public List<FilesListFromDrive> getFilesList9() {
		return filesList9;
	}

	public void setFilesList9(List<FilesListFromDrive> filesList9) {
		this.filesList9 = filesList9;
	}

	public List<FilesListFromDrive> getFilesList10() {
		return filesList10;
	}

	public void setFilesList10(List<FilesListFromDrive> filesList10) {
		this.filesList10 = filesList10;
	}

	public ListOfFilesList(List<FilesListFromDrive> filesList1, List<FilesListFromDrive> filesList2,
			List<FilesListFromDrive> filesList3) {
		super();
		this.filesList1 = filesList1;
		this.filesList2 = filesList2;
		this.filesList3 = filesList3;
	}

	public ListOfFilesList(List<FilesListFromDrive> filesList1, List<FilesListFromDrive> filesList2,
			List<FilesListFromDrive> filesList3, List<FilesListFromDrive> filesList4,
			List<FilesListFromDrive> filesList5, List<FilesListFromDrive> filesList6,
			List<FilesListFromDrive> filesList7, List<FilesListFromDrive> filesList8,
			List<FilesListFromDrive> filesList9, List<FilesListFromDrive> filesList10) {
		super();
		this.filesList1 = filesList1;
		this.filesList2 = filesList2;
		this.filesList3 = filesList3;
		this.filesList4 = filesList4;
		this.filesList5 = filesList5;
		this.filesList6 = filesList6;
		this.filesList7 = filesList7;
		this.filesList8 = filesList8;
		this.filesList9 = filesList9;
		this.filesList10 = filesList10;
	}

	public ListOfFilesList() {
		super();
	}

	@Override
	public String toString() {
		return "ListOfFilesList [filesList1=" + filesList1 + ", filesList2=" + filesList2 + ", filesList3=" + filesList3
				+ ", filesList4=" + filesList4 + ", filesList5=" + filesList5 + ", filesList6=" + filesList6
				+ ", filesList7=" + filesList7 + ", filesList8=" + filesList8 + ", filesList9=" + filesList9
				+ ", filesList10=" + filesList10 + "]";
	}

}
