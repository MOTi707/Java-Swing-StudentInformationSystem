package com.NCHUStudent.pojo;

public class StuCourseMarkModel {
    private int stu_id;
    private int course_id;
    private String courseName;
    private int courseMark;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseMark() {
        return courseMark;
    }

    public void setCourseMark(int courseMark) {
        this.courseMark = courseMark;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
