import java.time.ZonedDateTime;

public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now();//默认时区
        System.out.println(zbj);
        //2020-12-15T20:09:16.589+08:00[Asia/Shanghai] 当前时间
    }
}
