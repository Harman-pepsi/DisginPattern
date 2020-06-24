package _03_单一职责原则;

/**
 * @Classname PrincipleOfSingleResponsibility 单一职责原则
 * @Description
 * @Date 2020/6/24 18:31
 * @Created by Harman
 */
class LiveCourse{
    public void study(String courseName){
        System.out.println(courseName + "不能快进!");
    }
}

class ReolayCourse{
    public void study(String courseName){
        System.out.println(courseName + "可以反复看!");
    }
}

//添加权限
/*
interface ICourse{
    String getCourseName();
    byte[] getCourseVideo();
    void studyCourse();
    void refundCourse();
}
*/
interface ICourseInfo{
    String getCourseName();
    byte[] getCourseVideo();
}

interface ICourseManager{
    void studyCourse();
    void refundCourse();
}

public class PrincipleOfSingleResponsibility {
    public static void main(String[] args) {
        LiveCourse liveCourse = new LiveCourse();
        liveCourse.study("直播课");
        ReolayCourse reolayCourse = new ReolayCourse();
        reolayCourse.study("录播课");
    }
}
