public class Dicc {

    public static final int DEL = 127;

    public static final int t_RIGHT = -1;
    public static final int t_LEFT = -2;
    public static final int t_HOME = -3;
    public static final int t_END = -4;
    public static final int t_INTRO = -5;
    public static final int t_SUP = -7;
    public static final int t_INSERT = -9;
    public static final int t_ADD = -10;
    public static final int t_ERROR = -404; //not found


    //codes

    public static final String s_RIGHT = "\033[C";
    public static final String s_LEFT = "\033[D";
    public static final String s_HOME = "\033";
    public static final String s_END = "\033";
    public static final String s_INSERT = "\033[4h";
    public static final String s_SUP = "\033[P";
    public static final String s_DEL = "\033[D\033[P";

    public Dicc(){

    }


    public String make(int name, int pos){
        String tmp;
        if(name==t_HOME){
            tmp = "\033[" + pos + "D";
            return tmp;
        }
        else if(name== t_END){
            tmp= "\033[" + pos + "C";
            return tmp;
        }
        else
            return "";
    }



}