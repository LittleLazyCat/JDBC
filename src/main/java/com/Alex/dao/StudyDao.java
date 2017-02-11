package com.Alex.dao;

import java.util.List;

public interface StudyDao {
	//抽象方法，获得用户课程信息，保存在一个List中
	public abstract List<String> getCourseInfo(String name);
}
