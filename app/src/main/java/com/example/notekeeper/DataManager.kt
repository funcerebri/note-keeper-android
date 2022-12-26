package com.example.notekeeper

class DataManager {
    val courses = HashMap<String, CourseInfo> ()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }

    private fun initializeCourses(){
        var course = CourseInfo("couser_one", "Course Title Is Here!")
        courses.set(course.courseId, course)

        course = CourseInfo("couser_two", "Course Title Is HeSDFGSDFGre!")
        courses.set(course.courseId, course)

        course = CourseInfo("couser_three", "Course Title Is SDFGSDe!")
        courses.set(course.courseId, course)

        course = CourseInfo("couser_four", "Course Title Is asdADASF!")
        courses.set(course.courseId, course)

    }
}